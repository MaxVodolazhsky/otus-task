package ru.vodolazhsky.otus.reader;

import com.opencsv.exceptions.CsvException;
import ru.vodolazhsky.otus.model.StudyTest;

import java.io.IOException;
import java.util.Locale;

public interface StudyTestReader {

    StudyTest parseTest(Locale locale) throws IOException, CsvException;
}
