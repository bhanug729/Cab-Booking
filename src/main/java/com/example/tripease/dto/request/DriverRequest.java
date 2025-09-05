package com.example.tripease.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DriverRequest {
    private String name;
    private int age;
    private String emailId;
}
