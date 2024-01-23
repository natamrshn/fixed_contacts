package spring.boot.optic.okulist;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderExample {
    public static void main(String[] args) {
        String mainAdmin = "mainAdminPassword14";
        String admin = "regularAdminPassword12";
        String user = "userPsdav2349(";

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPasswordMA = passwordEncoder.encode(mainAdmin);
        String hashedPasswordA = passwordEncoder.encode(admin);
        String hashedPasswordUS = passwordEncoder.encode(user);

        System.out.println("Original MA Password: " + mainAdmin);
        System.out.println("Original A Password: " + admin);
        System.out.println("Original U Password: " + user);
        System.out.println("Hashed MA Password: " + hashedPasswordMA);
        System.out.println("Hashed A Password: " + hashedPasswordA);
        System.out.println("Hashed U Password: " + hashedPasswordUS);
    }
}
