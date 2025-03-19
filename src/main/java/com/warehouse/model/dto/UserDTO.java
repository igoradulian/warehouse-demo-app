package com.warehouse.model.dto;

import com.warehouse.validartion.FieldMatch;
import com.warehouse.validartion.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author igoradulyan on 12/18/24
 * @project IntelliJ IDEA
 */
@Getter
@Setter
@NoArgsConstructor
@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
public class UserDTO {

    private String userId;

    @NotBlank
    @NotNull
    @Pattern(regexp = "^[a-zA-Z]*$", message = "First name must contain only letters")
    private String firstName;

    @NotBlank
    @NotNull
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Last name must contain only letters")
    private String lastName;

    @NotBlank
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Username must contain only letters and numbers")
    private String username;

    @ValidPassword
    private String password;

    private String confirmPassword;

    @Email
    private String email;
    private String enabled;
}
