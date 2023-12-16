package com.user.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
  private String name;
  private LocalDate dob;
  private String address;
  private String contactNumber;
  private String email;
  private String department;
  private String title;
  private LocalDate hireDate;
  private Double salaryLpa;
  private String emergencyContact;
}
