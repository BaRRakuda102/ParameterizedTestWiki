package com.Barrakuda;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WebTests {
    @CsvSource({
            "English, Welcome to Wikipedia",
            "Deutsch, Willkommen bei Wikipedia"
    })
    @DisplayName("Проверка смены языка на Википедии")
    @ParameterizedTest(name = "Проверка смены языка на Википедии {0}, ожидаем результат {1}")
    void languageWikiTests(String testData, String expectedResult) {
        // Предусловия:
        Selenide.open("https://ru.wikipedia.org/");
        // Шаги:
        $("#mw-panel").$(byText(testData)).click();
        // Ожидаемый результат:
        $$("#mp-topbanner").find(Condition.text(expectedResult));

    }

    static Stream<Arguments> methodSourceTestDataAndSeveralResults() {
        return Stream.of(
                Arguments.of("English", List.of("Welcome to Wikipedia")),
                Arguments.of("Deutsch", List.of("Willkommen bei Wikipedia"))
        );
    }

    @MethodSource("methodSourceTestDataAndSeveralResults")
    @ParameterizedTest
    void amazonSearchDataAndSeveralResults(String testData, List<String> expectedResult) {
        // Предусловия:
        Selenide.open("https://ru.wikipedia.org/");
        // Шаги:
        $("#mw-panel").$(byText(testData)).click();
        // Ожидаемый результат:
        $$("#mp-topbanner").find(Condition.text(String.valueOf(expectedResult)));
    }
}


