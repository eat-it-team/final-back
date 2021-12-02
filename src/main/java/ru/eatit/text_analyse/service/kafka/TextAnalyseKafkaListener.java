package ru.eatit.text_analyse.service.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.eatit.common.InputJSONFields;
import ru.eatit.common.OutputJSONFields;
import ru.eatit.text_analyse.service.api.EmotionService;
import ru.eatit.text_analyse.service.impl.MatDictionaryService;

@Service
public class TextAnalyseKafkaListener {

    Logger LOGGER = LoggerFactory.getLogger(TextAnalyseKafkaListener.class);

    private final KafkaProducer kafkaProducer;
    private final EmotionService emotionService;
    private final MatDictionaryService matDictionaryService;

    @Autowired
    public TextAnalyseKafkaListener(KafkaProducer kafkaProducer, EmotionService emotionService, MatDictionaryService matDictionaryService) {
        this.kafkaProducer = kafkaProducer;
        this.emotionService = emotionService;
        this.matDictionaryService = matDictionaryService;
    }

    @KafkaListener(topics = "${kafka.input.topicName}", id = "${kafka.groupId}")
    public void listener(ConsumerRecord<String, String> record) throws ParseException {

        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.topic());
        System.out.println(record.offset());

        String message = record.value();
        String outputKey = record.key();

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(message);

        String text = null;
        boolean useEmotionAnalyse = false;
        boolean useMatAnalyse = false;
        for (Object o : json.keySet()) {
            InputJSONFields field = InputJSONFields.getField(o);
            if (field == null) {
                LOGGER.error("Not found field " + o);
                continue;
            }
            switch (field) {
                case TEXT:
                    text = (String) json.get(o);
                    break;
                case USE_EMOTION_ANALYSE:
                    useEmotionAnalyse = true;
                    break;
                case USE_MAT_ANALYSE:
                    useMatAnalyse = true;
                    break;
                default:
                    LOGGER.error("Not USE field " + o);
            }
        }
        JSONObject result = new JSONObject();

        if (text == null) {
            result.put(OutputJSONFields.ERROR_RESULT, "Не найден текст для анализа");
            kafkaProducer.sendMessage(outputKey, result.toString());
            return;
        }

        if (useEmotionAnalyse) {
            result.put(OutputJSONFields.EMOTION_ANALYSE_RESULT, emotionService.analyse(text));
        }

        if (useMatAnalyse) {
            result.put(OutputJSONFields.MAT_ANALYSE_RESULT, matDictionaryService.analyse(text));
        }

        if (result.isEmpty()) {
            result.put(OutputJSONFields.ERROR_RESULT, "Не найдены результаты анализа");
            kafkaProducer.sendMessage(outputKey, result.toString());
            return;
        }

        kafkaProducer.sendMessage(outputKey, result.toString());
    }
}
