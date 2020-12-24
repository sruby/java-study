package io.sruby.github.test.unit.parameter;

import lombok.AllArgsConstructor;

import java.time.LocalDate;

/**
 * @description: person
 * @author: sruby
 * @create: 2020-12-24 09:59
 */
@AllArgsConstructor
public class Person {
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate dateOfBirth;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
