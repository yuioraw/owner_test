package testwithjenkins.pages;

import com.codeborne.selenide.SelenideElement;
import testwithjenkins.TestData;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {

    public PracticeFormPage() {
    }

    SelenideElement
            firstName = $("#firstName"),
            lastName = $("#lastName"),
            email = $("#userEmail"),
            userNumber = $("#userNumber"),
            gender = $("#genterWrapper"),
            subject = $("#subjectsInput"),
            hobbies = $("#hobbiesWrapper"),
            uploadPicture = $("#uploadPicture"),
            currentAddress = $("#currentAddress"),
            stateInput = $("#react-select-3-input"),
            cityInput = $("#react-select-4-input"),
            submit = $("#submit");

    public PracticeFormPage openPracticeFormPage() {
        open("https://demoqa.com/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        return this;
    }

    public PracticeFormPage fillFirsName(String firstName) {
        this.firstName.val(firstName);
        return this;
    }

    public PracticeFormPage fillLastName(String lastName) {
        this.lastName.val(lastName);
        return this;
    }

    public PracticeFormPage fillEmail(String email) {
        this.email.val(email);
        return this;
    }

    public PracticeFormPage fillUserNumber(String number) {
        userNumber.val(number);
        return this;
    }

    public PracticeFormPage selectGender(String genderName) {
        gender.$(byText(genderName)).click();
        return this;
    }

    public PracticeFormPage fillSubject(String subject) {
        this.subject.val(subject);
        return this;
    }

    public PracticeFormPage selectHobbies(List<String> hobbies) {
        hobbies.forEach(hobby -> this.hobbies.$(byText(hobby)).click());
        return this;
    }

    public PracticeFormPage uploadPicture(String filePath) {
        uploadPicture.uploadFromClasspath(filePath);
        return this;
    }

    public PracticeFormPage fillCurrentAddress(String Address) {
        currentAddress.scrollTo().val(Address);
        return this;
    }

    public PracticeFormPage fillState(String State) {
        stateInput.val(State).pressEnter();
        return this;
    }

    public PracticeFormPage fillCity(String City) {
        cityInput.val(City).pressEnter();
        return this;
    }

    public PracticeFormPage fillDateOfBirth(Date birthDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthDay);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(calendar.get(Calendar.MONTH));
        $(".react-datepicker__year-select").selectOption(String.valueOf(calendar.get(Calendar.YEAR)));
        $$x("//div[contains(@class,'datepicker__day ')][not(contains(@class,'outside-month'))]")
                .find(text(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)))).click();
        return this;
    }

    public PracticeFormPage clickSubmit() {
        submit.click();
        return this;
    }

    public PracticeFormPage assertForm(TestData testData) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM,yyyy", Locale.ENGLISH);
        $(".table").shouldHave(text(
                        testData.firstName + " " + testData.lastName), text(testData.email),
                text(testData.gender), text(testData.number), text(dateFormat.format(testData.dateOfBirth)),
                text(testData.hobbies.get(0) + ", " + testData.hobbies.get(1)),
                text(testData.currentAddress), text(testData.state + " " + testData.city));
        return this;
    }


}
