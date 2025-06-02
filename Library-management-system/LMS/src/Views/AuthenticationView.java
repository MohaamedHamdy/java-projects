package Views;

import Models.User;
import Service.Authentication;

import java.util.Scanner;

public class AuthenticationView {
    Authentication auth;
    private static Scanner in = new Scanner(System.in);

    public AuthenticationView(Authentication auth) {
        this.auth = auth;
    }

    public User signUp() {
        System.out.print("Enter name: ");
        String name = in.next();
        System.out.print("Enter email: ");
        String email = in.next();
        System.out.print("Enter password: ");
        String password = in.next();

        return auth.signUp(name, email, password);
    }
}
