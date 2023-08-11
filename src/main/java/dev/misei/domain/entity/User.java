package dev.misei.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;

//FEATURE: We could add some HR also in a future
//FEATURE: Ticket system, Chat or Request more materials
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Comparable<User> {

    @Id
    private String userName;
    private String fullName;
    private String password;

    @Nullable
    private String base64Image;
    @Nullable
    private String phone;

    //FEATURE: UserRoles are defined on the User level because only one organization exists
    private UserRole userRole;


    @Override
    public boolean equals(Object o) {
        return (o instanceof User that) && this.userName.equalsIgnoreCase(that.userName);
    }

    public boolean isAdmin() {
        return userRole == UserRole.ADMIN;
    }

    @Override
    public int compareTo(User user) {
        return this.userRole.compareTo(user.userRole) * -1;
    }
}


