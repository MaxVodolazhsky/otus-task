package ru.vodolazhsky.otus.service;

import com.opencsv.exceptions.CsvException;
import ru.vodolazhsky.otus.model.Question;
import ru.vodolazhsky.otus.model.StudyTest;
import ru.vodolazhsky.otus.reader.StudyTestReader;

import java.io.IOException;
import java.util.List;

public class ShowTestServiceImpl implements ShowTestService {

    private final StudyTestReader studyTestReader;

    public ShowTestServiceImpl(StudyTestReader studyTestReader) {
        this.studyTestReader = studyTestReader;
    }

    public void showTest() throws IOException, CsvException {
        StudyTest studyTest = studyTestReader.parseTest();
        List<Question> questions = studyTest.questions();
        int count = 1;

        System.out.println("\t".repeat(7) + "=== TEST ===");
        System.out.println("_".repeat(80));
        System.out.printf("%s. %50s %20s\n", "№", "Question", "Correct answers");
        System.out.println("_".repeat(80));

        for (Question question : questions) {
            System.out.printf("%d. %50s  %20s\n",
                    count++,
                    question.question(),
                    String.join(", ", question.correctAnswers())
            );

            System.out.println("_".repeat(80));
        }
    }
}
