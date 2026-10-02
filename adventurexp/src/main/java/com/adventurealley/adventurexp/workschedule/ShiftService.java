package com.adventurealley.adventurexp.workschedule;

import java.util.List;

import com.adventurealley.adventurexp.workschedule.Shift;
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
    
}
