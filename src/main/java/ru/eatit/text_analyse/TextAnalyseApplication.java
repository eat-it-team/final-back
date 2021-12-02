package ru.eatit.text_analyse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class TextAnalyseApplication {

    public static void main(String[] args) {
        SpringApplication.run(TextAnalyseApplication.class, args);
    }

}
