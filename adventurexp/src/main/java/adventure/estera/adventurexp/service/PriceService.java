package adventure.estera.adventurexp.service;
import adventure.estera.adventurexp.exceptions.NotFoundException;
import adventure.estera.adventurexp.model.ActivitiesEnum;
import adventure.estera.adventurexp.models.ActivityPrice;
import adventure.estera.adventurexp.models.BookingPackage;
import adventure.estera.adventurexp.repository.ActivityPriceRepository;
import adventure.estera.adventurexp.repository.BookingPackageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PriceService {

    private final ActivityPriceRepository activityPriceRepository;
    private final BookingPackageRepository bookingPackageRepository;

    public PriceService(ActivityPriceRepository activityPriceRepository,
                        BookingPackageRepository bookingPackageRepository) {
        this.activityPriceRepository = activityPriceRepository;
        this.bookingPackageRepository = bookingPackageRepository;
    }

    // Aktiviteter //

    public List<ActivityPrice> getActivityPrices() {
        return activityPriceRepository.findAll();
    }

    public ActivityPrice updateActivityPrice(ActivitiesEnum activity, int newPrice) {
        validatePrice(newPrice);

        ActivityPrice price = activityPriceRepository.findById(activity)
                .orElseThrow(() -> new NotFoundException("Ingen pris for " + activity));

        price.setPricePerPerson(newPrice);
        return activityPriceRepository.save(price);
    }

    // Pakker

    public List<BookingPackage> getPackages() {
        return bookingPackageRepository.findAll();
    }

    public BookingPackage createPackage(BookingPackage pkg) {
        validatePrice(pkg.getPricePerPerson());
        pkg.setId(null);      // sikrer at det bliver en NY række, ikke en overskrivning
        return bookingPackageRepository.save(pkg);
    }

    public BookingPackage updatePackage(Long id, BookingPackage changes) {
        validatePrice(changes.getPricePerPerson());

        BookingPackage pkg = bookingPackageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pakke ikke fundet: " + id));

        pkg.setName(changes.getName());
        pkg.setContents(changes.getContents());
        pkg.setPricePerPerson(changes.getPricePerPerson());
        return bookingPackageRepository.save(pkg);
    }

    //Regel

    private void validatePrice(int price) {
        if (price < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Prisen må ikke være negativ");
        }
    }
}