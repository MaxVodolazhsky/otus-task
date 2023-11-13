package ru.vodolazhsky.otus.service;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;

public interface ShowTestService {

    void showTest() throws IOException, CsvException;
}
