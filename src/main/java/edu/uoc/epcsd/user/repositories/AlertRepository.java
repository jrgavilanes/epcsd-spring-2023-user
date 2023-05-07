package edu.uoc.epcsd.user.repositories;

import edu.uoc.epcsd.user.entities.Alert;
import edu.uoc.epcsd.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Date;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    @Query("select a from Alert a where user_id=:userId and starts=:fromDate and ends=:toDate")
    Collection<Alert> getAlertsByUserAndFromTo(@Param("userId") Long userId,
                                               @Param("fromDate") Date fromDate,
                                               @Param("toDate") Date toDate);

    @Query("select a from Alert a where model=:model and starts<=:date and ends>=:date")
    Collection<Alert> getAlertsByModelAndDate(@Param("model") String model,
                                              @Param("date") Date date);


    @Query("select distinct a.user from Alert a where model=:model and starts<=:date and ends>=:date")
    Collection<User> getUsersToAlert(@Param("model") String model,
                                     @Param("date") Date date);


}

