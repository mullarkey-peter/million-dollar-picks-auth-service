package com.glizzy.milliondollarpicks.authservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginEvent {
    private String username;
    private Long timestamp;
}