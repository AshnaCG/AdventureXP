package adventure.adventurexp.model;

public class Employee {
    private int id;
    private AttendanceState attendanceState;
    private String name;

public enum AttendanceState {
    PRESENT,
    ABSENT,
    ON_LEAVE
}
}
