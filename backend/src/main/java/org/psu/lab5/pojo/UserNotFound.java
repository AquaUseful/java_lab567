package org.psu.lab5.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserNotFound extends LoginException {

    private String username;

}
