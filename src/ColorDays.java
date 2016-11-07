/**
 * Created by v_kal on 06.11.2016.
 */
public enum ColorDays {
    CURRENT_DAY_COLOR("\033[31m","<td class=\"Red\">"),
    WEEKEND_COLOR("\033[34m","<td class=\"Blue\">"),
    RESET_COLOR("\033[0m","<td class=\"Black\">"),
    JUST_DAY("\033[0m","<td class=\"Black\">");

    private String colorConsole;
    private String colorHTML;
    private ColorDays(String colorConsole, String colorHTML){
        this.colorConsole=colorConsole;
        this.colorHTML=colorHTML;
    }
    public String getColorConsole(){
        return colorConsole;
    }
    public String getColorHTML()
    {
        return colorHTML;
    }
}
