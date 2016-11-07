import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by v_kal on 06.11.2016.
 */
public class PrintConsole extends Printer {
    public PrintConsole(ArrayList<DayOfWeek> week){
        super(week);
    }

    private void setNameOfDays(ArrayList<DayOfWeek> week) {
        for (DayOfWeek d : week)
            calendar = calendar + d.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)+"\t";
    }
    public void dayColor(int day, ColorDays color) {
        calendar = calendar + color.getColorConsole() + day + ColorDays.RESET_COLOR.getColorConsole()+"\t";
    }

    public void nextWeek(){calendar=calendar+"\n"; }
    public void ignorDay(){
        calendar=calendar+"\t";
    }
    @Override
    public String toString(){
        return calendar;
    }
}
