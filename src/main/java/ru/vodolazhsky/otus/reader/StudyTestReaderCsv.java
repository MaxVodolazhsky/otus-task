package ru.vodolazhsky.otus.reader;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.core.io.Resource;
import ru.vodolazhsky.otus.model.Question;
import ru.vodolazhsky.otus.model.StudyTest;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class StudyTestReaderCsv implements StudyTestReader {

    private final Resource questions;

    public StudyTestReaderCsv(Resource questions) {
        this.questions = questions;
    }

    public StudyTest parseTest() throws IOException, CsvException {
        CSVParser csvParser = new CSVParserBuilder()
                .withSeparator(':')
                .build();

        try (CSVReader reader = new CSVReaderBuilder(
                new InputStreamReader(questions.getInputStream()))
                .withCSVParser(csvParser)
                .build()) {

            return new StudyTest(reader.readAll().stream()
                    .map(part -> new Question(
                            part[0],
                            List.of(part[1].split(",")),
                            List.of(part[2].split(","))))
                    .toList());
        }
    }
}
