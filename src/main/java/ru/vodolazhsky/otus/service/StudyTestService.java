package ru.vodolazhsky.otus.service;

import com.opencsv.exceptions.CsvException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.io.IOException;

public interface StudyTestService {

    void execute() throws IOException, CsvException;

    default String getMessage(MessageSource ms, String key) {
        return ms.getMessage(key, null, LocaleContextHolder.getLocale());
    }
}
