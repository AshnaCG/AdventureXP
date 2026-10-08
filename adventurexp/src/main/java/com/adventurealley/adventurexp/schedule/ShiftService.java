package com.adventurealley.adventurexp.schedule;

import java.util.List;
import java.time.LocalDate;
import org.springframework.stereotype.Service;



@Service 
public class ShiftService {


        private final ShiftRepository shiftRepository;

    public ShiftService(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }


    public void createShift(Shift shift){
        shiftRepository.save(shift);
    }

    public List<Shift> getAllShifts(){
        return shiftRepository.findAll();
    }

    public List<Shift> getShiftsByDate(LocalDate date){
        return shiftRepository.findByDateBetween(date, date);
    }
    
}
