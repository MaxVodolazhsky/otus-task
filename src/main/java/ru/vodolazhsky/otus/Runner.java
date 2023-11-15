package ru.vodolazhsky.otus;

import com.opencsv.exceptions.CsvException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.vodolazhsky.otus.service.StudyTestService;

import java.io.IOException;

@SpringBootApplication
public class Runner {

    public static void main(String[] args) throws IOException, CsvException {
        ConfigurableApplicationContext run = SpringApplication.run(Runner.class);
        String applicationMode = run.getEnvironment().getProperty("application.mode");
        StudyTestService service = (StudyTestService) run.getBean(applicationMode);
        service.execute();
    }
}