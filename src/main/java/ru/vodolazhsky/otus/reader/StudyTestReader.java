package ru.vodolazhsky.otus.reader;

import com.opencsv.exceptions.CsvException;
import ru.vodolazhsky.otus.model.StudyTest;

import java.io.IOException;

public interface StudyTestReader {

    StudyTest parseTest() throws IOException, CsvException;
}
