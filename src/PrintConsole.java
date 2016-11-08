import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by v_kal on 06.11.2016.
 */
public class PrintConsole extends Printer {
    public PrintConsole(ArrayList<DayOfWeek> week) {
        super(week);

        wayOfPrint=WayOfPrint.CONSOLE;
    }

    protected void setWeekFromFirstDay(ArrayList<DayOfWeek> week) {
        for (DayOfWeek d : week)
            calendar = calendar + d.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + "\t";
    }

    public void printDay(int day, ColorDays color) {
        calendar = calendar + color.getColorConsole() + day + ColorDays.RESET_COLOR.getColorConsole() + "\t";
    }

    public void nextWeek() {
        calendar = calendar + "\n";
    }

    public void ignoreDay() {
        calendar = calendar + "\t";
    }

    @Override
    public String toString() {
        return calendar;
    }
}
