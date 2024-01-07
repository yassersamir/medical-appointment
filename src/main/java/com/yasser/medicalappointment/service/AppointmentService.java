package com.yasser.medicalappointment.service;

import com.yasser.medicalappointment.model.Appointment;
import com.yasser.medicalappointment.model.Doctor;
import com.yasser.medicalappointment.model.Patient;
import com.yasser.medicalappointment.repository.AppointmentRepository;
import com.yasser.medicalappointment.repository.DoctorRepository;
import com.yasser.medicalappointment.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    private final DoctorRepository doctorRepository;

    private final PatientRepository patientRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getAppointmentsByDate(LocalDate date) {
        return appointmentRepository.findByDateTimeAfter(date.atStartOfDay())
                .stream()
                .filter(appointment -> appointment.getDateTime().toLocalDate().equals(date))
                .toList();
    }

    public List<Appointment> getAppointmentsByPatientName(String patientName) {
        return appointmentRepository.findByPatientName(patientName);
    }

    public void addAppointment(Long doctorId, Long patientId, LocalDateTime dateTime) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));

        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setDateTime(dateTime);

        appointmentRepository.save(appointment);
    }

    public void cancelAppointment(Long appointmentId, String cancellationReason) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));

        appointment.setCancellationReason(cancellationReason);
        appointmentRepository.save(appointment);
    }
}
