package com.adventurealley.adventurexp.reservation;

import com.adventurealley.adventurexp.activity.Activity;
import com.adventurealley.adventurexp.activity.ActivityRepository;
import com.adventurealley.adventurexp.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/adventure/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final ActivityRepository activityRepository;

    public ReservationController(ReservationService reservationService, ActivityRepository activityRepository) {
        this.reservationService = reservationService;
        this.activityRepository = activityRepository;
    }

    @PostMapping
    public void createReservation(@RequestBody ReservationDTO dto) {
        String fullName = dto.firstName() + " " + dto.lastName();
        String email = dto.email();
        String phoneNumber = dto.phoneNumber();
        Activity activity = activityRepository.findByNameIgnoreCase(dto.activity())
                .orElseThrow(() -> new NotFoundException("Activity not found: " + dto.activity()));
        LocalDateTime dateTime = dto.dateTime();

        Reservation reservation = new Reservation(fullName, dateTime, email, phoneNumber, activity);

        reservationService.createReservation(reservation);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<String> cancelReservation(@PathVariable Long id) {

        try {
            reservationService.cancelReservation(id);

            return ResponseEntity.ok("Reservation cancelled ✅");
        } catch (IllegalStateException e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
