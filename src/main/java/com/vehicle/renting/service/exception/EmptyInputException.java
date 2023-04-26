package com.vehicle.renting.service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class EmptyInputException extends RuntimeException
{
   private Integer errorCode;
   private String errorMessage;
}
