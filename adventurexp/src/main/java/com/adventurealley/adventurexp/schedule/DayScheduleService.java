package com.adventurealley.adventurexp.schedule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import com.adventurealley.adventurexp.booking.Booking;
import com.adventurealley.adventurexp.booking.BookingDTO;
import com.adventurealley.adventurexp.booking.BookingRepository;

@Service
public class DayScheduleService {

        private final ShiftRepository shiftRepository;
        private final BookingRepository bookingRepository;

        public DayScheduleService(ShiftRepository shiftRepository, BookingRepository bookingRepository) {
                this.shiftRepository = shiftRepository;
                this.bookingRepository = bookingRepository;
        }

        public List<DayScheduleDTO> getPeriodSchedule(LocalDate startDate, LocalDate endDate, List<Shift> shifts,
                        List<Booking> bookings) {

                Map<LocalDate, List<ShiftDTO>> dayShifts = new TreeMap<>();
                Map<LocalDate, List<BookingDTO>> dayBookings = new TreeMap<>();

                for (Shift shift : shifts) {
                        ShiftDTO shiftDTO = new ShiftDTO(
                                s.getEmployeeName(),
                                s.getShiftStart(),
                                s.getShiftEnd(),
                                s.getDate());
                        dayShifts.computeIfAbsent(s.getDate(), d -> new ArrayList<>()).add(shiftDTO);
                }

                for (Booking booking : bookings) {
                        BookingDTO bookingDTO = new BookingDTO(
                                b.getGuestName(),
                                b.getDate(),
                                b.getStartTime(),
                                b.getBookingDuration(),
                                b.getEmail(),
                                b.getPhoneNumber());
                        dayBookings.ComputeIfAbsent(b.getDate), d -> new ArrayList<>()).add(bookingDTO);
                }

                List<DayScheduleDTO> daySchedules = new ArrayList<>();

                for (LocalDate currentDate = start; !currentDate.isAfter(end); currentDate = currentDate.plusDays(1)){
                        daySchedules.add(new DayScheduleDTO(
                                currentDate,
                                dayShifts.getOrDefault(currentDate, new ArrayList<>()),
                                dayBookings.getOrDefault(currentDate, new ArrayList<>())
                        ));
                }
                
                return daySchedules;
        }

        /*
         * public List<DayScheduleDTO> getDaySchedule(List<Shift> shifts){
         * 
         * 
         * Map<LocalDate, List<ShiftDTO>> groupedShifts = shifts.stream()
         * .collect(Collectors.groupingBy(
         * Shift::getDate,
         * Collectors.mapping(shift -> new ShiftDTO(shift.getEmployeeName(),
         * shift.getShiftStart(), shift.getShiftEnd(), shift.getDate()),
         * Collectors.toList())
         * ));
         * 
         * return groupedShifts.entrySet().stream()
         * .map(entry -> new DayScheduleDTO(entry.getKey(), entry.getValue()))
         * .collect(Collectors.toList());
         * }
         */

        public List<DayScheduleDTO> getWeekSchedule(LocalDate startOfWeek) {

                List<Shift> shifts = shiftRepository.findByDate(startOfWeek);

                LocalDate endOfWeek = startOfWeek.plusDays(6);

                List<Shift> weekShifts = new ArrayList<>();

                for (Shift s : shifts) {

                        LocalDate date = s.getDate();
                        if (!date.isBefore(startOfWeek) && !date.isAfter(endOfWeek)) {
                                weekShifts.add(s);
                        }
                }
                return getDaySchedule(weekShifts);

        }

        /*
         * public List<DayScheduleDTO> getWeekSchedule(
         * List<Shift> shifts, LocalDate startOfWeek) {
         * 
         * LocalDate endOfWeek = startOfWeek.plusDays(6);
         * 
         * 
         * List<Shift> weekShifts = shifts.stream()
         * .filter(shift -> !shift.getDate().isBefore(startOfWeek) &&
         * !shift.getDate().isAfter(endOfWeek))
         * .toList();
         * 
         * 
         * return getDaySchedule(weekShifts);
         * }
         */

        public List<DayScheduleDTO> getMonthSchedule(LocalDate date) {

                List<Shift> shifts = shiftRepository.findAll();

                LocalDate startOfMonth = date.withDayOfMonth(1);
                LocalDate endOfMonth = date.withDayOfMonth(date.lengthOfMonth());

                List<Shift> monthShifts = new ArrayList<>();

                for (Shift s : shifts) {
                        LocalDate shiftDate = s.getDate();
                        if (!shiftDate.isBefore(startOfMonth) && !shiftDate.isAfter(endOfMonth)) {
                                monthShifts.add(s);
                        }
                }
                return getDaySchedule(monthShifts);
        }

        /*
         * public List<DayScheduleDTO> getMonthSchedule(
         * List<Shift> shifts, LocalDate date) {
         * 
         * LocalDate startOfMonth = date.withDayOfMonth(1);
         * LocalDate endOfMonth = date.withDayOfMonth(date.lengthOfMonth());
         * 
         * List<Shift> monthShifts = shifts.stream()
         * .filter(shift ->
         * !shift.getDate().isBefore(startOfMonth)
         * && !shift.getDate().isAfter(endOfMonth))
         * .toList();
         * 
         * return getDaySchedule(monthShifts);
         * }
         */

}
