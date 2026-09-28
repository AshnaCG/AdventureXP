package adventure.estera.adventurexp.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ActivityEnumsTest {

    @Test
    @DisplayName("Der findes 4 aktiviteter")
    void hadFourActivities() {
        assertEquals(4, ActivitiesEnum.values().length);
    }
    @Test
    @DisplayName("Gocart har de rigtige værdier")
        void gocartValues() {
        ActivitiesEnum gokart = ActivitiesEnum.GOKART;

        assertEquals("Gokart", gokart.getName());
        assertEquals(30, gokart.getDurationMinutes());
        assertEquals(14, gokart.getAgeLimit());
        assertEquals(150, gokart.getHeightLimit());
        }

        @ParameterizedTest
        @EnumSource(ActivitiesEnum.class)
        @DisplayName("Billedet findes i static/image")
    void imageExists(ActivitiesEnum activity) {
        assertNotNull(getClass(). getClassLoader().getResource("static/" + activity.getImageURL()));
        }

}
