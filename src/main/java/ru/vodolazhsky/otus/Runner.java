package ru.vodolazhsky.otus;

import com.opencsv.exceptions.CsvException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.vodolazhsky.otus.config.AppConfig;
import ru.vodolazhsky.otus.service.StudyTestService;

import java.io.IOException;

public class Runner {

    public static void main(String[] args) throws IOException, CsvException {
        var context = new AnnotationConfigApplicationContext(AppConfig.class);
        String applicationMode = context.getEnvironment().getProperty("application.mode");
        StudyTestService service = (StudyTestService) context.getBean(applicationMode);
        service.execute();
    }
}