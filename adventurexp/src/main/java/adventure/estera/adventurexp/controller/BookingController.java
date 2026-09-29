package adventure.estera.adventurexp.controller;

import adventure.estera.adventurexp.models.Booking;
import adventure.estera.adventurexp.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("adventure/booking")
    public class BookingController {
        private final BookingService bookingService;

        public BookingController(BookingService bookingService) {
            this.bookingService = bookingService;

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
    }



