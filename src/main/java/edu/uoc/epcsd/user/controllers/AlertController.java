package edu.uoc.epcsd.user.controllers;

import edu.uoc.epcsd.user.entities.Alert;
import edu.uoc.epcsd.user.repositories.AlertRepository;
import edu.uoc.epcsd.user.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/alert")
public class AlertController {
    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Alert> getAllAlerts() {
        log.trace("getAllAlerts");
        return alertRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Alert> getAlertById(@PathVariable("id") Long id) {
        log.trace("getAlertById");
        return alertRepository.findById(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAlert(@RequestBody @Valid Alert alert) {
        log.trace("createAlert");
        alert.setUser(userRepository.getById(1L));
        alertRepository.save(alert);
    }

}
