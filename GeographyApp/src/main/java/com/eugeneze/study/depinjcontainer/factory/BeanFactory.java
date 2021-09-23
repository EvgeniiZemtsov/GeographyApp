package com.eugeneze.study.depinjcontainer.factory;

import com.eugeneze.study.depinjcontainer.config.Configuration;
import com.eugeneze.study.depinjcontainer.config.JavaConfiguration;
import com.eugeneze.study.depinjcontainer.configurator.BeanConfigurator;
import com.eugeneze.study.depinjcontainer.configurator.JavaBeanConfigurator;
import com.eugeneze.study.depinjcontainer.context.ApplicationContext;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class BeanFactory {

    private final Configuration configuration;
    private final BeanConfigurator beanConfigurator;
    private final ApplicationContext applicationContext;

    private final Map<String, Class> objectFields;

    public BeanFactory(ApplicationContext applicationContext) {
        this.configuration = new JavaConfiguration();
        this.beanConfigurator = new JavaBeanConfigurator(configuration.getPackageToScan());
        this.applicationContext = applicationContext;
        this.objectFields = applicationContext.getObjectFields();
    }

    public <T> T getBean(Class<T> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<? extends T> implementationClass = clazz;
        if (implementationClass.isInterface()) {
            implementationClass = beanConfigurator.getImplementationClass(implementationClass);
        }
        T bean = implementationClass.getDeclaredConstructor().newInstance();
        for (Field field : Arrays.stream(implementationClass.getDeclaredFields()).filter(field -> objectFields.get(field.getName()).equals(clazz)).collect(Collectors.toList())) {
            field.setAccessible(true);
            field.set(bean, applicationContext.getBean(field.getType()));
        }
        return bean;
    }
}
