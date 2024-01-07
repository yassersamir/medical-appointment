package com.yasser.medicalappointment.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class AppointmentDTO {
    private Long doctorId;
    private Long patientId;
    private LocalDateTime dateTime;
}
