package com.adventurealley.adventurexp.reservation;

import com.adventurealley.adventurexp.activity.ActivityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CancelReservationTest {

    @Test
    void shouldCancelReservation() {
        //Arrange
        Reservation reservation = new Reservation();

        //Act
        reservation.cancel();

        //Assert
        assertEquals(ReservationStatus.CANCELLED, reservation.getStatus());
    }

    @Test
    void shouldCancelReservationWhereMoreThan24HoursBefore() {
        //Arrange
        ReservationRepository repo = mock(ReservationRepository.class);
        ReservationService service = new ReservationService(repo);

        Reservation reservation = new Reservation();
        reservation.setDateTime(LocalDateTime.now().plusHours(25));

        when(repo.findById(1L))
                .thenReturn(Optional.of(reservation));

        //Act
        service.cancelReservation(1L);

        //Assert
        assertEquals(ReservationStatus.CANCELLED, reservation.getStatus());
    }

    @Test
    void shouldReturnOkWhenReservationIsCancelled() {
        //Arrange
        ReservationService service = mock(ReservationService.class);
        ActivityRepository activityRepository = mock(ActivityRepository.class);
        ReservationController controller = new ReservationController(service, activityRepository);
        Reservation reservation = new Reservation();

        //Act
        ResponseEntity<String> response = controller.cancelReservation(reservation.getId());

        //Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
