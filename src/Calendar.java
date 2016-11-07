import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by v_kal on 06.11.2016.
 */
public class Calendar {
    private ArrayList<DayOfWeek> week=new ArrayList<>();
    private ArrayList<DayOfWeek> holiday=new ArrayList<>();
    private LocalDate firstDayOfMonth;
    private LocalDate userdate;
    private Printer printer;
    //=================================================
    public Calendar(DayOfWeek firstDayOfWeek) {
        userdate = LocalDate.now();
        firstDayOfMonth = userdate.withDayOfMonth(1);

        setFirstDayOfWeek(firstDayOfWeek);
        printer =new PrintHTML(week);              //  тип вывода
    }
    public Calendar(String yourDate,DayOfWeek firstDayOfWeek) {
        this.userdate = LocalDate.parse(yourDate);
        firstDayOfMonth = userdate.withDayOfMonth(1);

        setFirstDayOfWeek(firstDayOfWeek);
        printer =new PrintHTML(week);
    }
    //=================================================
    public void createMonth(){
        while (isCurrentMonth()){
            createWeek();
        }
    }
    private void createWeek(){
        printer.nextWeek();

        for(DayOfWeek day: week){
            if(notSameDay(day))
                continue;

            if(isToday()) {
                printer.dayColor(firstDayOfMonth.getDayOfMonth(), ColorDays.CURRENT_DAY_COLOR);
                nextDay();
                if(isCurrentMonth())
                    continue;
                else break;
            }

            if(isHoliday()){
                printer.dayColor(firstDayOfMonth.getDayOfMonth(),ColorDays.WEEKEND_COLOR);
                nextDay();
                if(isCurrentMonth())
                    continue;
                else break;
            }

            if(true){
                printer.dayColor(firstDayOfMonth.getDayOfMonth(),ColorDays.JUST_DAY);
                nextDay();
                if(isCurrentMonth())
                    continue;
                else break;
            }
        }

        if(printer instanceof PrintHTML)
            ((PrintHTML) printer).endWeekHtml();
    }

    //==================================================
    private boolean notSameDay(DayOfWeek day) {
        if (day != firstDayOfMonth.getDayOfWeek()) {
            printer.ignorDay();
            return true;
        } else return false;
    }
    private boolean isHoliday() {
        for (DayOfWeek d : holiday)
            if (d == firstDayOfMonth.getDayOfWeek())
                return true;
        return false;
    }
    private boolean isToday(){
        if(firstDayOfMonth.equals(userdate))
            return true;
        else return false;
    }
    private boolean isCurrentMonth(){
        if(firstDayOfMonth.getMonth()==userdate.getMonth())
            return true;
        else return false;
    }
    private void nextDay() {
        firstDayOfMonth = firstDayOfMonth.plusDays(1);
    }
    //==================================================
    private void setFirstDayOfWeek(DayOfWeek firstDayOfWeek) {
        for (DayOfWeek d : DayOfWeek.values())
            if (d.getValue() >= firstDayOfWeek.getValue())
                week.add(d);

        for (DayOfWeek d : DayOfWeek.values())
            if (d.getValue() < firstDayOfWeek.getValue())
                week.add(d);
    }
    public void setHolidays(DayOfWeek... days) {
        for (DayOfWeek d : days) {
            holiday.add(d);
        }
    }
    //================================================
    @Override
    public String toString(){
        return printer.toString();
    }
}
