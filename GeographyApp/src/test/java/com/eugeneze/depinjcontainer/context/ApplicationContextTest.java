package com.eugeneze.depinjcontainer.context;

import com.eugeneze.dao.DBPool;
import com.eugeneze.depinjcontainer.factory.BeanFactory;
import com.eugeneze.models.City;
import com.eugeneze.models.Country;
import com.eugeneze.models.PopulationDidntSupportException;
import com.eugeneze.models.Voting;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ApplicationContextTest {

    @Test
    public void getBeanThrowsExceptionWhenXmlFileDoesntExist() {
        BeanFactory beanFactory = mock(BeanFactory.class);
        ApplicationContext context = new ApplicationContext("src\\test\\resources\\nofile.xml");
        context.setBeanFactory(beanFactory);
        Assertions.assertThrows(Exception.class, () -> context.getBean(DBPool.class));
    }

    @Test
    public void getBeanThrowsExceptionWhenXmlFileIsEmpty() {
        BeanFactory beanFactory = mock(BeanFactory.class);
        ApplicationContext context = new ApplicationContext("src\\test\\resources\\test1.xml");
        context.setBeanFactory(beanFactory);
        Assertions.assertThrows(Exception.class, () -> context.getBean(DBPool.class));
    }

    @Test
    public void getBeanReturnsNotNullValueWhenXmlFileIsCorrect() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        BeanFactory beanFactory = mock(BeanFactory.class);
        ApplicationContext context = new ApplicationContext("src\\test\\resources\\test2.xml");
        context.setBeanFactory(beanFactory);
        assertThat(context.getBean(DBPool.class)).isNotNull();
    }

    @Test
    public void getBeanThrowsExceptionWhenXmlFileIsNotCorrect() {
        BeanFactory beanFactory = mock(BeanFactory.class);
        ApplicationContext context = new ApplicationContext("src\\test\\resources\\test3.xml");
        context.setBeanFactory(beanFactory);
        Assertions.assertThrows(Exception.class, () -> context.getBean(DBPool.class));
    }

}