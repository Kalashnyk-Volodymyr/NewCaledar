import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by v_kal on 06.11.2016.
 */
public class PrintHTML extends Printer {
    private String calendar=""+getHmlBegin();

    @Override
    public void setWeekStart(ArrayList<DayOfWeek> weekStart) {
        calendar = calendar + "<tr>\n";
        for (DayOfWeek d : weekStart)
            calendar = calendar + "<th>" +
                    d.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + "</th>\n";
        calendar = calendar + "</tr>\n";
    }
    @Override
    public void printDay(int day, ColorDays color) {
        calendar = calendar + color.getColorHTML() + day + "</td>" + "\n";
    }
    @Override
    public void printDay(){
        calendar=calendar+"<td></td>";
    }
    @Override
    public void nextWeek() {
        calendar = calendar + "<tr>";
    }

    @Override
    public void endWeek() { calendar = calendar + "</tr>"; }

    private String getHmlBegin() {
        String beginHtml = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<style>\n" +
                ".CurrentDay {\n" +
                "    color: red;\n" +
                "  } \n" +
                ".Weekend {\n" +
                "    color: blue;\n" +
                "  } \n" +
                ".Just_day {\n" +
                "    color: black;\n" +
                "  }\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<table>\n";

        return beginHtml;
    }

    public String getHtmlEnd() {
        String endHtml = "</table>\n" +
                "</body>\n" +
                "</html>";
        return endHtml;
    }

    @Override
    public String toString() {
        return calendar + "\n" + getHtmlEnd();
    }
}