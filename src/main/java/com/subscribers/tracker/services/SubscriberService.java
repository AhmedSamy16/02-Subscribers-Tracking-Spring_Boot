package com.subscribers.tracker.services;

import com.subscribers.tracker.DTOs.AddChannelDTO;
import com.subscribers.tracker.DTOs.CreateSubscriberDTO;
import com.subscribers.tracker.DTOs.UpdateSubscriberDTO;
import com.subscribers.tracker.entities.Subscriber;
import com.subscribers.tracker.respository.SubscribersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscriberService {
    private final SubscribersRepository subscribersRepository;

    public List<Subscriber> GetAllSubscribers() {
        return subscribersRepository.findAll();
    }

    public Optional<Subscriber> GetSubscriberById(Integer id) {
        return subscribersRepository.findById(id);
    }

    public Subscriber CreateSubscriber(CreateSubscriberDTO sub) {
        var newSub = new Subscriber();
        newSub.setName(sub.getName());
        newSub.setChannels(sub.getChannels());

        return  subscribersRepository.save(newSub);
    }

    public void DeleteSubscriber(Integer id) {
        subscribersRepository.deleteById(id);
    }

    public Optional<Subscriber> UpdateSubscriber(Integer id, UpdateSubscriberDTO sub) {
        var existingSub = subscribersRepository.findById(id);
        if (existingSub.isEmpty()) {
            return Optional.empty();
        }

        Subscriber updatedSub = existingSub.get();
        updatedSub.setName(sub.getName());

        return Optional.of(subscribersRepository.save(updatedSub));
    }

    public Optional<Subscriber> AddChannelToUser(Integer id, AddChannelDTO channelToAdd) {
        var subscriber = GetSubscriberById(id);
        if (subscriber.isEmpty()) {
            return Optional.empty();
        }

        var updatedSubscriber = subscriber.get();
        var channels = updatedSubscriber.getChannels();
        channels.add(channelToAdd.getChannel());
        subscribersRepository.save(updatedSubscriber);

        return Optional.of(updatedSubscriber);
    }
}
