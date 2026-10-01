package adventure.estera.adventurexp;

import adventure.estera.adventurexp.enums.Role;
import adventure.estera.adventurexp.model.ActivitiesEnum;
import adventure.estera.adventurexp.model.User;
import adventure.estera.adventurexp.models.ActivityPrice;
import adventure.estera.adventurexp.models.BookingPackage;
import adventure.estera.adventurexp.repository.ActivityPriceRepository;
import adventure.estera.adventurexp.repository.BookingPackageRepository;
import adventure.estera.adventurexp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitData implements CommandLineRunner {
    private final UserRepository userRepository;
    private final ActivityPriceRepository activityPriceRepository;
    private final BookingPackageRepository bookingPackageRepository;

    public InitData(UserRepository userRepository,
                    ActivityPriceRepository activityPriceRepository,
                    BookingPackageRepository bookingPackageRepository) {
        this.userRepository = userRepository;
        this.activityPriceRepository = activityPriceRepository;
        this.bookingPackageRepository = bookingPackageRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() > 0) {
            return;
        }
        User employee = User.create("Employee", "1234", Role.EMPLOYEE);
        User admin = User.create("Admin", "1234", Role.ADMIN);
        User reservation = User.create("Reservation", "1234", Role.RESERVATION);


        userRepository.save(employee);
        userRepository.save(admin);
        userRepository.save(reservation);

        activityPriceRepository.save(new ActivityPrice(ActivitiesEnum.GOKART, 250));
        activityPriceRepository.save(new ActivityPrice(ActivitiesEnum.MINIGOLF, 100));
        activityPriceRepository.save(new ActivityPrice(ActivitiesEnum.PAINTBALL, 300));
        activityPriceRepository.save(new ActivityPrice(ActivitiesEnum.SUMOBRYDNING, 150));

        bookingPackageRepository.save(new BookingPackage("Hele centret",
                "Alle aktiviteter for hele gruppen (min. 40 personer)", 700));
    }
}

