package ru.vodolazhsky.otus.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import ru.vodolazhsky.otus.model.Question;
import ru.vodolazhsky.otus.model.StudyTest;
import ru.vodolazhsky.otus.reader.StudyTestReader;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class OrganizerStudyTestTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final int examBarrier = 1;
    @Mock
    private StudyTestReader reader;

    @Mock
    private MessageSource messageSource;

    private OrganizerStudyTest organizerStudyTest;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        prepareContext();
        organizerStudyTest = new OrganizerStudyTest(reader, examBarrier, messageSource);
    }

    @Test
    void execute_happyCase() throws Exception {
        //when
        Mockito.when(reader.parseTest(LocaleContextHolder.getLocale())).thenReturn(
                new StudyTest(List.of(new Question("test", List.of("test"), List.of("test")))));
        Mockito.when(messageSource.getMessage(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn("Congratulations");
        //then
        organizerStudyTest.execute();

        Assertions.assertTrue(outputStreamCaptor.toString().contains("Congratulations"));
    }

    @Test
    void execute_badCase() throws Exception {
        //when
        Mockito.when(reader.parseTest(LocaleContextHolder.getLocale())).thenReturn(
                new StudyTest(List.of(new Question("wrong", List.of("wrong"), List.of("wrong")))));
        Mockito.when(messageSource.getMessage(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn("Sorry");
        //then
        organizerStudyTest.execute();

        Assertions.assertTrue(outputStreamCaptor.toString().contains("Sorry"));
    }

    private static void prepareContext() {
        String input = "Test\nTest\ntest\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}