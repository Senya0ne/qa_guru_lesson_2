package tests;

import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.junit.jupiter.api.Test;
import java.io.File;


import java.util.Locale;

import static com.codeborne.selenide.Selenide.*;

public class RegistrationTests {
    



    @Test
    void successfulRegistrationTest() {
        Faker faker = new Faker();
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String userEmail = fakeValuesService.bothify("????##@gmail.com");
        String userNumber = fakeValuesService.regexify("[0-9]{10}");
        String address = faker.address().fullAddress();
        File file = new File("src/test/java/helpers/test.jpg");


        open("https://demoqa.com/automation-practice-form");
        $("#firstName").val(firstName);
        $("#lastName").val(lastName);
        $("#userEmail").val(userEmail);
        $x("//*[@for='gender-radio-1']").click();
        $("#userNumber").val(userNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__day--003").click();
//        $("#subjectsContainer").val("Hindi");
        $x("//*[@for='hobbies-checkbox-1']").click();
        $("#uploadPicture").uploadFile(file);
        $("#currentAddress").val(address);

//        $("#state").scrollTo().click();
//        $$(".css-l3qy-menu");
//        $("#city").click();
//        $(".div").selectOptionContainingText("Delhi");

        $("#submit").scrollTo().click();

        $("#example-modal-sizes-title-lg").shouldHave(Condition.text("Thanks for submitting the form"));

    }
}
