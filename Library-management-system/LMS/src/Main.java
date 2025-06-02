import Models.User;
import Service.Authentication;
import Service.Database;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Database database = new Database();
        Authentication authentication = new Authentication();
        User user = null;

        while (true){
            System.out.println("Welcome to LMS");
            int choice = -1;
            System.out.println("--Main menu--");
            System.out.println("1-Login\n2-Signup\n3-Exit");
            choice = getChoice(choice);
            switch (choice){
                case 1 -> System.out.println("login");
                case 2 -> user = authentication.signUp("mohamed", "mhmd@gmail.com", "123");
                case 3 -> {
                    break;
                }
                default -> System.out.println("Error");
            }
        }

    }

    public static int getChoice(int choice){
        while (true){
            Scanner in = new Scanner(System.in);
            try {
                choice = in.nextInt();
                return choice;
            } catch (Exception e) {
                System.out.println("Input must be number");
                in.nextLine();
            }
        }
    }
}