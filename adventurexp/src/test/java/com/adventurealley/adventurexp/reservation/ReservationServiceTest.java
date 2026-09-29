package com.adventurealley.adventurexp.reservation;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ReservationServiceTest {

    @Test
    void shouldCreateReservation() {
        //Arrange
        ReservationRepository repo = mock(ReservationRepository.class);
        ReservationService service = new ReservationService(repo);
        Reservation reservation = new Reservation();
        //Act
        service.createReservation(reservation);
        //Assert
        verify(repo).save(reservation);
    }
}
