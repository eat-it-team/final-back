package ru.eatit.text_analyse.service.api;

import ru.eatit.text_analyse.service.api.entity.DictionaryAnalyseResult;

/**
 * Анализирует текст на наличие слов из определенного словаря
 */
public interface DictionaryService {

    DictionaryAnalyseResult analyse(String text);
}
