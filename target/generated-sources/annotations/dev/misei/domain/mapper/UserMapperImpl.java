package dev.misei.domain.mapper;

import dev.misei.domain.WorkingHours;
import dev.misei.domain.entity.Appointment;
import dev.misei.domain.entity.Business;
import dev.misei.domain.entity.User;
import dev.misei.domain.payload.AppointmentPayload;
import dev.misei.domain.payload.BusinessPayload;
import dev.misei.domain.payload.UserPayload;
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
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserPayload payload) {
        if ( payload == null ) {
            return null;
        }

        User user = new User();

        user.setEmail( payload.getEmail() );
        user.setName( payload.getName() );
        user.setPassword( payload.getPassword() );
        user.setPhone( payload.getPhone() );

        return user;
    }

    @Override
    public UserPayload toPayload(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserPayload userPayload = new UserPayload();

        userPayload.setName( entity.getName() );
        userPayload.setEmail( entity.getEmail() );
        userPayload.setPhone( entity.getPhone() );
        userPayload.setBusiness( businessToBusinessPayload( entity.getBusiness() ) );
        userPayload.setAppointments( appointmentSetToAppointmentPayloadSet( entity.getAppointments() ) );

        return userPayload;
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

    protected BusinessPayload businessToBusinessPayload(Business business) {
        if ( business == null ) {
            return null;
        }

        BusinessPayload businessPayload = new BusinessPayload();

        businessPayload.setSubdomain( business.getSubdomain() );
        businessPayload.setName( business.getName() );
        businessPayload.setAddress( business.getAddress() );
        businessPayload.setEmail( business.getEmail() );
        businessPayload.setPhone( business.getPhone() );
        Map<String, Long> map = business.getSlotDurationByServices();
        if ( map != null ) {
            businessPayload.setSlotDurationByServices( new LinkedHashMap<String, Long>( map ) );
        }
        Map<DayOfWeek, WorkingHours> map1 = business.getWorkingHoursByDay();
        if ( map1 != null ) {
            businessPayload.setWorkingHoursByDay( new LinkedHashMap<DayOfWeek, WorkingHours>( map1 ) );
        }
        Set<String> set = business.getSlotOwners();
        if ( set != null ) {
            businessPayload.setSlotOwners( new LinkedHashSet<String>( set ) );
        }
        Map<LocalDate, WorkingHours> map2 = business.getSpecialWorkingDays();
        if ( map2 != null ) {
            businessPayload.setSpecialWorkingDays( new LinkedHashMap<LocalDate, WorkingHours>( map2 ) );
        }
        businessPayload.setAppointments( appointmentSetToAppointmentPayloadSet( business.getAppointments() ) );

        return businessPayload;
    }
}
