package ru.vodolazhsky.otus.service;

import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import ru.vodolazhsky.otus.model.Question;
import ru.vodolazhsky.otus.model.StudyTest;
import ru.vodolazhsky.otus.reader.StudyTestReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service("organizer")
public class OrganizerStudyTest implements StudyTestService {

    private final Scanner scanner;
    private final StudyTestReader reader;
    private final int examBarrier;
    private final MessageSource mSource;

    public OrganizerStudyTest(StudyTestReader reader,
                              @Value("${exam.barrier}") int examBarrier,
                              MessageSource mSource) {

        this.scanner = new Scanner(System.in);
        this.reader = reader;
        this.examBarrier = examBarrier;
        this.mSource = mSource;
    }

    @Override
    public void execute() throws IOException, CsvException {
        System.out.print(getMessage(mSource, "study.test.organizer.name"));
        String name = scanner.nextLine().trim();
        System.out.print(getMessage(mSource, "study.test.organizer.surname"));
        String surname = scanner.nextLine().trim();

        System.out.printf(getMessage(mSource, "study.test.organizer.letsdoit"), name, surname, examBarrier);

        StudyTest studyTest = reader.parseTest(LocaleContextHolder.getLocale());
        int correctAnswerCount = 0;

        for (Question q : studyTest.questions()) {
            System.out.printf("%s\n", q.question());
            correctAnswerCount += processQuestion(q);
        }

        if(correctAnswerCount >= examBarrier) {
            System.out.printf(getMessage(mSource, "study.test.organizer.congrats"), name, correctAnswerCount);
        } else {
            System.out.printf(getMessage(mSource, "study.test.organizer.sorry"), name, correctAnswerCount);
        }
    }

    /**
     * Достаточно витиеватая логика получилась.
     * <br>
     * <br>- Если q.correctAnswers().size() == 1, следовательно вопрос имеет однозначный ответ,
     * следовательно, я имею право выполнить следующую логику: q.correctAnswers().get(0);
     * <br>
     * <br> - Если q.correctAnswers().size() > 1, то подразумевается, что вопрос имеет несколько ответов
     * <br>
     * @param q - сущность "Вопрос"
     * @return результат процессинга вопроса (1 - ответ верный, 0 - ответ неверный)
     */
    private int processQuestion(Question q) {
        if (q.correctAnswers().size() == 1) {
            System.out.printf(getMessage(mSource, "study.test.organizer.answer.option"), String.join(", ", q.possibleAnswer()));
            String expectedAnswer = q.correctAnswers().get(0).toLowerCase();
            String currentAnswer = scanner.nextLine().trim().toLowerCase();

            if (currentAnswer.equals(expectedAnswer)) {
                System.out.println(getMessage(mSource, "study.test.organizer.correct.answer"));
                return 1;
            } else {
                System.out.printf(getMessage(mSource, "study.test.organizer.wrong.answer"), expectedAnswer);
                return 0;
            }
        } else {
            System.out.println(getMessage(mSource, "study.test.organizer.listing.answer"));
            String currentAnswer;
            List<String> answers = new ArrayList<>();
            while (!(currentAnswer = scanner.nextLine().trim()).equals("end")) {
                answers.add(currentAnswer.toLowerCase());
            }
            if (q.correctAnswers().size() == answers.size() && q.correctAnswers().containsAll(answers)) {
                System.out.println(getMessage(mSource, "study.test.organizer.correct.answer"));
                return 1;
            } else {
                System.out.printf(getMessage(mSource, "study.test.organizer.wrong.answer"), String.join(", ", q.correctAnswers()));
                return 0;
            }
        }
    }
}
