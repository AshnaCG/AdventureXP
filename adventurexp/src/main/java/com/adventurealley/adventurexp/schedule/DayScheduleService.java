package com.adventurealley.adventurexp.schedule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class DayScheduleService {

        private final ShiftRepository shiftRepository;

        public DayScheduleService(ShiftRepository shiftRepository) {
                this.shiftRepository=shiftRepository;
        }


        public List<DayScheduleDTO> getDaySchedule(List<Shift> shifts){

                Map<LocalDate, List<ShiftDTO>> dayShifts = new HashMap<>();


                for(Shift s : shifts){
                        LocalDate date =s.getDate();

                        ShiftDTO shiftDTO = new ShiftDTO(
                                s.getEmployeeName(),
                                s.getShiftStart(),
                                s.getShiftEnd(),
                                s.getDate());

                                if(!dayShifts.containsKey(date)){
                                        dayShifts.put(date, new ArrayList<>());
                                }

                                dayShifts.get(date).add(shiftDTO);
                }

                List<DayScheduleDTO> daySchedules = new ArrayList<>();

                for(Map.Entry<LocalDate, List<ShiftDTO>> entry : dayShifts.entrySet()){
                        DayScheduleDTO dayscheduleDTO = new DayScheduleDTO(entry.getKey(), entry.getValue());
                        daySchedules.add(dayscheduleDTO);
        }
        return daySchedules;
        }

   /*  public List<DayScheduleDTO> getDaySchedule(List<Shift> shifts){

        
        Map<LocalDate, List<ShiftDTO>> groupedShifts = shifts.stream()
                .collect(Collectors.groupingBy(
                        Shift::getDate,
                        Collectors.mapping(shift -> new ShiftDTO(shift.getEmployeeName(), shift.getShiftStart(), shift.getShiftEnd(), shift.getDate()), Collectors.toList())
                ));

        return groupedShifts.entrySet().stream()
                .map(entry -> new DayScheduleDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    } */

                public List<DayScheduleDTO> getWeekSchedule(LocalDate startOfWeek) {

                        List<Shift> shifts = shiftRepository.findByDate(startOfWeek);

                        LocalDate endOfWeek = startOfWeek.plusDays(6);


                        List<Shift> weekShifts = new ArrayList<>();

                        for (Shift s : shifts){

                                LocalDate date = s.getDate();
                                if(!date.isBefore(startOfWeek) && !date.isAfter(endOfWeek)){
                                        weekShifts.add(s);
                                }
                        }
                        return getDaySchedule(weekShifts);
             
                }

   /*  public List<DayScheduleDTO> getWeekSchedule(
        List<Shift> shifts, LocalDate startOfWeek) {

        LocalDate endOfWeek = startOfWeek.plusDays(6);


        List<Shift> weekShifts = shifts.stream()
                .filter(shift -> !shift.getDate().isBefore(startOfWeek) && !shift.getDate().isAfter(endOfWeek))
                .toList();


        return getDaySchedule(weekShifts);
    } */

        public List<DayScheduleDTO> getMonthSchedule(LocalDate date) {

                List<Shift> shifts = shiftRepository.findAll();

                LocalDate startOfMonth = date.withDayOfMonth(1);
                LocalDate endOfMonth = date.withDayOfMonth(date.lengthOfMonth());

                List<Shift> monthShifts = new ArrayList<>();

                for (Shift s : shifts){
                        LocalDate shiftDate = s.getDate();
                        if(!shiftDate.isBefore(startOfMonth) && !shiftDate.isAfter(endOfMonth)){
                                monthShifts.add(s);
                        }
                }
                return getDaySchedule(monthShifts);
        }


    
 /*    public List<DayScheduleDTO> getMonthSchedule(
        List<Shift> shifts, LocalDate date) {

    LocalDate startOfMonth = date.withDayOfMonth(1);
    LocalDate endOfMonth = date.withDayOfMonth(date.lengthOfMonth());

    List<Shift> monthShifts = shifts.stream()
            .filter(shift ->
                    !shift.getDate().isBefore(startOfMonth)
                    && !shift.getDate().isAfter(endOfMonth))
            .toList();

    return getDaySchedule(monthShifts);
} */

}
