package yuioraw;

import com.github.javafaker.Faker;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TestData {

    private static final Faker FAKER = new Faker();

    public final String
            firstName = FAKER.name().firstName(),
            lastName = FAKER.name().lastName(),
            email = FAKER.internet().emailAddress(),
            number = FAKER.phoneNumber().subscriberNumber(10),
            gender = "Male",
            subject = FAKER.rickAndMorty().character(),
            picture = "yay.png",
            currentAddress = FAKER.address().fullAddress(),
            state = "NCR",
            city = "Noida";

    public final Date dateOfBirth = FAKER.date().birthday();
    public final List<String> hobbies = Arrays.asList("Sports", "Music");

}