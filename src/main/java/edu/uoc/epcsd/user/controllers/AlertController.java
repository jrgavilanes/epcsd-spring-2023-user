package edu.uoc.epcsd.user.controllers;

import edu.uoc.epcsd.user.entities.Alert;
import edu.uoc.epcsd.user.entities.User;
import edu.uoc.epcsd.user.repositories.AlertRepository;
import edu.uoc.epcsd.user.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
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

    @GetMapping("")
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

    @PostMapping("/{user_id}/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAlert(@RequestBody @Valid Alert alert, @PathVariable("user_id") Long id) {
        log.trace("createAlert");
        alert.setUser(userRepository.getById(id));
        alertRepository.save(alert);
    }

    @GetMapping("/{user_id}/{from_date}/{to_date}")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Alert> getAlertsByUserFromTo(@PathVariable("user_id") Long userId,
                                                   @PathVariable("from_date") String fromDate,
                                                   @PathVariable("to_date") String toDate) throws ParseException {
        log.trace("getAlertsByUserFromTo");
        Date from = new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
        Date to = new SimpleDateFormat("yyyy-MM-dd").parse(toDate);

        return alertRepository.getAlertsByUserAndFromTo(userId, from, to);
    }

    @GetMapping("/{model}/{date}")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Alert> getAlertsByModelAndDate(@PathVariable("model") String model,
                                                     @PathVariable("date") String date) throws ParseException {
        log.trace("getAlertsByModelAndDate");
        Date d = new SimpleDateFormat("yyyy-MM-dd").parse(date);


        return alertRepository.getAlertsByModelAndDate(model, d);
    }

    @GetMapping("/{model}/{date}/users")
    @ResponseStatus(HttpStatus.OK)
    public Collection<User> getUsersToAlert(@PathVariable("model") String model,
                                            @PathVariable("date") String date) throws ParseException {
        log.trace("getUsersToAlert");
        Date d = new SimpleDateFormat("yyyy-MM-dd").parse(date);


        return alertRepository.getUsersToAlert(model, d);
    }

}
