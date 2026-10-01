package adventure.estera.adventurexp.service;

import adventure.estera.adventurexp.enums.BookingType;
import adventure.estera.adventurexp.models.Booking;
import adventure.estera.adventurexp.models.BookingPackage;
import adventure.estera.adventurexp.repository.ActivityPriceRepository;
import adventure.estera.adventurexp.repository.BookingPackageRepository;
import adventure.estera.adventurexp.repository.BookingRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BookingServiceTest {

    private final BookingRepository bookingRepository = mock(BookingRepository.class);
    private final ActivityPriceRepository activityPriceRepository = mock(ActivityPriceRepository.class);
    private final BookingPackageRepository bookingPackageRepository = mock(BookingPackageRepository.class);

    private final BookingService bookingService =
            new BookingService(bookingRepository, activityPriceRepository, bookingPackageRepository);

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

        BookingPackage pkg = new BookingPackage("Hele centret", "Alle aktiviteter for hele gruppen", 700);   // ÆNDRET
        pkg.setId(1L);
        when(bookingPackageRepository.findById(1L)).thenReturn(Optional.of(pkg));
        booking.setBookingPackage(pkg);

        // Grænseværdien: her må der IKKE komme en exception
        assertDoesNotThrow(() -> bookingService.createManualBooking(booking));
    }
}