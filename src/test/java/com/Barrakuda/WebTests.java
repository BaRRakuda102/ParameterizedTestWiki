package com.Barrakuda;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WebTests {
    @ValueSource(strings = {
            "English",
            "Deutsch"
    })
    @DisplayName("Проверка смены языка на Википедии")
    @ParameterizedTest(name = "Проверка смены языка на Википедии {0}")
    void languageWikiTests(String testData) {
        // Предусловия:
        Selenide.open("https://ru.wikipedia.org/");
        // Шаги:
        $("#mw-panel").$(byText(testData)).click();
        // Ожидаемый результат:
        $$("#mp-topbanner").find(Condition.text(testData));

    }
}

