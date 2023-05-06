package edu.uoc.epcsd.user.repositories;

import edu.uoc.epcsd.user.entities.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AlertRepository extends JpaRepository<Alert, Long> {

}

