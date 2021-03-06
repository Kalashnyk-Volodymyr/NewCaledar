import java.awt.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.DayOfWeek;
import java.io.*;
import java.util.Scanner;

/**
 * Created by v_kal on 05.11.2016.
 */
public class Main {
    public static void main(String[] args) throws IOException {
      /*  Scanner sc=new Scanner(System.in);
        System.out.print("write the date:\n");
        String yourDate = sc.nextLine();
        Calendar cl=createCalendar(yourDate);
        cl.setHolidays(DayOfWeek.MONDAY,DayOfWeek.SUNDAY,DayOfWeek.THURSDAY);
        cl.print(choseWayOfPrint());
        if(cl.getWayPrint()==WayOfPrint.HTML)
            writeIntoHTMLFile(cl);*/
      Calendar cl=new Calendar(DayOfWeek.SATURDAY);
        cl.setHolidays(DayOfWeek.MONDAY);
        cl.print(WayOfPrint.HTML);

        System.out.println(cl);

        //================================================
        int requestNumber = 0;
        ServerSocket server = new ServerSocket(3575);
        System.out.println(server.getChannel());
        System.out.println("Listening for connection on port 3575 ....");
        while (true) {
            try (Socket socket = server.accept()){
                requestNumber++;
                String httpResponse = ""+"Hello world! Times: " + requestNumber;
                 httpResponse = ""+cl;
                socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
            }
        }
        //================================================
    }

    public static String myform(){
        return "";
    }

    public static WayOfPrint choseWayOfPrint(){
        Scanner sc=new Scanner(System.in);
        System.out.println("chose the way of print: \nConsole\t1\nHTML\t2");
        int a=sc.nextInt();
        if(a==1) return WayOfPrint.CONSOLE;
        else return WayOfPrint.HTML;
    }
    public static Calendar createCalendar(String yourDate){
        Calendar cl;

        if(yourDate.length() == 0)
            cl=new Calendar(DayOfWeek.SATURDAY);
        else
            cl=new Calendar(DayOfWeek.SATURDAY, yourDate);

        return cl;
    }

    public static void writeIntoHTMLFile(Calendar cl) {
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

