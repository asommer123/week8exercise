package edu.matc.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {

    private int userRoleId;
    private String rollName;
    private Users usersByAccountId;

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "user_role_id", nullable = false)
    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    @Basic
    @Column(name = "roll_name", nullable = false, length = 15)
    public String getRollName() {
        return rollName;
    }

    public void setRollName(String rollName) {
        this.rollName = rollName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole userRole = (UserRole) o;

        if (userRoleId != userRole.userRoleId) return false;
        if (rollName != null ? !rollName.equals(userRole.rollName) : userRole.rollName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userRoleId;

        result = 31 * result + (rollName != null ? rollName.hashCode() : 0);
        return result;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_name", referencedColumnName = "user_name", nullable = false)
    @JsonBackReference
    public Users getUsersByAccountId() {
        return usersByAccountId;
    }

    public void setUsersByAccountId(Users usersByAccountId) {
        this.usersByAccountId = usersByAccountId;
    }
}
