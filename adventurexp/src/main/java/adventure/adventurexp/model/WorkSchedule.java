package adventure.adventurexp.model;

import jakarta.persistence.*;

import java.time.LocalDate;
// import java.util.List;
@Entity
public class WorkSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int employeeId;
    private int shiftId;
    private LocalDate date;
    //private List<T> dayPlan;
    @Enumerated(EnumType.STRING)
    private AttendanceState attendanceState;

    public WorkSchedule(){}

    public WorkSchedule(int id, int employeeId, int shiftId, LocalDate date,
                        AttendanceState attendanceState) {
        this.id = id;
        this.employeeId = employeeId;
        this.shiftId = shiftId;
        this.date = date;
       // this.dayPlan = dayPlan;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

   public enum AttendanceState {
        ACTIVE,
        SICK,
        VACATION,
        NO_SHOW
    }
}

