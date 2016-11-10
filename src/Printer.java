import java.time.DayOfWeek;
import java.util.ArrayList;
/**
 * Created by v_kal on 06.11.2016.
 */
public abstract class Printer {
    protected  String calendar="";
    public abstract void setWeekStart(ArrayList<DayOfWeek> weekStart);

    public abstract void printDay(int day, ColorDays color);

    public abstract void printDay();

    public abstract void nextWeek();

    public abstract void endWeek();

    public  String toString() {return calendar;}

}
