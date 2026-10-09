package com.adventurealley.adventurexp.reservation;

import com.adventurealley.adventurexp.activity.Activity;
import com.adventurealley.adventurexp.activity.ActivityRepository;
import com.adventurealley.adventurexp.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/adventure/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final ActivityRepository activityRepository;
    private final ReservationRepository reservationRepository;

    public ReservationController(ReservationService reservationService, ActivityRepository activityRepository,
                                 ReservationRepository reservationRepository) {
        this.reservationService = reservationService;
        this.activityRepository = activityRepository;
        this.reservationRepository=reservationRepository;
    }

    @PostMapping
    public ResponseEntity<Long> createReservation(@RequestParam String guestName,
                                                  @RequestParam LocalDateTime dateTime,
                                                  @RequestParam String email,
                                                  @RequestParam String phoneNumber,
                                                  @RequestParam Activity activity,
                                                  @RequestParam LocalDateTime starTime,
                                                  @RequestParam int hourCount) {

        Reservation reservation = new Reservation(guestName, dateTime, email, phoneNumber,
                activity, starTime, hourCount);

        reservationRepository.save(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservation.getId());
    }
}
