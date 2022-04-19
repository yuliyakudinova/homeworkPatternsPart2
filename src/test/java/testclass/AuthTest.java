package testclass;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import dataclass.DataGenerator;
import dataclass.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AuthTest {

    @BeforeEach
    void setUpAll() {
        open("http://localhost:9999/");
    }

    @Test
    public void shouldCheckActiveUser() {
        var user = DataGenerator.Registration.getRegisteredUser("active");
        $("[data-test-id=login] input").setValue(user.getLogin());
        $("[data-test-id=password] input").setValue(user.getPassword());
        $("[data-test-id='action-login']").click();
        $x("//*[contains(text(), 'Личный кабинет')]").should(Condition.visible);
    }

    @Test
    public void shouldCheckBlockedUser() {
        var user = DataGenerator.Registration.getRegisteredUser("blocked");
        $("[data-test-id=login] input").setValue(user.getLogin());
        $("[data-test-id=password] input").setValue(user.getPassword());
        $("[data-test-id='action-login']").click();
        $("[data-test-id='error-notification']").shouldBe(visible)
                .shouldHave(Condition.text("Ошибка\n" + "Ошибка! Пользователь заблокирован"));
    }

    @Test
    public void shouldCheckWithoutLogin() {
        var user = DataGenerator.Registration.getRegisteredUser("active");
        //$("[data-test-id=login] input").setValue(user.getLogin());
        $("[data-test-id=password] input").setValue(user.getPassword());
        $("[data-test-id='action-login']").click();
        $("[data-test-id=login].input_invalid .input__sub")
                .shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldCheckWithoutPassword() {
        var user = DataGenerator.Registration.getRegisteredUser("active");
        $("[data-test-id=login] input").setValue(user.getLogin());
        //$("[data-test-id=password] input").setValue(user.getPassword());
        $("[data-test-id='action-login']").click();
        $("[data-test-id=password].input_invalid .input__sub")
                .shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

}
