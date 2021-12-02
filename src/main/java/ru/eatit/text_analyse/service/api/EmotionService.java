package ru.eatit.text_analyse.service.api;

import ru.eatit.text_analyse.service.api.entity.EmotionResult;


public interface EmotionService {
    EmotionResult analyse(String text);
}
