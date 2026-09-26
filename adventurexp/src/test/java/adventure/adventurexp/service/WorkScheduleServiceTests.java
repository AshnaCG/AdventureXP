package adventure.adventurexp.service;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import adventure.adventurexp.repository.WorkScheduleRepository;

public class WorkScheduleServiceTests {

    @Test 
        void shouldSaveWorkSchedule(){
            // Arrange her opsætter vi rammerne for testen, herunder opretter vi en instans af WorkScheduleService og eventuelle afhængigheder, der er nødvendige for at gemme en WorkSchedule.
            WorkScheduleRepository repo = mock(WorkScheduleRepo)
            // Act her udfører vi handlingen, som vi ønsker at teste, i dette tilfælde at gemme en WorkSchedule ved hjælp af WorkScheduleService.
            // Assert her kontrollerer vi, om resultatet af handlingen er som forventet, f.eks. ved at verificere, at WorkSchedule er blevet gemt korrekt i databasen eller at den returnerede værdi er som forventet.
        }
    
}

