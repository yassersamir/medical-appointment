package com.yasser.medicalappointment.mapper;

import com.yasser.medicalappointment.dto.AppointmentResponseDTO;
import com.yasser.medicalappointment.model.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    @Mappings({
            @Mapping(target = "id", source = "appointment.id"),
            @Mapping(target = "doctorId", source = "appointment.doctor.id"),
            @Mapping(target = "patientId", source = "appointment.patient.id"),
            @Mapping(target = "dateTime", source = "appointment.dateTime"),
            @Mapping(target = "cancellationReason", source = "appointment.cancellationReason")
    })
    AppointmentResponseDTO convertToResponseDTO(Appointment appointment);

}
