package adventure.estera.adventurexp.controller;

import adventure.estera.adventurexp.dto.ReservationDTO;
import adventure.estera.adventurexp.model.ActivitiesEnum;
import adventure.estera.adventurexp.model.Reservation;
import adventure.estera.adventurexp.service.ReservationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/adventure/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @PostMapping
    public void createReservation(@RequestBody ReservationDTO dto) {
        String fullName = dto.firstName()+" "+dto.lastName();
        String email =dto.email();
        String phoneNumber = dto.phoneNumber();
        ActivitiesEnum activity = ActivitiesEnum.valueOf(dto.activity().toUpperCase());
        LocalDateTime dateTime = dto.dateTime();

        Reservation reservation = new Reservation(fullName, dateTime, email, phoneNumber, activity);

        reservationService.createReservation(reservation);

    }

}
