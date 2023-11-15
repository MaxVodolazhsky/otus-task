package ru.vodolazhsky.otus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import ru.vodolazhsky.otus.reader.StudyTestReader;
import ru.vodolazhsky.otus.reader.StudyTestReaderCsv;

import java.util.Locale;
import java.util.Map;

@Configuration
public class AppConfig {

    @Bean
    StudyTestReader studyTestReaderCsv(
            @Value("classpath:/data/question_en.csv") Resource csvFileEn,
            @Value("classpath:/data/question_ru.csv") Resource csvFileRu) {

        Locale ruRu = new Locale("ru", "RU");
        return new StudyTestReaderCsv(
                Map.of(
                        ruRu, csvFileRu,
                        Locale.ENGLISH, csvFileEn
                )
        );
    }
}
