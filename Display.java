//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Display
{
    String type;
    String category;
    double money;
    String day;
    //default constructor
        public Display()
        {
            type = "";
            category = "";
            money = 0.0;
            day = "";
        }
        //parameter constructor
        public Display(String t, String c, double m, String d)
        {
            type = t;
            category = c;
            money = m;
            day = d;
        }
        //return methods
        public double getMoney(){
            return money;
        }
        public String getType(){
            return type;
        }
        public String toString(){
            //if there is no category don't use parenthesis
            if (category.equals("")){
                return day + " - " + type + "   $ " + String.format("%.2f",money);
            }
            return day + " - " + type + "   $ " + String.format("%.2f",money) + " (" + category + ")";
        }
}