package dev.misei.domain.mapper;

import dev.misei.domain.entity.Appointment;
import dev.misei.domain.payload.AppointmentPayload;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-09T21:55:48+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
public class AppointmentMapperImpl implements AppointmentMapper {

    @Override
    public Appointment toEntity(AppointmentPayload payload) {
        if ( payload == null ) {
            return null;
        }

        String slotOwner = null;
        LocalDateTime slotStartAppointment = null;
        Long slotDuration = null;
        String slotService = null;
        String manualBookingInfoByAdmin = null;
        String manualBookingInviteToJoin = null;

        slotOwner = payload.getSlotOwner();
        slotStartAppointment = payload.getSlotStartAppointment();
        slotDuration = payload.getSlotDuration();
        slotService = payload.getSlotService();
        manualBookingInfoByAdmin = payload.getManualBookingInfoByAdmin();
        manualBookingInviteToJoin = payload.getManualBookingInviteToJoin();

        String id = null;

        Appointment appointment = new Appointment( id, slotOwner, slotStartAppointment, slotDuration, slotService, manualBookingInfoByAdmin, manualBookingInviteToJoin );

        return appointment;
    }

    @Override
    public AppointmentPayload toPayload(Appointment entity) {
        if ( entity == null ) {
            return null;
        }

        AppointmentPayload appointmentPayload = new AppointmentPayload();

        appointmentPayload.setId( entity.getId() );
        appointmentPayload.setSlotOwner( entity.getSlotOwner() );
        appointmentPayload.setSlotStartAppointment( entity.getSlotStartAppointment() );
        appointmentPayload.setSlotDuration( entity.getSlotDuration() );
        appointmentPayload.setSlotService( entity.getSlotService() );
        appointmentPayload.setManualBookingInfoByAdmin( entity.getManualBookingInfoByAdmin() );
        appointmentPayload.setManualBookingInviteToJoin( entity.getManualBookingInviteToJoin() );

        return appointmentPayload;
    }
}
