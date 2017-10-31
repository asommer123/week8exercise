package edu.matc.persistence;

import edu.matc.entity.UserRole;
import edu.matc.entity.Users;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UsersDaoTest {

    UsersDao dao;

    @Before
    public void setup() {
        dao = new UsersDao();
    }

    @Test
    public void getAllUsers() throws Exception {
        List<Users> users = dao.getAllUsers();
        assertTrue(users.size() > 0);
    }

    @Test
    public void getUser() throws Exception {
        Users user = dao.getUser(1);
        assertTrue(user.getAccountId() == 1);
    }

    /*@Test
    public void addUser() throws Exception {
        Users user = new Users();

        user.setUserName("newAcct6");
        user.setUserPass("test");
        user.setEmailAddress("newUser@test.com");
        user.setFirstName("TestName");
        user.setLastName("LastName");

        UserRole userRole = new UserRole();
        userRole.setRollName("tester");
        userRole.setUsersByAccountId(user);
        user.getUserRoleByUserName().add(userRole);

        String userId = dao.addUser(user);

        assertTrue(dao.getUser(Integer.valueOf(userId)).equals(user));
    }

    @Test
    public void deleteUser() throws Exception {
        Users user = dao.getUser(3);
        dao.deleteUser(user.getAccountId());
        List<Users> usersList = dao.getAllUsers();
        Users user2 = dao.getUser(3);

        assertTrue(user2 == null);
    }

    @Test
    public void updateUser() throws Exception {
        Users users = dao.getUser(1);
        users.setEmailAddress("myEmail@test.edu");
        dao.updateUser(users);

        Users users1 = dao.getUser(1);

        assertTrue(users.equals(users1));
    }*/



}