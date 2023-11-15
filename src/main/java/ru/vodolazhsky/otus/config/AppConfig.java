package ru.vodolazhsky.otus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import ru.vodolazhsky.otus.reader.StudyTestReader;
import ru.vodolazhsky.otus.reader.StudyTestReaderCsv;

@ComponentScan(basePackages = "ru.vodolazhsky.otus")
@PropertySource("classpath:app.properties")
@Configuration
public class AppConfig {

    @Bean
    StudyTestReader studyTestReaderCsv(@Value("classpath:/data/question.csv") Resource csvFile) {
        return new StudyTestReaderCsv(csvFile);
    }
}
