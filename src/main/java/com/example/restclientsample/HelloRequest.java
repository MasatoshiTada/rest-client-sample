package com.example.restclientsample;

import java.time.LocalDate;

public record HelloRequest(String message, LocalDate date) {
}
