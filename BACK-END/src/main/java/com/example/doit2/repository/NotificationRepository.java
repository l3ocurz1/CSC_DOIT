package com.example.doit2.repository;

import com.example.doit2.model.Notification;
import com.example.doit2.model.Progettista;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Integer> {

    Optional<Notification> findByReceiver(String username);

}
