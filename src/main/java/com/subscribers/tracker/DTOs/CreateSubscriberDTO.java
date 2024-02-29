package com.subscribers.tracker.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CreateSubscriberDTO {
    private String name;
    private Set<String> channels = new HashSet<>();
}
