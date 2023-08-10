package dev.misei.domain.mapper;

import dev.misei.domain.WorkingHours;
import dev.misei.domain.entity.Appointment;
import dev.misei.domain.entity.Business;
import dev.misei.domain.payload.AppointmentPayload;
import dev.misei.domain.payload.BusinessPayload;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-30T01:04:58+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
public class BusinessMapperImpl implements BusinessMapper {

    @Override
    public Business toEntity(BusinessPayload payload) {
        if ( payload == null ) {
            return null;
        }

        String subdomain = null;
        String name = null;
        String address = null;
        String email = null;
        String phone = null;
        Map<String, Long> slotDurationByServices = null;
        Map<DayOfWeek, WorkingHours> workingHoursByDay = null;
        Set<String> slotOwners = null;
        Map<LocalDate, WorkingHours> specialWorkingDays = null;

        subdomain = payload.getSubdomain();
        name = payload.getName();
        address = payload.getAddress();
        email = payload.getEmail();
        phone = payload.getPhone();
        Map<String, Long> map = payload.getSlotDurationByServices();
        if ( map != null ) {
            slotDurationByServices = new LinkedHashMap<String, Long>( map );
        }
        Map<DayOfWeek, WorkingHours> map1 = payload.getWorkingHoursByDay();
        if ( map1 != null ) {
            workingHoursByDay = new LinkedHashMap<DayOfWeek, WorkingHours>( map1 );
        }
        Set<String> set = payload.getSlotOwners();
        if ( set != null ) {
            slotOwners = new LinkedHashSet<String>( set );
        }
        Map<LocalDate, WorkingHours> map2 = payload.getSpecialWorkingDays();
        if ( map2 != null ) {
            specialWorkingDays = new LinkedHashMap<LocalDate, WorkingHours>( map2 );
        }

        Set<Appointment> appointments = null;

        Business business = new Business( subdomain, name, address, email, phone, slotDurationByServices, workingHoursByDay, slotOwners, specialWorkingDays, appointments );

        return business;
    }

    @Override
    public BusinessPayload toPayload(Business entity) {
        if ( entity == null ) {
            return null;
        }

        BusinessPayload businessPayload = new BusinessPayload();

        businessPayload.setSubdomain( entity.getSubdomain() );
        businessPayload.setName( entity.getName() );
        businessPayload.setAddress( entity.getAddress() );
        businessPayload.setEmail( entity.getEmail() );
        businessPayload.setPhone( entity.getPhone() );
        Map<String, Long> map = entity.getSlotDurationByServices();
        if ( map != null ) {
            businessPayload.setSlotDurationByServices( new LinkedHashMap<String, Long>( map ) );
        }
        Map<DayOfWeek, WorkingHours> map1 = entity.getWorkingHoursByDay();
        if ( map1 != null ) {
            businessPayload.setWorkingHoursByDay( new LinkedHashMap<DayOfWeek, WorkingHours>( map1 ) );
        }
        Set<String> set = entity.getSlotOwners();
        if ( set != null ) {
            businessPayload.setSlotOwners( new LinkedHashSet<String>( set ) );
        }
        Map<LocalDate, WorkingHours> map2 = entity.getSpecialWorkingDays();
        if ( map2 != null ) {
            businessPayload.setSpecialWorkingDays( new LinkedHashMap<LocalDate, WorkingHours>( map2 ) );
        }
        businessPayload.setAppointments( appointmentSetToAppointmentPayloadSet( entity.getAppointments() ) );

        return businessPayload;
    }

    protected AppointmentPayload appointmentToAppointmentPayload(Appointment appointment) {
        if ( appointment == null ) {
            return null;
        }

        AppointmentPayload appointmentPayload = new AppointmentPayload();

        appointmentPayload.setId( appointment.getId() );
        appointmentPayload.setSlotOwner( appointment.getSlotOwner() );
        appointmentPayload.setSlotStartAppointment( appointment.getSlotStartAppointment() );
        appointmentPayload.setSlotDuration( appointment.getSlotDuration() );
        appointmentPayload.setSlotService( appointment.getSlotService() );
        appointmentPayload.setManualBookingInfoByAdmin( appointment.getManualBookingInfoByAdmin() );
        appointmentPayload.setManualBookingInviteToJoin( appointment.getManualBookingInviteToJoin() );

        return appointmentPayload;
    }

    protected Set<AppointmentPayload> appointmentSetToAppointmentPayloadSet(Set<Appointment> set) {
        if ( set == null ) {
            return null;
        }

        Set<AppointmentPayload> set1 = new LinkedHashSet<AppointmentPayload>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Appointment appointment : set ) {
            set1.add( appointmentToAppointmentPayload( appointment ) );
        }

        return set1;
    }
}
