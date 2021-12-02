package ru.eatit.text_analyse.service;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import ru.eatit.text_analyse.service.api.TextParseService;
import ru.eatit.text_analyse.service.impl.MatDictionaryService;

import java.io.IOException;


@RunWith(MockitoJUnitRunner.class)
public class MatDictionaryServiceTest {

    private MatDictionaryService matDictionaryService;

    @Before
    public void setUp() throws IOException {
        TextParseService textParseService = new TextParseService();
        matDictionaryService = new MatDictionaryService(textParseService);
    }

    @Test
    public void test3() {
        boolean b = matDictionaryService.levenshtein_equals("хуй", "хуя", true);
        System.out.println(b);

        Object хуй_хуюшки = matDictionaryService.analyse("хуй похуй");
        System.out.println(хуй_хуюшки);
    }





}