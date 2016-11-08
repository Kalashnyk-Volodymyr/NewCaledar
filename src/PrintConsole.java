import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by v_kal on 06.11.2016.
 */
public class PrintConsole extends Printer {
    private String calendar="";
    //public String getCalendar(){return  calendar;}

    @Override
    public void setWeekStart(ArrayList<DayOfWeek> weekStart) {
        for (DayOfWeek d : weekStart)
            calendar = calendar + d.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + "\t";
    }
    @Override
    public void printDay(int day, ColorDays color) {
        calendar = calendar + color.getColorConsole() + day + ColorDays.RESET_COLOR.getColorConsole() + "\t";
    }
    @Override
    public void nextWeek() {
        calendar = calendar + "\n";
    }
    @Override
    public void ignoreDay() {
        calendar = calendar + "\t";
    }

    @Override
    public String toString() {
        return calendar;
    }
}
