package adventure.estera.adventurexp.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(ActivityController.class)
class ActivityControllerTest {

    @Autowired
    MockMvcTester mvc;

    @Test
    @DisplayName("GET /adventure/activity returnerer 4 aktiviteter med de felter frontenden bruger")
    void getAll() {
        var json = assertThat(mvc.get().uri("/adventure/activity"))
                .hasStatusOk()
                .bodyJson();

        json.extractingPath("$.length()").isEqualTo(4);             // ÆNDRET (lenght -> length)
        json.extractingPath("$[0].name").isEqualTo("Gokart");
        json.extractingPath("$[0].imageURL").isEqualTo("image/Gokart.jpg");
        json.extractingPath("$[0].ageLimit").isEqualTo(14);         // ÆNDRET ($ foran)
    }

    @Test
    @DisplayName("GET /adventure/activity/gokart virker uanset store/små bogstaver")
    void getOne() {
        assertThat(mvc.get().uri("/adventure/activity/gokart"))     // ÆNDRET (lille a i adventure)
                .hasStatusOk()
                .bodyJson()
                .extractingPath("$.name").isEqualTo("Gokart");
    }

    @Test
    @DisplayName("Ukendt aktivitet giver 404")                      // ÆNDRET (stavefejl)
    void unknownGives404() {
        assertThat(mvc.get().uri("/adventure/activity/bowling"))
                .hasStatus(HttpStatus.NOT_FOUND);
    }
}