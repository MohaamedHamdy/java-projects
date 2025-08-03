import Models.User;
import Service.Authentication;
import Service.Database;
import Views.AuthenticationView;

import java.util.Scanner;

public class Main {
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to LMS");

        boolean isRun = true;

        Database database = new Database();
        Authentication authentication = new Authentication(database);
        User user;
        AuthenticationView authenticationView = new AuthenticationView(authentication);


        while (isRun) {
            int choice = -1;
            System.out.println("\n--Main menu--");
            System.out.println("1-Login\n2-Signup\n3-Exit");
            choice = getChoice(choice);
            switch (choice) {
                case 1:
                    System.out.println("login");
                    break;
                case 2:
                    user = authenticationView.signUp();
                    System.out.println(user);
                    break;
                case 3:
                    isRun = false;
                    break;
                default:
                    System.out.println("Error");
            }
        }

    }

    public static int getChoice(int choice) {
        while (true) {
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