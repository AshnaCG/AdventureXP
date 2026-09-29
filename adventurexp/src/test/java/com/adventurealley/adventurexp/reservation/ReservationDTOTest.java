package com.adventurealley.adventurexp.reservation;

import com.adventurealley.adventurexp.activity.Activity;
import com.adventurealley.adventurexp.activity.ActivityRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReservationDTOTest {

    @Test
    void shouldCreateReservation() {
        //Arrange
        ReservationService service = mock(ReservationService.class);
        ActivityRepository activityRepository = mock(ActivityRepository.class);
        when(activityRepository.findByNameIgnoreCase("GOKART"))
                .thenReturn(Optional.of(new Activity("Gokart", "Kør om kap", "/image/Gokart.jpg", 30, 14, 150)));
        ReservationController controller = new ReservationController(service, activityRepository);

        ReservationDTO dto = new ReservationDTO(
                "GOKART",
                "Stein",
                "Bagger",
                "steinbagger@gmail.com",
                "34587484",
                LocalDateTime.of(2026, 10, 1, 14, 0)
        );

        //Act
        controller.createReservation(dto);

        //Assert
        verify(service).createReservation(any(Reservation.class));
    }
}
