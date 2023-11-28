package org.example.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
    public static String myId;
    private String password;
    private String sessionId;
}