package com.adventurealley.adventurexp.booking;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("adventure/booking")
    public class BookingController {
        private final BookingService bookingService;

        public BookingController(BookingService bookingService) {
            this.bookingService = bookingService;

        }
        @GetMapping public List<Booking> getAll() {
            return bookingService.getAllBookings();
        }

        @PostMapping("/online")
        @ResponseStatus(HttpStatus.CREATED)
        public Booking createOnline (@RequestBody Booking booking) {
            return bookingService.createOnlineBooking(booking);
        }

        @PostMapping("/manual")
        @ResponseStatus(HttpStatus.CREATED)
        public Booking createManual(@RequestBody Booking booking) {
            return bookingService.createManualBooking(booking);
        }

    @PutMapping("/{id}/cancel")
    public Booking cancel(@PathVariable Long id) {
        return bookingService.cancelBooking(id);
    }
    }



