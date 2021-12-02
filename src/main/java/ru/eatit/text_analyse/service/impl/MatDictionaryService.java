package ru.eatit.text_analyse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.eatit.text_analyse.service.api.TextParseService;

import java.io.IOException;

@Service
public class MatDictionaryService extends AbstractDictionaryService {

    @Autowired
    public MatDictionaryService(TextParseService textParseService) throws IOException {
        super(textParseService, "mat.txt");
    }


}
