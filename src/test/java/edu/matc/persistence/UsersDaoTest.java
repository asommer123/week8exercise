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

}