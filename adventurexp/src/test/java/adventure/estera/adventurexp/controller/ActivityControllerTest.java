package adventure.estera.adventurexp.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.test.web.servlet.assertj.MockMvcTester;


    @WebMvcTest(ActivityController.class)
    class ActivityControllerTest{

        @Autowired
        MockMvcTester mvc;

        @Test
        @DisplayName ("Get/adventure/activity returnerer 4 aktiviteter med de samme felter frontenden bruger")
        void getAll() {
        var json = assertThat(mvc.get().uri("/adventure/activity"))
                .hasStatusOk()
                .bodyJson();

        json.extractingPath("$.lenght()").isEqualTo(4);
        json.extractingPath("$[0].name").isEqualTo("Gokart");
        json.extractingPath("$[0].imageURL").isEqualTo("image/Gokart.jpg");
        json.extractingPath("[0].ageLimit").isEqualTo(14);
        }
        @Test
        @DisplayName("GET /adventure/activity/gokart virker uanset store/små bogstaver")
        void getOne() {
            assertThat(mvc.get().uri("/Adventure/activity/gokart"))
                    .hasStatusOk()
                    .bodyJson()
                    .extractingPath("$.name").isEqualTo("Gokart");
            }

        @Test
        @DisplayName("Ukent aktivitet giver 404")
        void unknownGives404() {
            assertThat(mvc.get().uri("/adventure/activity/bowling"))
                    .hasStatus(HttpStatus.NOT_FOUND);
        }



}
