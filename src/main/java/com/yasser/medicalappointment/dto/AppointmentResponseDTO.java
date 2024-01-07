package com.yasser.medicalappointment.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class AppointmentResponseDTO {
    private Long id;
    private Long doctorId;
    private Long patientId;
    private LocalDateTime dateTime;
    private String cancellationReason;
}
