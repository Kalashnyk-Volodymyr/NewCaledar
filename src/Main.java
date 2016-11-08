import java.time.DayOfWeek;
import java.io.*;
import java.util.Scanner;

/**
 * Created by v_kal on 05.11.2016.
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String yourDate = sc.nextLine();

        Calendar cl=createCalendar(yourDate);
        cl.setHolidays(DayOfWeek.MONDAY,DayOfWeek.SUNDAY,DayOfWeek.THURSDAY);

        cl.print();

        writeIntoHTMLFile(cl);
        System.out.println(cl);
    }

    public static Calendar createCalendar(String yourDate){
        Calendar cl;

        if(yourDate.length() == 0)
            cl=new Calendar(DayOfWeek.SATURDAY,WayOfPrint.CONSOLE);
        else
            cl=new Calendar(DayOfWeek.SATURDAY ,WayOfPrint.CONSOLE,yourDate);

        return cl;
    }

    public static void writeIntoHTMLFile(Calendar cl){
        if(cl.getWayOfPrint()==WayOfPrint.HTML) {
            FileWriter fw;
            try {
                fw = new FileWriter(new File("mytextfile.html"));
                fw.write(String.format(cl.toString()));
                fw.write(System.lineSeparator());
                fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}

