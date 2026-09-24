package adventure.estera.adventurexp.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
@Entity
public class WorkSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int employee_Id;
    private int shift_Id;
    private LocalDate date;
    private List<T> dayPlan;
    @Enumerated(EnumType.STRING)
    private AttendanceState attendanceState;

    public WorkSchedule(){}

    public WorkSchedule(int id, int employee_Id, int shift_Id, LocalDate date,
                        List<T> dayPlan,AttendanceState attendanceState) {
        this.id = id;
        this.employee_Id = employee_Id;
        this.shift_Id = shift_Id;
        this.date = date;
        this.dayPlan = dayPlan;
    }
   public enum AttendanceState {
        ACTIVE,
        SICK,
        VACATION,
        NO_SHOW
    }
}

