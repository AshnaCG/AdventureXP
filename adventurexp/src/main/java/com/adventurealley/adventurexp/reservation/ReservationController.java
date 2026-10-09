package com.adventurealley.adventurexp.reservation;

import com.adventurealley.adventurexp.activity.Activity;
import com.adventurealley.adventurexp.activity.ActivityRepository;
import com.adventurealley.adventurexp.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

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
    public ResponseEntity<Long> createReservation(@RequestBody ReservationDTO dto) {
        String fullName = dto.firstName() + " " + dto.lastName();
        String email = dto.email();
        String phoneNumber = dto.phoneNumber();
        Activity activity = activityRepository.findByNameIgnoreCase(dto.activity())
                .orElseThrow(() -> new NotFoundException("Activity not found: " + dto.activity()));
        LocalDateTime dateTime = dto.dateTime();

        Reservation reservation = new Reservation(fullName, dateTime, email, phoneNumber, activity);

        Reservation saved = reservationService.createReservation(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved.getId());
    }

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @PutMapping ("/id")
    public Reservation updateReservation(
        @RequestParam Long id, 
        @RequestBody Reservation updatedReservation) {

        return reservationService.updateReservation(id, updatedReservation);
    }
    
    @DeleteMapping("/delete")
    public void deleteReservation(@RequestParam Long id) {
        reservationService.deleteReservation(id);
    }

    @GetMapping ("/activity")
    public List<Reservation> filterByActivity(
        @RequestParam String activityName) {

        return reservationService.filterByActivity(activityName);
    }

@GetMapping("/test2")
public String test2() {
    return "test2";
}


}
