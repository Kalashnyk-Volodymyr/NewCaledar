import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by v_kal on 06.11.2016.
 */
public abstract class Printer {
    protected String calendar = "";
    protected WayOfPrint wayOfPrint;
    public Printer(ArrayList<DayOfWeek> week) {
        setWeekFromFirstDay(week);
    }

    // Sat Sun ... Fri
    protected abstract void setWeekFromFirstDay(ArrayList<DayOfWeek> week);

    public abstract void printDay(int day, ColorDays color);

    public abstract void nextWeek();

    public abstract void ignoreDay();

    @Override
    public String toString() {
        return calendar;
    }
}
