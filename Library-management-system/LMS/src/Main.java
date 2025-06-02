import Models.Admin;
import Models.Student;
import Models.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        User admin = new Admin("Mohamd", "mhmd@gmail.com", "123456");
//        System.out.println(admin);
//        User admin2 = new Admin("Mohamd", "mhmd@gmail.com", "123456");
//        System.out.println(admin2);
//        User admin3 = new Admin("Mohamd", "mhmd@gmail.com", "123456");
//        System.out.println(admin3);
//        User student = new Student("Mohamd", "mhmd@gmail.com", "123456");
//        System.out.println(student);
//        User student2 = new Student("Mohamd", "mhmd@gmail.com", "123456");
//        System.out.println(student);
        System.out.println("Welcome to LMS");
        int choice;
        do {
            System.out.println("--Main menu--");
            System.out.println("1-Login\n2-Signup\n");
            try {
                Scanner in = new Scanner(System.in);
                choice = in.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("u must enter number");
            }
        }while (true);

        switch (choice){
            case 1 -> System.out.println("login");
            case 2 -> System.out.println("Signup");
            default -> System.out.println("Error");
        }

    }
}