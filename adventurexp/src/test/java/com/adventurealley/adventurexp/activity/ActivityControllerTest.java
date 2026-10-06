package com.adventurealley.adventurexp.activity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@WebMvcTest(ActivityController.class)
class ActivityControllerTest {

    @Autowired
    MockMvcTester mvc;

    @MockitoBean
    ActivityRepository activityRepository;

    @MockitoBean
    ActivityService activityService;

    private final Activity gokart = new Activity("Gokart", "Kør om kap", "/image/Gokart.jpg", 30, 14, 150);
    private final Activity minigolf = new Activity("Minigolf", "18 huller", "/image/minigolf.jpg", 60, 0, 0);

    @Test
    @DisplayName("GET /adventure/activity returnerer aktiviteterne med de felter frontenden bruger")
    void getAll() {
        when(activityRepository.findAll()).thenReturn(List.of(gokart, minigolf));

        var json = assertThat(mvc.get().uri("/adventure/activity"))
                .hasStatusOk()
                .bodyJson();

        json.extractingPath("$.length()").isEqualTo(2);
        json.extractingPath("$[0].name").isEqualTo("Gokart");
        json.extractingPath("$[0].imageURL").isEqualTo("/image/Gokart.jpg");
        json.extractingPath("$[0].minAge").isEqualTo(14);
    }

    @Test
    @DisplayName("GET /adventure/activity/gokart virker uanset store/små bogstaver")
    void getOne() {
        when(activityRepository.findByNameIgnoreCase("gokart")).thenReturn(Optional.of(gokart));

        assertThat(mvc.get().uri("/adventure/activity/gokart"))
                .hasStatusOk()
                .bodyJson()
                .extractingPath("$.name").isEqualTo("Gokart");
    }

    @Test
    @DisplayName("Ukendt aktivitet giver 404")
    void unknownGives404() {
        when(activityRepository.findByNameIgnoreCase("bowling")).thenReturn(Optional.empty());

        assertThat(mvc.get().uri("/adventure/activity/bowling"))
                .hasStatus(HttpStatus.NOT_FOUND);
    }
}
