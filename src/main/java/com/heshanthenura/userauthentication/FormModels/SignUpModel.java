package com.heshanthenura.userauthentication.FormModels;

import com.heshanthenura.userauthentication.Validation.PasswordMatch.FieldMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "confPassword", message = "Password fields must match")
})
public class SignUpModel {

    @NotBlank(message = "Full Name is Required")
    private String fullName;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Please confirm the password")
    private String confPassword;

    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    private String email;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getConfPassword() {
        return confPassword;
    }

    public void setConfPassword(String confPassword) {
        this.confPassword = confPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
