package com.adventurealley.adventurexp.activity;

import com.adventurealley.adventurexp.booking.BookingPackage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adventure/prices")
public class PriceController {
    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping ("/activities")
    public List<ActivityPrice> getActivityPrices() {
        return priceService.getActivityPrices();
    }
    @PutMapping("/activities/{activity}")
    public ActivityPrice updateActivityPrice
            (@PathVariable ActivitiesEnum activity, @RequestBody ActivityPrice body) {
    return priceService.updateActivityPrice(activity, body.getPricePerPerson());
    }
    @GetMapping("/packages")
    public List<BookingPackage> getPackages() {
        return priceService.getPackages();
    }

    @PostMapping("/packages")
    @ResponseStatus(HttpStatus.CREATED)
    public BookingPackage createPackage(@RequestBody BookingPackage pkg) {
        return priceService.createPackage(pkg);
    }

    @PutMapping("/packages/{id}")
    public BookingPackage updatePackage(@PathVariable Long id, @RequestBody BookingPackage pkg) {
        return priceService.updatePackage(id, pkg);
    }

}
