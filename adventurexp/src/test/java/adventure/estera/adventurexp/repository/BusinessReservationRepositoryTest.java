package adventure.estera.adventurexp.repository;

import adventure.estera.adventurexp.model.BusinessReservation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

@DataJpaTest
public class BusinessReservationRepositoryTest {

    @Autowired
    private BusinessReservationRepository repository;

    @Test
    void savedReservationGetAnId() {
        BusinessReservation r = new BusinessReservation(
                "Firma A", "Anders Andersen", "12345678",
                LocalDateTime.of(2026, 11, 20, 10, 0), 40);

        BusinessReservation saved = repository.save(r);

        assertNotNull(saved.getId());
    }

    @Test
    void savedReservationCanBeFoundAgainWithSameValues() {
        BusinessReservation saved = repository.save(new BusinessReservation(
                "Firma A", "Anders Andersen", "12345678",
                LocalDateTime.of(2026, 11, 20, 10, 0), 40));

        BusinessReservation found = repository.findById(saved.getId()).orElseThrow();

        assertEquals("Firma A", found.getCompanyName());
        assertEquals("Anders Andersen", found.getContactPerson());
        assertEquals("12345678", found.getPhoneNumber());
        assertEquals(LocalDateTime.of(2026, 11, 20, 10, 0), found.getReservationTime());
        assertEquals(40, found.getParticipants());
    }

    @Test
    void findAllReturnAllSavedReservation() {
        repository.save(new BusinessReservation("Firma A", "Anders", "11111111",
                LocalDateTime.of(2026, 11, 20, 10, 0), 40));
        repository.save(new BusinessReservation("Firma B", "Bente", "22222222",
                LocalDateTime.of(2026, 12, 1, 12, 0), 60));

        assertEquals(2, repository.findAll().size());
    }

    @Test
    void deletedReservationCannotBeFound() {
        BusinessReservation saved = repository.save(new BusinessReservation(
                "Firma A", "Anders", "11111111",
                LocalDateTime.of(2026, 11, 20, 10, 0), 40));

        repository.deleteById(saved.getId());

        assertTrue(repository.findById(saved.getId()).isEmpty());
    }
}
