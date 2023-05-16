package edu.uoc.epcsd.user;

import edu.uoc.epcsd.user.entities.Alert;
import edu.uoc.epcsd.user.entities.User;
import edu.uoc.epcsd.user.repositories.AlertRepository;
import edu.uoc.epcsd.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.text.SimpleDateFormat;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories
public class UserApplication implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AlertRepository alertRepository;

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Insert demo data
        try {
            var users = List.of(
                    new User(-1l, "name1", "email1@ya.com", "123", "600000", null),
                    new User(-1l, "name2", "email2@ya.com", "123", "700000", null),
                    new User(-1l, "name3", "email3@ya.com", "123", "800000", null),
                    new User(-1l, "name4", "email4@ya.com", "123", "900000", null)
            );
            for (var u : users) {
                userRepository.save(u);
            }

            var user1 = userRepository.getById(1l);
            var user2 = userRepository.getById(2l);

            var alerts = List.of(
                    new Alert(-1L,
                            new SimpleDateFormat("yyyy-MM-dd").parse("2023-04-30"),
                            new SimpleDateFormat("yyyy-MM-dd").parse("2023-08-01"),
                            "sony",
                            "psx",
                            user1),
                    new Alert(-1L,
                            new SimpleDateFormat("yyyy-MM-dd").parse("2023-04-30"),
                            new SimpleDateFormat("yyyy-MM-dd").parse("2023-09-25"),
                            "sony",
                            "psx",
                            user2),
                    new Alert(-1L,
                            new SimpleDateFormat("yyyy-MM-dd").parse("2023-04-01"),
                            new SimpleDateFormat("yyyy-MM-dd").parse("2023-10-11"),
                            "marca1",
                            "modelo2",
                            user1),
                    new Alert(-1L,
                            new SimpleDateFormat("yyyy-MM-dd").parse("2024-01-01"),
                            new SimpleDateFormat("yyyy-MM-dd").parse("2024-02-30"),
                            "marca1",
                            "modelo2",
                            user1)
            );
            for (var alert : alerts) {
                alertRepository.save(alert);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
