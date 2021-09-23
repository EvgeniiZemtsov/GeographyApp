package com.eugeneze.depinjcontainer.context;

import com.eugeneze.study.depinjcontainer.context.ApplicationContext;
import com.eugeneze.study.depinjcontainer.factory.BeanFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ApplicationContextTest {

    @Test
    public void getBeanThrowsExceptionWhenXmlFileDoesntExist() {
        BeanFactory beanFactory = mock(BeanFactory.class);
        ApplicationContext context = new ApplicationContext("src\\test\\resources\\nofile.xml");
        context.setBeanFactory(beanFactory);
        Assertions.assertThrows(Exception.class, () -> context.getBean(FirstTestClass.class));
    }

    @Test
    public void getBeanThrowsExceptionWhenXmlFileIsEmpty() {
        BeanFactory beanFactory = mock(BeanFactory.class);
        ApplicationContext context = new ApplicationContext("src\\test\\resources\\EmptyXmlFile.xml");
        context.setBeanFactory(beanFactory);
        Assertions.assertThrows(Exception.class, () -> context.getBean(FirstTestClass.class));
    }

    @Test
    public void getBeanReturnsNotNullValueWhenXmlFileIsCorrect() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        BeanFactory beanFactory = mock(BeanFactory.class);
        ApplicationContext context = new ApplicationContext("src\\test\\resources\\CorrectXmlFile.xml");
        context.setBeanFactory(beanFactory);
        assertThat(context.getBean(FirstTestClass.class)).isNotNull();
    }

    @Test
    public void getBeanThrowsExceptionWhenXmlFileIsNotCorrect() {
        BeanFactory beanFactory = mock(BeanFactory.class);
        ApplicationContext context = new ApplicationContext("src\\test\\resources\\NotCorrectXmlFile.xml");
        context.setBeanFactory(beanFactory);
        Assertions.assertThrows(Exception.class, () -> context.getBean(FirstTestClass.class));
    }

    @Test
    public void getBeanReturnsNotNullValuesWhenXmlFileIsCorrectAndContainsTransitiveDependency() throws NoSuchMethodException,
            InstantiationException,
            IllegalAccessException,
            InvocationTargetException {
        BeanFactory beanFactory = mock(BeanFactory.class);
        ApplicationContext context = new ApplicationContext("src\\test\\resources\\TransitiveDependencyFile.xml");
        context.setBeanFactory(beanFactory);
        assertThat(context.getBean(FirstTestClass.class)).isNotNull();
        assertThat(context.getBean(SecondTestClass.class)).isNotNull();
        assertThat(context.getBean(ThirdTestClass.class)).isNotNull();
        assertThat(context.getBean(FourthTestClass.class)).isNotNull();
    }

    @Test
    public void getBeanReturnsNotNullValuesWhenXmlFileIsCorrectAndContainsCycleDependency() throws NoSuchMethodException,
            InstantiationException,
            IllegalAccessException,
            InvocationTargetException {
        BeanFactory beanFactory = mock(BeanFactory.class);
        ApplicationContext context = new ApplicationContext("src\\test\\resources\\CycleDependencyFile.xml");
        context.setBeanFactory(beanFactory);
        assertThat(context.getBean(TestClassA.class)).isNotNull();
        assertThat(context.getBean(TestClassB.class)).isNotNull();
    }

}