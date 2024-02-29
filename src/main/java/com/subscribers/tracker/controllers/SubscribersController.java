package com.subscribers.tracker.controllers;

import com.subscribers.tracker.DTOs.AddChannelDTO;
import com.subscribers.tracker.DTOs.CreateSubscriberDTO;
import com.subscribers.tracker.DTOs.UpdateSubscriberDTO;
import com.subscribers.tracker.entities.Subscriber;
import com.subscribers.tracker.services.SubscriberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class SubscribersController {
    private final SubscriberService subscriberService;

    @GetMapping("/")
    public ResponseEntity<?> getAllSubscribers() {
        return ResponseEntity.ok(subscriberService.GetAllSubscribers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSubscriberById(@PathVariable Integer id) {
        Optional<Subscriber> existingSub = subscriberService.GetSubscriberById(id);
        if (existingSub.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(existingSub.get());
    }

    @PostMapping("/")
    public ResponseEntity<?> createSubscriber(@RequestBody CreateSubscriberDTO sub) {
        var newSub = subscriberService.CreateSubscriber(sub);
        return ResponseEntity.ok(newSub);
    }

    @PatchMapping("/{id}/channel")
    public ResponseEntity<?> addChannelToSubscriber(@PathVariable Integer id, @RequestBody AddChannelDTO channel) {
        var subscriber = subscriberService.AddChannelToUser(id, channel);
        if (subscriber.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(subscriber.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSubscriber(@PathVariable Integer id, @RequestBody UpdateSubscriberDTO sub) {
        var subscriber = subscriberService.UpdateSubscriber(id, sub);
        if (subscriber.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(subscriber.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubscriber(@PathVariable Integer id) {
        subscriberService.DeleteSubscriber(id);
        return ResponseEntity.noContent().build();
    }
}
