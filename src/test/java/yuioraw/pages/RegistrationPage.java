package yuioraw.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import yuioraw.components.Calendar;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;

import java.io.File;
import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class RegistrationPage {

    private final static String FORM_TITLE = "Student Registration Form";
    private final static String RESULTS_TITLE = "Thanks for submitting the form";

    private final SelenideElement
            formTitle = $(".practice-form-wrapper");
    private final SelenideElement firstNameInput = $("#firstName");
    private final SelenideElement lastNameInput = $("#lastName");
    private final SelenideElement emailInput = $("#userEmail");
    private final SelenideElement mobileNumberInput = $("#userNumber");
    private final SelenideElement subjectsDropdown = $("#subjectsInput");
    private final SelenideElement selectPictureBtn = $("#uploadPicture");
    private final SelenideElement addressTextarea = $("#currentAddress");
    private final SelenideElement stateDropdown = $("#react-select-3-input");
    private final SelenideElement cityDropdown = $("#react-select-4-input");
    private final SelenideElement resultsPopup = $("[role=dialog]");
    private final SelenideElement resultsPopupTitle = $(".modal-title");

    private final Calendar calendar = new Calendar();

    @Step("Открытие стр-цы регистрационной формы студента")
    public void openPage() {
        open("/automation-practice-form");
        formTitle.shouldHave(text(FORM_TITLE));
    }

    @Step("Ввод имени")
    public RegistrationPage typeFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    @Step("Ввод фамилии")
    public RegistrationPage typeLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    @Step("Ввод почты")
    public RegistrationPage typeEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    @Step("Выбор пола")
    public RegistrationPage selectGender(String gender) {
        $(format("[name=gender][value=%s]", gender)).parent().click();
        return this;
    }

    @Step("Ввод номера телефона")
    public RegistrationPage typeMobileNumber(String mobileNumber) {
        mobileNumberInput.setValue(mobileNumber);
        return this;
    }

    @Step("Выбор даты рождения")
    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        calendar.setDate(day, month, year);
        return this;
    }

    @Step("Выбор предметов")
    public RegistrationPage selectSubjects(String subj1, String subj2, String subj3) {
        subjectsDropdown.setValue(subj1).pressEnter();
        subjectsDropdown.setValue(subj2).pressEnter();
        subjectsDropdown.setValue(subj3).pressEnter();
        return this;
    }

    @Step("Выбор хобби")
    public RegistrationPage selectHobbies(String hobbyNumber) {
        $(format("[for=hobbies-checkbox-%s]", hobbyNumber)).scrollTo().click();
        return this;
    }

    @Step("Загрузка фотографии")
    public RegistrationPage uploadPicture() {
        selectPictureBtn.uploadFile(new File("src/test/resources/image.png"));
        return this;
    }

    @Step("Ввод адреса проживания")
    public RegistrationPage typeCurrentAddress(String address) {
        addressTextarea.setValue(address);
        return this;
    }

    @Step("Выбор штата и города")
    public void selectStateAndCity(String state, String city) {
        stateDropdown.setValue(state).pressEnter();
        cityDropdown.setValue(city).pressEnter();
    }

    @Step("Отправка формы регистрации")
    public void submitForm() {
        $("#submit").scrollTo().click();
    }

    @Step("Проверка заголовка popup-а с результатами регистрации")
    public void checkResultsPopupTitle() {
        resultsPopup.shouldBe(visible);
        resultsPopupTitle.shouldHave(text(RESULTS_TITLE));
    }

    @Step("Проверка всех полей popup-а с результатами регистрации")
    public void checkResultsPopupValue(Map<String, String> expectedData) {
        ElementsCollection popupResults = $$(".table-responsive tbody tr").snapshot();

        SoftAssertions softly = new SoftAssertions();

        for (SelenideElement popupResult : popupResults) {
            String line = popupResult.$("td").text();
            String expectedValue = expectedData.get(line);
            String actualValue = popupResult.$("td", 1).text();

            softly.assertThat(actualValue)
                    .as(format("Result in line %s was %s, but expected %s", line, actualValue, expectedValue))
                    .isEqualTo(expectedValue);
        }
        softly.assertAll();
    }
}