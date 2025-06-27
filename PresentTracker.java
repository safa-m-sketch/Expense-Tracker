import java.util.Scanner;
import java.util.ArrayList;
public class PresentTracker {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        ArrayList<Display> info = new ArrayList<>();
        int choice;
        do {
            System.out.println("1. Add Transaction");
            System.out.println("2. View Balance");
            System.out.println("3. View Transactions");
            System.out.println("4. Exit");
            choice = s.nextInt();
            s.nextLine();
            if (choice == 1) {
                System.out.print("Date (MM-DD-YYYY): ");
                String day = s.nextLine();

                System.out.print("Type (income/expense): ");
                String type = s.nextLine();

                System.out.print("Amount ($): ");
                double money = s.nextDouble();
                s.nextLine();

                System.out.print("Category (ex. food, clothing): ");
                String category = s.nextLine();

                info.add(new Display(type, category, money, day));
                System.out.println("Transaction added!");
            }
            else if (choice == 2){
                double curBalance = 0.0;
                for (Display d : info){
                    if (d.getType().equalsIgnoreCase("Income")) {
                        curBalance += d.getMoney();
                    } else if (d.getType().equalsIgnoreCase("Expense")) {
                        curBalance -= d.getMoney();
                    }
                }
                System.out.println("Current Balance: $" + curBalance);
            }
            else if (choice == 3){
                    for (Display d : info){
                        System.out.println(d.toString());
                    }
                }
            }while (choice != 4);
        System.out.println("Sucessfully exited");
        s.close();

    }
}
