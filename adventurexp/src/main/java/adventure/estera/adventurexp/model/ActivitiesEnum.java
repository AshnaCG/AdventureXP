package adventure.estera.adventurexp.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ActivitiesEnum {
    GOCART("Gokart", "Tag plads bag rattet og kør om kap", "Hjelm, Balaclava, Kørerdragt", "image/Gokart.jpg", 30, 14, 150),
    MINIGOLF("Minigolf", "Slå bolden i hul gennem 18 baner", "Ingen", "image/minigolf.jpg", 60, 0, 0),
    PAINTBALL("Paintball", "Skyd malingskugler på modstanderne", "Maske, Dragt", "image/paintball.jpg", 90, 15, 0),
    SUMOBRYDNING("Sumobrydning", "Brydekamp i oppustelige dragter", "Dragt, Hjelm", "image/sumobrydning.jpg", 30, 10, 0);

    private final String name;
    private final String description;
    private final String equipment;
    private final String imageURL;
    private final int durationMinutes;
    private final int ageLimit;
    private final int heightLimit;

    ActivitiesEnum(String name, String description, String equipment, String imageURL,
                   int durationMinutes, int ageLimit, int heightLimit) {
        this.name = name;
        this.description = description;
        this.equipment = equipment;
        this.imageURL = imageURL;
        this.durationMinutes = durationMinutes;
        this.ageLimit = ageLimit;
        this.heightLimit = heightLimit;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getEquipment() { return equipment; }
    public String getImageURL() { return imageURL; }
    public int getDurationMinutes() { return durationMinutes; }
    public int getAgeLimit() { return ageLimit; }
    public int getHeightLimit() { return heightLimit; }
}