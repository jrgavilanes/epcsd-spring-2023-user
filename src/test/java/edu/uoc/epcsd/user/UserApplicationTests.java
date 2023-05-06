package edu.uoc.epcsd.user;

import edu.uoc.epcsd.user.repositories.AlertRepository;
import edu.uoc.epcsd.user.repositories.UserRepository;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {


//        var x = userRepository.findAllWithAlerts();
        var x = userRepository.findAll();
        System.out.println(x);
        assertEquals(1, 1);
    }

}
