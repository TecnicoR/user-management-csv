package com.user.entity;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
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
