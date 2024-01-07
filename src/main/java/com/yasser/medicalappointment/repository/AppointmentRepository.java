package com.yasser.medicalappointment.repository;

import com.yasser.medicalappointment.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByDateTimeAfter(LocalDateTime dateTime);
    List<Appointment> findByPatientName(String patientName);

}
