package adventure.estera.adventurexp.service;

import adventure.estera.adventurexp.model.Reservation;
import adventure.estera.adventurexp.repository.ReservationRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ReservertionServiceTest {

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
