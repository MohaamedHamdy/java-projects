package Service;

import Models.Admin;
import Models.Student;
import Models.User;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Authentication {
    private final Set<String> emails = new HashSet<>();
    private Database database = new Database();

    public User signUp(String name, String email, String password) {
        if (!emails.add(email)) {
            System.out.println("Sorry Email must be unique, Try again later");
            return null;
        }
        System.out.println("1- Admin\n2- Student");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        User user = switch (choice) {
            case 1 -> new Admin(name, email, password);
            case 2 -> new Student(name, email, password);
            default -> null;
        };
        database.addUser(user);

        return user;
    }
}
