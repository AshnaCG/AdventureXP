package adventure.estera.adventurexp.service;

import adventure.estera.adventurexp.enums.BookingChannel;
import adventure.estera.adventurexp.enums.BookingType;
import adventure.estera.adventurexp.models.Booking;
import adventure.estera.adventurexp.repository.BookingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BookingService {
    public static final int Min_PERSONS_WHOLE_CENTER = 40;

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking createOnlineBooking(Booking booking) {
        booking.setType(BookingType.PRIVATE);
        booking.setChannel(BookingChannel.ONLINE);
        validate(booking);
        return bookingRepository.save(booking);
    }

   public Booking createManualBooking(Booking booking) {
        booking.setChannel(BookingChannel.MANUAL);
        validate(booking);
        return bookingRepository.save(booking);
    }

    private void validate(Booking booking) {
        if (booking.getParticipants() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Antal personer skal være mindst 1");
        }

        if (booking.getType() == BookingType.WHOLE_CENTER
            && booking.getParticipants() >Min_PERSONS_WHOLE_CENTER) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Booking af hele centret kræver mindst " + Min_PERSONS_WHOLE_CENTER + " personer");

            }

        if (booking.getType() == BookingType.PRIVATE && booking.getActivity() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vælg en aktivitet");
        }
    }

}

