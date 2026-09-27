package adventure.adventurexp.service;

import org.springframework.stereotype.Service;

import adventure.adventurexp.model.WorkSchedule;
import adventure.adventurexp.repository.WorkScheduleRepository;

@Service
public class WorkScheduleService {
    private final WorkScheduleRepository workScheduleRepository;

    public WorkScheduleService(WorkScheduleRepository workScheduleRepository) {
        this.workScheduleRepository = workScheduleRepository;
    }


    
    public void deleteWorkScheduleById(Long id) {
        workScheduleRepository.deleteById(id);
    }

    public void deleteAllWorkSchedules() {
        workScheduleRepository.deleteAll();
    }

    public void createWorkSchedule(WorkSchedule workSchedule) {
        workScheduleRepository.save(workSchedule);
    }

    public void getAllWorkSchedules() {
        workScheduleRepository.findAll();
    }

    public WorkSchedule getWorkScheduleById(Long id) {
        return workScheduleRepository.findById(id).orElse(null);
    }

    public WorkSchedule updateWorkSchedule(WorkSchedule workSchedule) {
        return workScheduleRepository.save(workSchedule);
    }


}
