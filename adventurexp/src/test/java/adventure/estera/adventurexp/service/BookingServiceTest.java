package adventure.estera.adventurexp.service;
import adventure.estera.adventurexp.enums.BookingType;
import adventure.estera.adventurexp.models.Booking;
import adventure.estera.adventurexp.repository.BookingRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

    class BookingServiceTest {

        private final BookingRepository bookingRepository = mock(BookingRepository.class);
        private final BookingService bookingService = new BookingService(bookingRepository);

        @Test
        @DisplayName("Hele centret med 39 personer bliver afvist")
        void wholeCenterUnder40IsRejected() {
            Booking booking = new Booking();
            booking.setType(BookingType.WHOLE_CENTER);
            booking.setParticipants(39);

            assertThrows(ResponseStatusException.class,
                    () -> bookingService.createManualBooking(booking));
        }

        @Test
        @DisplayName("Hele centret med præcis 40 personer bliver godkendt")
        void wholeCenterWith40IsAccepted() {
            Booking booking = new Booking();
            booking.setType(BookingType.WHOLE_CENTER);
            booking.setParticipants(40);

            // Grænseværdien: her må der IKKE komme en exception
            assertDoesNotThrow(() -> bookingService.createManualBooking(booking));
        }
    }

