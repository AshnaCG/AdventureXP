package com.adventurealley.adventurexp.activity;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ActivitiesEnum {
    GOKART("Gokart", "Tag plads bag rattet og kør om kap på vores asfaltbane med dæk-barrierer hele vejen rundt. \n\n" +
            "Før start får alle en kort sikkersinstruktion, og en medarbejder følger heatet fra banekanten", "Hjelm, Balaclava, Kørerdragt", "image/Gokart.jpg", 30, 14, 150),
    MINIGOLF("Minigolf", "18 huller med borge, broer og forhindringer. Banen passer til hele familien. \n\n" +
            "I går selv rundt i jeres eget tempo og tæller slag på scorekortet", "Ingen", "image/minigolf.jpg", 60, 0, 0),
    PAINTBALL("Paintball", "Hold mod hold på en bane med skjul og barrikader. Prisen er pr. person og inkluderer udstyr. \n\n" +
            "Ekstra kugler kan købes på dagen og betales efter aktiviteten.", "Maske, Dragt", "image/paintball.jpg", 90, 15, 0),
    SUMOBRYDNING("Sumobrydning", "Træk i de polstrede dragter og prøv at skubbe modstanderen ud af ringen. \n\n" +
            "Sjovt til polterabend, fødselsdage og teambuilding", "Dragt, Hjelm", "image/sumobrydning.jpg", 30, 10, 0);

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