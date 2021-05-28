package com.eugeneze.depinjcontainer.context;

import com.eugeneze.depinjcontainer.factory.BeanFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {

    private BeanFactory beanFactory;
    private final Map<Class, Object> beanMap = new ConcurrentHashMap<>();

    private final Map<String, Class> properties = new HashMap<>();
    private String filepath;

    public ApplicationContext(String filepath) {
        this.filepath = filepath;
        start();
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Map<String, Class> getProperties() {
        return properties;
    }

    public <T> T getBean(Class<T> clazz) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (beanMap.containsKey(clazz)) {
            return (T) beanMap.get(clazz);
        }
        T bean = beanFactory.getBean(clazz);
        beanMap.put(clazz, bean);

        return bean;
    }

    private void start() {
        File xmlFile = new File(filepath);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            // теперь XML полностью загружен в память
            document.getDocumentElement().normalize();

            // получаем узлы с именем bean
            NodeList nodeList = document.getElementsByTagName("bean");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                // если узел является элементом
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    element.getAttributeNode("class");

                    String[] classNameArray = element.getAttributeNode("class").toString().split("\"");
                    String className = classNameArray[1];
                    Class clazz = Class.forName(className);
                    beanMap.put(clazz, clazz.getDeclaredConstructor().newInstance());

                    // получаем список дочерних
                    NodeList childNodesList = element.getChildNodes();
                    for (int j = 0; j < childNodesList.getLength(); j++) {
                        Node childNode = childNodesList.item(j);

                        // если узел является элементом
                        if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                            String[] fieldNameParts = childNode.getAttributes().item(0).toString().split("\"");
                            String fieldName = fieldNameParts[1];
                            properties.put(fieldName, clazz);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
