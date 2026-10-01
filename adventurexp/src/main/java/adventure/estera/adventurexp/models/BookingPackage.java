package adventure.estera.adventurexp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
public class BookingPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String contents;
    private int pricePerPerson;

    public BookingPackage() {
    }

    public BookingPackage(String name, String contents, int pricePerPerson) {
        this.name = name;
        this.contents = contents;
        this.pricePerPerson = pricePerPerson;

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContents() {
        return contents;
    }

    public int getPricePerPerson() {
        return pricePerPerson;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setPricePerPerson(int pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }

}
