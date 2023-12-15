package vttp.ssf.assessment.eventmanagement.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class User {

    @NotEmpty(message = "Name is required")
    @Size(min = 5, max = 25, message = "Minimum 5 characters, maximum 25 characters")
    private String fullName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "DOB must be a past date")
    private LocalDate dob;

    @NotEmpty(message = "Email is required")
    @Email(message = "Wrong email format")
    @Size(max = 50, message = "Maximum of 50 characters")
    private String email;

    @Pattern(regexp = "(8|9)[0-9]{7}", message = "Invalid phone number entered")  
    private String mobile;

    private String gender;

    @Min(value = 1, message = "Minimum 1 ticket")
    @Max(value = 3, message = "Maximum 3 tickets")
    private int numberOfTickets;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public User(
            @NotEmpty(message = "Name is required") @Size(min = 5, max = 25, message = "Minimum 5 characters, maximum 25 characters") String fullName,
            @Past(message = "DOB must be a past date") LocalDate dob,
            @NotNull(message = "Email is required") @Email(message = "Wrong email format") @Size(max = 50, message = "Maximum of 50 characters") String email,
            @Pattern(regexp = "(8|9)[0-9]{7}", message = "Invalid phone number entered") String mobile, String gender,
            @Min(value = 1, message = "Minimum 1 ticket") @Max(value = 3, message = "Maximum 3 tickets") int numberOfTickets) {
        this.fullName = fullName;
        this.dob = dob;
        this.email = email;
        this.mobile = mobile;
        this.gender = gender;
        this.numberOfTickets = numberOfTickets;
    }

    public User(){

    }
}