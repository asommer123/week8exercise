package edu.matc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class Users implements Serializable {
    private int accountId;
    private String userName;
    private String userPass;
    private String firstName;
    private String lastName;
    private String emailAddress;

    private Set<UserRole> userRoleByUserName = new HashSet<UserRole>(0);

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "account_id", nullable = false)
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Basic
    @Column(name = "user_name", nullable = false, length = 15)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "user_pass", nullable = false, length = 15)
    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    @Basic
    @Column(name = "first_name", nullable = true, length = 25)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = true, length = 30)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "email_address", nullable = true, length = 60)
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users users = (Users) o;

        if (accountId != users.accountId) return false;
        if (userName != null ? !userName.equals(users.userName) : users.userName != null) return false;
        if (userPass != null ? !userPass.equals(users.userPass) : users.userPass != null) return false;
        if (firstName != null ? !firstName.equals(users.firstName) : users.firstName != null) return false;
        if (lastName != null ? !lastName.equals(users.lastName) : users.lastName != null) return false;
        if (emailAddress != null ? !emailAddress.equals(users.emailAddress) : users.emailAddress != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = accountId;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userPass != null ? userPass.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "usersByAccountId")
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    //@JsonManagedReference
    @JsonIgnore
    public Set<UserRole> getUserRoleByUserName() {
        return userRoleByUserName;
    }

    public void setUserRoleByUserName(Set<UserRole> userRoleByUserName) {
        this.userRoleByUserName = userRoleByUserName;
    }
}

