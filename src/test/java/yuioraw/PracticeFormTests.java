package yuioraw;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class PracticeFormTests extends TestBase {

    @Test
    void successTest() {
        step("открываем нужный репо", () -> {
            open("/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        });
        step("вводим имя и фамилию", () -> {
            $("#firstName").setValue("Veronika");
            $("#lastName").setValue("Padgok");

        });
        step("вводим email", () -> {
            $("#userEmail").setValue("testForm@mailinator.com");
        });
        step("выбираем пол", () -> {
            $("#gender-radio-2").parent().click();
        });
        step("вводим номер  телефона", () -> {
            $("#userNumber").setValue("1234543456");
        });
        step("вводим дату рождения", () -> {
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption("November");
            $(".react-datepicker__year-select").selectOption("1984");
            $(".react-datepicker__day--018").click();
        });
        step("выбираем любимый предмет", () -> {
            $("#subjectsContainer").click();
            $("#subjectsInput").setValue("Maths").pressEnter();
        });

        step("выбираем хобби", () ->
        {
            $("[for= hobbies-checkbox-2]").click();
        });

        //$("#uploadPicture").uploadFromClasspath("text.txt");
        step("вводим адрес проживания", () -> {
            $("#currentAddress").setValue("Minsk, 220117");
        });
        step("выбираем штат и город", () -> {
            $("#state").scrollTo().click();

            $("#state").$(byText("NCR")).click();

            $("#city").click();

            $("#city").$(byText("Delhi")).click();

            $("#submit").click();
        });
        step("делаем проверку", () -> {
            SelenideElement table = $(".modal-body").$("table").$("tbody");
            Assertions.assertEquals("Veronika Padgok", table.$("tr:nth-child(1)").
                    $("td:nth-child(2)").
                    getText());
            Assertions.assertEquals("testForm@mailinator.com", table.$("tr:nth-child(2)").
                    $("td:nth-child(2)").
                    getText());
            Assertions.assertEquals("Female", table.$("tr:nth-child(3)").
                    $("td:nth-child(2)").
                    getText());
            Assertions.assertEquals("1234543456", table.$("tr:nth-child(4)").
                    $("td:nth-child(2)").
                    getText());
            Assertions.assertEquals("18 November,1984", table.$("tr:nth-child(5)").
                    $("td:nth-child(2)").
                    getText());
            Assertions.assertEquals("Maths", table.$("tr:nth-child(6)").
                    $("td:nth-child(2)").
                    getText());
            Assertions.assertEquals("Reading", table.$("tr:nth-child(7)").
                    $("td:nth-child(2)").
                    getText());
            //Assertions.assertEquals("text.txt", table.$("tr:nth-child(8)").$("td:nth-child(2)").getText());
            Assertions.assertEquals("Minsk, 220117", table.$("tr:nth-child(9)").
                    $("td:nth-child(2)").
                    getText());
            Assertions.assertEquals("NCR Delhi", table.$("tr:nth-child(10)").
                    $("td:nth-child(2)").
                    getText());
        });

    }
}