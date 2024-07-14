package com.pup.api.dog.event.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class DogV0 {
    private Long dogId;
    private String name;
    private String profile;
    private LocalDate birth;
    private Boolean isNeutered;
}
