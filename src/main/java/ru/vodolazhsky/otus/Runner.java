package ru.vodolazhsky.otus;

import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import ru.vodolazhsky.otus.service.ShowTestServiceImpl;

import java.io.IOException;

public class Runner {
    public static void main(String[] args) throws IOException, CsvException {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions("app-config.xml");

        ShowTestServiceImpl bean = factory.getBean(ShowTestServiceImpl.class);
        bean.showTest();
    }
}