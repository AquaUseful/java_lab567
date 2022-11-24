package org.psu.lab5.pojo;

import javax.validation.constraints.Pattern;

import org.apache.logging.log4j.message.Message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @Pattern(regexp = "^[0-9A-Za-z]{5,20}$", message = "От 5 до 20 символов, только латинские буквы, цифры и _")
    private String username;

    @Pattern(regexp = "^(\\S){8,32}$", message = "От 8 до 32 непробельных символов")
    private String password;
}
