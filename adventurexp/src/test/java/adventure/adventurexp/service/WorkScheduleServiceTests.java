    package adventure.adventurexp.service;

    import static org.mockito.Mockito.mock;
    import static org.mockito.Mockito.verify;

    import org.junit.jupiter.api.Test;
    import com.adventurealley.adventurexp.workschedule.WorkSchedule;
    import com.adventurealley.adventurexp.workschedule.WorkScheduleRepository;
    import com.adventurealley.adventurexp.workschedule.WorkScheduleService;

    public class WorkScheduleServiceTests {

        @Test 
            void shouldCreateWorkSchedule(){
                // Arrange her opsætter vi rammerne for testen, herunder opretter vi en instans af WorkScheduleService og eventuelle afhængigheder, der er nødvendige for at gemme en WorkSchedule.
                WorkScheduleRepository repo = mock(WorkScheduleRepository.class);
                WorkScheduleService service = new WorkScheduleService(repo);
                WorkSchedule workSchedule = new WorkSchedule();
                // Act her udfører vi handlingen, som vi ønsker at teste, i dette tilfælde at gemme en WorkSchedule ved hjælp af WorkScheduleService.
                service.createWorkSchedule(workSchedule);
                // Assert her kontrollerer vi, om resultatet af handlingen er som forventet, f.eks. ved at verificere, at WorkSchedule service kalder repo.save()
                verify(repo).save(workSchedule);
            }


        @Test 
            void shouldReadWorkSchedule(){
                //Arrange.
                WorkScheduleRepository repo = mock(WorkScheduleRepository.class);
                WorkScheduleService service = new WorkScheduleService(repo);
                //Act
                service.getAllWorkSchedules();
                //Assert
                verify(repo).findAll();

            }
    }

