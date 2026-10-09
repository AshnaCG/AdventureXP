package com.adventurealley.adventurexp.booking;

import com.adventurealley.adventurexp.exception.NotFoundException;
import com.adventurealley.adventurexp.activity.ActivityPrice;
import com.adventurealley.adventurexp.activity.ActivityPriceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {
    public static final int MIN_PERSONS_WHOLE_CENTER = 40;

    private final BookingRepository bookingRepository;

    private final ActivityPriceRepository activityPriceRepository;
    private final BookingPackageRepository bookingPackageRepository;

    public BookingService(BookingRepository bookingRepository, ActivityPriceRepository activityPriceRepository,
                          BookingPackageRepository bookingPackageRepository) {

        this.bookingRepository = bookingRepository;
        this.bookingPackageRepository = bookingPackageRepository;
        this.activityPriceRepository = activityPriceRepository;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAllByOrderByStartTimeAsc();
    }

    public Booking createOnlineBooking(Booking booking) {
        booking.setType(BookingType.PRIVATE);
        booking.setChannel(BookingChannel.ONLINE);
        validate(booking);
        calculatePrice(booking);
        return bookingRepository.save(booking);
    }

    public Booking createManualBooking(Booking booking) {
        booking.setChannel(BookingChannel.MANUAL);
        validate(booking);
        calculatePrice(booking);
        return bookingRepository.save(booking);
    }

    private void validate(Booking booking) {
        if (booking.getParticipants() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Antal personer skal være mindst 1");
        }

        if (booking.getType() == BookingType.WHOLE_CENTER
                && booking.getParticipants() < MIN_PERSONS_WHOLE_CENTER) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Booking af hele centret kræver mindst " + MIN_PERSONS_WHOLE_CENTER + " Personer");

        }

        if (booking.getType() == BookingType.PRIVATE && booking.getActivity() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vælg en aktivitet");
        }
    }

    private void calculatePrice(Booking booking) {
        int pricePerPerson;

        if (booking.getBookingPackage() != null) {

            Long packageId = booking.getBookingPackage().getId();
            BookingPackage pkg = bookingPackageRepository.findById(packageId)
                    .orElseThrow(() -> new NotFoundException("Pakke ikke fundet: " + packageId));

            booking.setBookingPackage(pkg);
            booking.setPackageContents(pkg.getContents());
            pricePerPerson = pkg.getPricePerPerson();}

        else if (booking.getActivity() != null) {

            ActivityPrice price = activityPriceRepository.findById(booking.getActivity())
                    .orElseThrow(() -> new NotFoundException("Ingen pris for " + booking.getActivity()));

            booking.setPackageContents(null);
            pricePerPerson = price.getPricePerPerson();

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Vælg en aktivitet eller en pakke, så prisen kan beregnes");
        }

        booking.setTotalPrice(pricePerPerson *booking.getParticipants());
}

    public Booking cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Booking ikke fundet: " + id));

        if (booking.getStartTime().isBefore(LocalDateTime.now().plusHours(24))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Reservationen kan ikke aflyses under 24 timer før start");
        }

        booking.setCancelled(true);
        return bookingRepository.save(booking);
    }

}

