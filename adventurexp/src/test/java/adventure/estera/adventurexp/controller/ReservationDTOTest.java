package adventure.estera.adventurexp.controller;

import adventure.estera.adventurexp.dto.ReservationDTO;
import adventure.estera.adventurexp.model.Reservation;
import adventure.estera.adventurexp.service.ReservationService;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ReservationDTOTest {

    @Test
    void shouldCreateReservation() {
        //Arrange
        ReservationService service = mock(ReservationService.class);
        ReservationController controller = new ReservationController(service);

        ReservationDTO dto = new ReservationDTO(
                "GOCART",
                "Stein",
                "Bagger",
                "steinbagger@gmail.com",
                "34587484",
                LocalDateTime.of(2026,10,1,14,0)
        );

        //Act
        controller.createReservation(dto);

        //Assert
        verify(service).createReservation(any(Reservation.class));



    }
}
