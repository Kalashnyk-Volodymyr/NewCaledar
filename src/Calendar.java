import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by v_kal on 06.11.2016.
 */
public class Calendar {
    private ArrayList<DayOfWeek> weekStart = new ArrayList<>();
    private ArrayList<DayOfWeek> weekend = new ArrayList<>();
    private LocalDate firstDayOfMonth;
    private LocalDate toDay;
    private Printer printer;


    public Calendar(DayOfWeek firstDayOfWeek) {
        toDay = LocalDate.now();
        firstDayOfMonth = toDay.withDayOfMonth(1);
        setWeekStart(firstDayOfWeek);
    }

    public Calendar(DayOfWeek firstDayOfWeek, String yourDate) {
        this.toDay = LocalDate.parse(yourDate);
        firstDayOfMonth = toDay.withDayOfMonth(1);
        setWeekStart(firstDayOfWeek);
    }

    private void setWeekStart(DayOfWeek firstDayOfWeek) {
        for (DayOfWeek d : DayOfWeek.values())
            if (d.getValue() >= firstDayOfWeek.getValue())
                weekStart.add(d);

        for (DayOfWeek d : DayOfWeek.values())
            if (d.getValue() < firstDayOfWeek.getValue())
                weekStart.add(d);
    }

    public void setHolidays(DayOfWeek... days) {
        for (DayOfWeek d : days) {
            weekend.add(d);
        }
    }

    // =======================================================
    private void howToPrint(WayOfPrint wayOfPrint) {
        if (wayOfPrint == WayOfPrint.HTML) {
            printer = new PrintHTML();
            printer.setWeekStart(weekStart);
        } else {
            printer = new PrintConsole();
            printer.setWeekStart(weekStart);
        }
    }

    public WayOfPrint getWayPrint() {
        if (printer instanceof PrintConsole)
            return WayOfPrint.CONSOLE;
        else
            return WayOfPrint.HTML;
    }


    public void print(WayOfPrint wayOfPrint) {

        howToPrint(wayOfPrint);

        while (CurrentMonth()) {
            printWeek();
        }
    }

    // ======================================================
    private void printWeek() {
        printer.nextWeek();

        for (DayOfWeek day : weekStart)
            if (avoidDayOfAnotherMonth(day)) {
                printer.printDay();
                continue;
            } else {
                printDayWithColor(getColor());
                plusDay();
                if (CurrentMonth()) continue;
                else break;
            }

        printer.endWeek();
    }

    private boolean avoidDayOfAnotherMonth(DayOfWeek day) {
        return day != firstDayOfMonth.getDayOfWeek();
    }

    private void printDayWithColor(ColorDays colorDays) {
        printer.printDay(firstDayOfMonth.getDayOfMonth(), colorDays);
    }

    private ColorDays getColor() {
        if (Today()) return ColorDays.CURRENT_DAY_COLOR;
        if (Weekend()) return ColorDays.WEEKEND_COLOR;
        return ColorDays.JUST_DAY;
    }

    private boolean Weekend() {
        return weekend.contains(firstDayOfMonth.getDayOfWeek());
    }

    private boolean Today() {
        return firstDayOfMonth.equals(toDay);
    }

    private boolean CurrentMonth() {
        return firstDayOfMonth.getMonth() == toDay.getMonth();
    }

    private void plusDay() {
        firstDayOfMonth = firstDayOfMonth.plusDays(1);
    }

    @Override
    public String toString() {
        return printer.toString();
    }
}
