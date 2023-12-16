package com.user.service;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.user.dto.UserRequest;
import com.user.dto.UserResponse;
import com.user.entity.User;
import com.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
  private final ModelMapper modelMapper;
  private final UserRepository userRepository;

  public List<UserResponse> create( List<UserRequest> userRequests ) {
    return userRepository.saveAll(
            userRequests.stream().map( userRequest -> modelMapper.map( userRequest, User.class ) ).toList() ).stream()
        .map( user -> modelMapper.map( user, UserResponse.class ) ).toList();
  }

  public List<UserResponse> getAll(){
    return userRepository.findAll().stream().map( user -> modelMapper.map( user, UserResponse.class ) ).toList();
  }

  public UserResponse getById( UUID id ){
    return modelMapper.map( userRepository.findById( id ),  UserResponse.class );
  }

  public String downloadCsvForAllUsers(){
    StringWriter writer = new StringWriter();
    try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
      csvPrinter.printRecord(
          Arrays.stream( User.class.getDeclaredFields() ).map( Field::getName ).map( UserService::convertCamelCase ).toArray() );
      for (User user : userRepository.findAll()) {
        csvPrinter.printRecord(
            user.getId(),
            user.getName(),
            user.getDob(),
            user.getAddress(),
            user.getContactNumber(),
            user.getEmail(),
            user.getDepartment(),
            user.getTitle(),
            user.getHireDate(),
            user.getSalaryLpa(),
            user.getEmergencyContact()
        );
      }
    } catch ( IOException e) {
      throw new RuntimeException("Error in CSV file generation", e);
    }
    return writer.toString();
  }

  private static String convertCamelCase(String camelCaseStr) {
    if (camelCaseStr == null || camelCaseStr.isEmpty()) {
      return camelCaseStr;
    }

    StringBuilder result = new StringBuilder();
    char firstChar = camelCaseStr.charAt(0);
    result.append(Character.toUpperCase(firstChar));

    for (int i = 1; i < camelCaseStr.length(); i++) {
      char currentChar = camelCaseStr.charAt(i);
      if (Character.isUpperCase(currentChar)) {
        result.append(" ");
        result.append(currentChar);
      } else {
        result.append(currentChar);
      }
    }

    return result.toString();
  }
}
