import java.time.DayOfWeek;
import java.io.*;

/**
 * Created by v_kal on 05.11.2016.
 */
public class Main {
    public static void main(String[] args) {

        Calendar cl=new Calendar(DayOfWeek.SATURDAY);
        cl.setHolidays(DayOfWeek.MONDAY,DayOfWeek.SUNDAY,DayOfWeek.THURSDAY);

        cl.createMonth();
        System.out.println(cl);

        FileWriter fw;
        try {
            fw = new FileWriter(new File("mytextfile.html"));
            fw.write(String.format(cl.toString()));
            fw.write(System.lineSeparator()); //new line
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

