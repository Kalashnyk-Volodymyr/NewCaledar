import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by v_kal on 06.11.2016.
 */
public abstract class Printer {
    protected    String calendar="";
    public Printer(ArrayList<DayOfWeek> week){
        setNameOfDays(week);
    }
    // Sat Sun ... Fri
    private  void setNameOfDays(ArrayList<DayOfWeek> week) {
        for (DayOfWeek d : week)
            calendar = calendar + d.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)+"\t";
    }
    public abstract void dayColor(int day, ColorDays color);
    public abstract void nextWeek();
    public abstract void ignorDay();
    @Override
    public String toString(){
        return calendar;
    }
}
