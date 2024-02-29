package com.subscribers.tracker.respository;

import com.subscribers.tracker.entities.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscribersRepository extends JpaRepository<Subscriber, Integer> {
}
