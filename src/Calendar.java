import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 * Created by v_kal on 06.11.2016.
 */
public class Calendar {
    private ArrayList<DayOfWeek> weekStart =new ArrayList<>();
    private ArrayList<DayOfWeek> weekend =new ArrayList<>();
    private LocalDate firstDayOfMonth;
    private LocalDate toDay;
    private Printer printer;
    private WayOfPrint wayOfPrint;

    public Calendar(DayOfWeek firstDayOfWeek, WayOfPrint wayOfPrint) {
        toDay = LocalDate.now();
        firstDayOfMonth = toDay.withDayOfMonth(1);

        setWeekStart(firstDayOfWeek);
        this.wayOfPrint = wayOfPrint;

        howToPrint();
    }

    public Calendar(DayOfWeek firstDayOfWeek,WayOfPrint wayOfPrint,String yourDate) {
        this.toDay = LocalDate.parse(yourDate);
        firstDayOfMonth = toDay.withDayOfMonth(1);

        setWeekStart(firstDayOfWeek);
        this.wayOfPrint = wayOfPrint;

        howToPrint();
    }
    private void howToPrint() {
        if (wayOfPrint == WayOfPrint.HTML)
            printer = new PrintConsole(weekStart);
        else
            printer = new PrintHTML(weekStart);
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

    public WayOfPrint getWayOfPrint(){
        return wayOfPrint;
    }

    public void print(){
        while (isCurrentMonth()){
            printWeek();
        }
    }

    private void printWeek() {
        printer.nextWeek();

        for (DayOfWeek day : weekStart)
            if (avoidDayOfAnotherMonth(day))
                continue;
            else {
            printDayWithColor(getColor());
            plusDay();
            if (isCurrentMonth()) continue;
                else break;
        }

        if (printer instanceof PrintHTML)
            ((PrintHTML) printer).endWeekHtml();
    }

    private void printDayWithColor(ColorDays colorDays){
        printer.printDay(firstDayOfMonth.getDayOfMonth(),colorDays);
    }

    private ColorDays getColor() {
        if (isToday()) return ColorDays.CURRENT_DAY_COLOR;
        if (isWeekend()) return ColorDays.WEEKEND_COLOR;
        return ColorDays.JUST_DAY;
    }

    private boolean avoidDayOfAnotherMonth(DayOfWeek day) {
        if (day != firstDayOfMonth.getDayOfWeek()) {
            printer.ignoreDay();
            return true;
        } else return false;
    }

    private boolean isWeekend() {
        for (DayOfWeek d : weekend)
            if (d == firstDayOfMonth.getDayOfWeek())
                return true;
        return false;
    }

    private boolean isToday(){
        if(firstDayOfMonth.equals(toDay))
            return true;
        else return false;
    }

    private boolean isCurrentMonth() {
        if (firstDayOfMonth.getMonth() == toDay.getMonth())
            return true;
        else return false;
    }

    private void plusDay() {
        firstDayOfMonth = firstDayOfMonth.plusDays(1);
    }

    @Override
    public String toString(){
        return printer.toString();
    }
}
