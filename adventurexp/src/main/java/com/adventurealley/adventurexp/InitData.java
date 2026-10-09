package com.adventurealley.adventurexp;

import com.adventurealley.adventurexp.activity.ActivitiesEnum;
import com.adventurealley.adventurexp.activity.Activity;
import com.adventurealley.adventurexp.activity.ActivityPrice;
import com.adventurealley.adventurexp.activity.ActivityPriceRepository;
import com.adventurealley.adventurexp.activity.ActivityRepository;
import com.adventurealley.adventurexp.booking.BookingPackage;
import com.adventurealley.adventurexp.booking.BookingPackageRepository;
import com.adventurealley.adventurexp.equipment.Equipment;
import com.adventurealley.adventurexp.equipment.EquipmentRepository;
import com.adventurealley.adventurexp.equipment.State;
import com.adventurealley.adventurexp.product.Product;
import com.adventurealley.adventurexp.product.ProductCategory;
import com.adventurealley.adventurexp.product.ProductCategoryRepository;
import com.adventurealley.adventurexp.product.ProductRepository;
import com.adventurealley.adventurexp.login.Role;
import com.adventurealley.adventurexp.user.User;
import com.adventurealley.adventurexp.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;
    private final EquipmentRepository equipmentRepository;
    // Nyt: booking
    private final ActivityPriceRepository activityPriceRepository;
    private final BookingPackageRepository bookingPackageRepository;

    // Produkter
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepository productRepository;

    public InitData(UserRepository userRepository,
                    ActivityRepository activityRepository,
                    EquipmentRepository equipmentRepository,
                    ActivityPriceRepository activityPriceRepository,
                    BookingPackageRepository bookingPackageRepository,
                    ProductCategoryRepository productCategoryRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.activityRepository = activityRepository;
        this.equipmentRepository = equipmentRepository;
        this.activityPriceRepository = activityPriceRepository;
        this.bookingPackageRepository = bookingPackageRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        createUsers();
        createActivitiesWithEquipment();
        createPricesAndPackages(); // Nyt: booking
        createProducts(); //
    }

    private void createUsers() {
        if (userRepository.count() > 0) {
            return;
        }
        userRepository.save(User.create("Employee", "1234", Role.EMPLOYEE));
        userRepository.save(User.create("Admin", "1234", Role.ADMIN));
    }

    private void createActivitiesWithEquipment() {
        if (activityRepository.count() > 0) {
            return;
        }

        Activity gokart = new Activity("Gokart", "Tag plads bag rattet og kør om kap på vores asfaltbane med dæk-barrierer hele vejen rundt.\n\n" +
                "Før start får alle en kort sikkerhedsinstruktion, og en medarbejder følger heatet fra banekanten",
                "/image/Gokart.jpg", 30, 14, 150);

        Activity minigolf = new Activity("Minigolf",
                "18 huller med borge, broer og forhindringer. Banen passer til hele familien.\n\n" +
                        "I går selv rundt i jeres eget tempo og tæller slag på scorekortet",
                "/image/minigolf.jpg", 60, 0, 0);

        Activity paintball = new Activity("Paintball",
                "Hold mod hold på en bane med skjul og barrikader. Prisen er pr. person og inkluderer udstyr.\n\n" +
                        "Ekstra kugler kan købes på dagen og betales efter aktiviteten.",
                "/image/paintball.jpg", 90, 15, 0);

        Activity sumo = new Activity("Sumobrydning",
                "Træk i de polstrede dragter og prøv at skubbe modstanderen ud af ringen.\n\n" +
                        "Sjovt til polterabend, fødselsdage og teambuilding",
                "/image/sumobrydning.jpg", 30, 10, 0);

        activityRepository.saveAll(List.of(gokart, minigolf, paintball, sumo));

        List<Equipment> allEquipment = new ArrayList<>();
        allEquipment.addAll(addItems(gokart, "Hjelm", 5));
        allEquipment.addAll(addItems(gokart, "Balaclava", 5));
        allEquipment.addAll(addItems(gokart, "Kørerdragt", 5));
        allEquipment.addAll(addItems(paintball, "Maske", 5));
        allEquipment.addAll(addItems(paintball, "Dragt", 5));
        allEquipment.addAll(addItems(sumo, "Dragt", 5));
        allEquipment.addAll(addItems(sumo, "Hjelm", 5));
        allEquipment.addAll(addItems(minigolf, "Golfkølle", 5));
        allEquipment.addAll(addItems(minigolf, "Golfbolde", 5));

        equipmentRepository.saveAll(allEquipment);
    }

    // Nyt: booking. ActivitiesEnum bruges midlertidigt, se issue om at erstatte den med Activity.
    private void createPricesAndPackages() {
        if (activityPriceRepository.count() > 0) {
            return;
        }
        activityPriceRepository.save(new ActivityPrice(ActivitiesEnum.GOKART, 250));
        activityPriceRepository.save(new ActivityPrice(ActivitiesEnum.MINIGOLF, 100));
        activityPriceRepository.save(new ActivityPrice(ActivitiesEnum.PAINTBALL, 300));
        activityPriceRepository.save(new ActivityPrice(ActivitiesEnum.SUMOBRYDNING, 150));

        bookingPackageRepository.save(new BookingPackage("Hele centret",
                "Alle aktiviteter for hele gruppen (min. 40 personer)", 700));
    }

    private List<Equipment> addItems(Activity activity, String name, int antal) {
        List<Equipment> items = new ArrayList<>();
        for (int i = 0; i < antal; i++) {
            Equipment e = new Equipment(name, true, State.OK);
            activity.addEquipment(e);
            items.add(e);
        }
        return items;
    }

    private void createProducts() {
        if (productCategoryRepository.count() > 0) {
            return;
        }

        // Sætter kategorierne "sodavand", "slik" og tøj op
        ProductCategory sodavand = productCategoryRepository.save(new ProductCategory("Sodavand"));
        ProductCategory slik = productCategoryRepository.save(new ProductCategory("Slik"));
        ProductCategory toej = productCategoryRepository.save(new ProductCategory("Tøj"));

        // Tilføjer produkter til databasen
        productRepository.saveAll(List.of(
                new Product(null, "Cola", "0,5 l", 20, sodavand),
                new Product(null, "Fanta", "0,5 l", 20, sodavand),
                new Product(null, "Vand", "0,5 l", 15, sodavand),
                new Product(null, "Vingummi", "Pose, 100 g", 15, slik),
                new Product(null, "Chokolade", "Mælkechokolade, 50 g", 12, slik),
                new Product(null, "T-shirt", "Adventure Alley, forskellige størrelser", 150, toej),
                new Product(null, "Kasket", "Adventure Alley", 100, toej)
        ));
    }

}