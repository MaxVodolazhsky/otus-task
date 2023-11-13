package ru.vodolazhsky.otus.service;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;

public interface StudyTestService {

    void execute() throws IOException, CsvException;
}
