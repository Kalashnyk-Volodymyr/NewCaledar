import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by v_kal on 06.11.2016.
 */
public class PrintHTML extends Printer {
    public PrintHTML(ArrayList<DayOfWeek> weekStart) {
        super(weekStart);
        calendar = getHmlBegin();
        setWeekStart(weekStart);
    }

    protected void setWeekStart(ArrayList<DayOfWeek> weekStart) {
        calendar = calendar + "<tr>\n";
        for (DayOfWeek d : weekStart)
            calendar = calendar + "<th>" +
                    d.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + "</th>\n";
        calendar = calendar + "</tr>\n";
    }

    public void printDay(int day, ColorDays color) {
        calendar = calendar + color.getColorHTML() + day + "</td>" + "\n";
    }

    public void nextWeek() {
        calendar = calendar + "<tr>";
    }

    public void endWeekHtml() { calendar = calendar + "</tr>"; }

    public void ignoreDay() {
        calendar = calendar + "<td></td>";
    }

    //=====================================================
    private String getHmlBegin() {
        String beginHtml = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<style>\n" +
                ".Red {\n" +
                "    color: red;\n" +
                "  } \n" +
                ".Blue {\n" +
                "    color: blue;\n" +
                "  } \n" +
                ".Black {\n" +
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

    //======================================
    @Override
    public String toString() {
        return calendar + "\n" + getHtmlEnd();
    }
}
