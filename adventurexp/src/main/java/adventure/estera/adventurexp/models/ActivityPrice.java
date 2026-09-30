package adventure.estera.adventurexp.models;

import adventure.estera.adventurexp.model.ActivitiesEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class ActivityPrice {

    @Id
    @Enumerated(EnumType.STRING)
    private ActivitiesEnum activity;

    private int pricePerPerson;

    public ActivityPrice() {}

    public ActivityPrice(ActivitiesEnum activity, int pricePerPerson) {
        this.activity = activity;
        this.pricePerPerson = pricePerPerson;
    }

    public ActivitiesEnum getActivity() {
        return activity;}

    public int getPricePerPerson() {
        return pricePerPerson; }

    public void setPricePerPerson(int pricePerPerson) {this.pricePerPerson = pricePerPerson;}

}



