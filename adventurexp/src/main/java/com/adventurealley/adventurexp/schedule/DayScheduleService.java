package com.adventurealley.adventurexp.schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import com.adventurealley.adventurexp.booking.Booking;
import com.adventurealley.adventurexp.booking.BookingDTO;
import com.adventurealley.adventurexp.booking.BookingRepository;
import com.adventurealley.adventurexp.reservation.*;

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

                for (Shift s : shifts) {
                        ShiftDTO shiftDTO = new ShiftDTO(
                                s.getEmployeeName(),
                                s.getDate(),
                                s.getShiftStart().getHour(),
                                s.getShiftEnd().getHour(),
                                s.getEmployee() != null ? s.getEmployee().getEmail() : "",
                                s.getEmployee() != null ? s.getEmployee().getPhoneNumber() : ""
                        );
                        dayShifts.computeIfAbsent(s.getDate(), d -> new ArrayList<>()).add(shiftDTO);
                }

                for (Booking b : bookings) {
                        BookingDTO bookingDTO = new BookingDTO(
                                b.getCustomerName(),
                                b.getStartTime().toLocalDate(),
                                b.getStartTime().getHour(),
                                b.getHourCount(),
                                b.getCustomerEmail(),
                                b.getCustomerPhoneNumber());
                        dayBookings.computeIfAbsent(b.getStartTime().toLocalDate(), d -> new ArrayList<>()).add(bookingDTO);
                }

                List<DayScheduleDTO> daySchedules = new ArrayList<>();

                for (LocalDate currentDate = startDate; !currentDate.isAfter(endDate); currentDate = currentDate.plusDays(1)){
                        daySchedules.add(new DayScheduleDTO(
                                currentDate,
                                dayShifts.getOrDefault(currentDate, new ArrayList<>()),
                                dayBookings.getOrDefault(currentDate, new ArrayList<>())
                        ));
                }

                return daySchedules;
        }

        public List<DayScheduleDTO> getWeekSchedule(LocalDate startOfWeek) {
                LocalDate endOfWeek = startOfWeek.plusDays(6);

                LocalDateTime startDateTime = startOfWeek.atStartOfDay();
                LocalDateTime endDateTime = endOfWeek.atTime(java.time.LocalTime.MAX);

                return getPeriodSchedule(
                                startOfWeek,
                                endOfWeek,
                                shiftRepository.findByDateBetween(startOfWeek, endOfWeek),
                                bookingRepository.findByStartTimeBetween(startDateTime, endDateTime));
        }

        public List<DayScheduleDTO> getMonthSchedule(LocalDate date) {

                LocalDate startOfMonth = date.withDayOfMonth(1);
                LocalDate endOfMonth = date.withDayOfMonth(date.lengthOfMonth());

                LocalDateTime startDateTime = startOfMonth.atStartOfDay();
                LocalDateTime endDateTime = endOfMonth.atTime(java.time.LocalTime.MAX);
                return getPeriodSchedule(
                                startOfMonth,
                                endOfMonth,
                                shiftRepository.findByDateBetween(startOfMonth, endOfMonth),
                                bookingRepository.findByStartTimeBetween(startDateTime, endDateTime));
        }
}
