package com.yasser.medicalappointment.controller;

import com.yasser.medicalappointment.dto.AppointmentDTO;
import com.yasser.medicalappointment.dto.AppointmentResponseDTO;
import com.yasser.medicalappointment.mapper.AppointmentMapper;
import com.yasser.medicalappointment.model.Appointment;
import com.yasser.medicalappointment.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
@Slf4j
public class AppointmentController {

    private final AppointmentService appointmentService;

    private final AppointmentMapper appointmentMapper;

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDTO>> getAllAppointments() {
        log.debug("get all appointments");
        List<Appointment> appointments = appointmentService.getAllAppointments();
        List<AppointmentResponseDTO> responseDTOs = appointments.stream()
                .map(appointmentMapper::convertToResponseDTO)
                .toList();
        log.debug("appointments list:{}", responseDTOs);
        return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
    }

    @GetMapping("/byDate")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.debug("get Appointments By Date:{}",date);
        List<Appointment> appointments = appointmentService.getAppointmentsByDate(date);
        List<AppointmentResponseDTO> responseDTOs = appointments.stream()
                .map(appointmentMapper::convertToResponseDTO)
                .toList();
        log.debug("Appointments By Date list:{}",responseDTOs);
        return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
    }

    @GetMapping("/byPatient")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByPatientName(@RequestParam String patientName) {
        log.debug("get Appointments By patient name:{}",patientName);
        List<Appointment> appointments = appointmentService.getAppointmentsByPatientName(patientName);
        List<AppointmentResponseDTO> responseDTOs = appointments.stream()
                .map(appointmentMapper::convertToResponseDTO)
                .toList();
        log.debug("Appointments By Date list:{}",responseDTOs);
        return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        log.debug("add appointment with info:{}",appointmentDTO);
        appointmentService.addAppointment(appointmentDTO.getDoctorId(), appointmentDTO.getPatientId(), appointmentDTO.getDateTime());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<Void> cancelAppointment(@PathVariable Long id, @RequestParam String cancellationReason) {
        log.debug("cancel appointment id :{} with reason:{}",id,cancellationReason);
        appointmentService.cancelAppointment(id, cancellationReason);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
