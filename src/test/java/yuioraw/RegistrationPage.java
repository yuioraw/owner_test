package yuioraw;

import yuioraw.TestData;
import yuioraw.CalendarComponent;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    public CalendarComponent calendarComponent = new CalendarComponent();

    private static final SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            currentAddressInput = $("#currentAddress"),
            userEmailInput = $("#userEmail"),
            userNumberInput = $("#userNumber"),
            subjectInput = $("#subjectsInput"),
            genderSelectInput = $("#genterWrapper"),
            hobbiesCheckBoxInput = $("#hobbiesWrapper"),
            uploadFileInput = $("#uploadPicture"),
            stateFieldSelect = $("#state"),
            stateFieldInput = $("#stateCity-wrapper").$(byText(TestData.state)),
            cityFieldSelect = $("#city"),
            cityFieldInput = $("#stateCity-wrapper").$(byText(TestData.city)),
            submitButtonPush = $("#submit"),
            resultsTableHeader = $("#example-modal-sizes-title-lg"),
            resultsTableBody = $(".modal-body");

    @Step("Открываем страницу с формой и проверяем, что она открылась")
    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        return this;
    }

    @Step("Вводим Имя пользователя")
    public RegistrationPage typeFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    @Step("Вводим фамилию")
    public RegistrationPage typeLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    @Step("Вводим email")
    public RegistrationPage typeUserEmail(String value) {
        userEmailInput.setValue(value);
        return this;
    }

    @Step("Вводим адрес")
    public RegistrationPage typeCurrentAddress(String value) {
        currentAddressInput.setValue(value);
        return this;
    }

    @Step("Вводим номер телефона")
    public RegistrationPage typeUserNumber(String value) {
        userNumberInput.setValue(value);
        return this;
    }

    @Step("Заполняем поле Subject")
    public RegistrationPage fillSubjectField(String value) {
        subjectInput.setValue(value).pressEnter();
//        $(".subjects-auto-complete__menu #react-select-2-option-0").click();
        return this;
    }

    @Step("Выбираем пол")
    public RegistrationPage genderSelectGender(String gender) {
        genderSelectInput.$(byText(gender)).click();
        return this;
    }

    @Step("Выбираем хобби")
    public RegistrationPage hobbiesCheckBoxSelect(String hobbie) {
        hobbiesCheckBoxInput.$(byText(hobbie)).click();
        return this;
    }

    @Step("Загружаем изображение")
    public RegistrationPage uploadFile(String filePath) {
        uploadFileInput.uploadFromClasspath(filePath);
        return this;
    }

    @Step("Заполняем поле Штат")
    public RegistrationPage fillStateField() {
        stateFieldSelect.scrollTo().click();
        stateFieldInput.click();
        return this;
    }

    @Step("Заполняем поле Город")
    public RegistrationPage fillCityField() {
        cityFieldSelect.scrollTo().click();
        cityFieldInput.click();
        return this;
    }

    @Step("Нажымаем кнопку \"Отправить\"")
    public void pushSubmitButton() {
        submitButtonPush.click();
    }
    @Step("Проверяем, что окно подтверждения данных открылось")
    public RegistrationPage assertFormTitle(String title) {
        resultsTableHeader.shouldHave(text(title));
        return this;
    }

    @Step("Проверяем, что окно подтверждения данных закрылось")
    public void assertFormTitleNegative() {
        resultsTableHeader.shouldNotBe(visible);
    }

    @Step("Проверяем введенные данные")
    public RegistrationPage assertsForm(String key, String value) {
        resultsTableBody.$(byText(key)).parent().shouldHave(text(value));
        return this;
    }

    @Step("Проверяем показывается ли рекламный баннер и закрываем его при наличии")
    public void closeTable() {
        ElementsCollection closeBannerButton = $$("#close-fixedban");
        if (closeBannerButton.size() > 0 && closeBannerButton.get(0).isDisplayed())
            closeBannerButton.get(0).click();
        $("#closeLargeModal").scrollTo().click();
    }
}
