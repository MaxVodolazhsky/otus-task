package ru.vodolazhsky.otus.service;

import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.vodolazhsky.otus.model.StudyTest;
import ru.vodolazhsky.otus.reader.StudyTestReader;

import java.io.IOException;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class StudyTestServiceImplTest {

    @Mock
    private StudyTestReader reader;

    private StudyTestService service;

    @BeforeEach
    void setUp() {
        service = new StudyTestPrinter(reader);
    }

    @Test
    void showTest() throws IOException, CsvException {
        // when
        Mockito.when(reader.parseTest()).thenReturn(new StudyTest(List.of()));

        // then
        service.execute();
        Mockito.verify(reader).parseTest();
    }
}