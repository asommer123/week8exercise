package edu.matc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.entity.Users;
import edu.matc.persistence.UsersDao;
import org.apache.log4j.Logger;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/users")
public class UserService {
    private final Logger log = Logger.getLogger(this.getClass());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessage() throws Exception {

        log.info("-- starting getMessage --");
        System.out.println("-- starting getMessage --");
        UsersDao usersDao = new UsersDao();
        log.info("-- created UsersDao --");
        System.out.println("-- created UsersDao --");
        List<Users> users = usersDao.getAllUsers();
        log.info("-- created list of users --");
        System.out.println("-- created list of users --");
        log.info(users);

        ObjectMapper mapper = new ObjectMapper();
        String output = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(users);

        return Response.status(200).entity(output).build();
    }




    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{userId}")
    public Response getMessageXML(@PathParam("userId") int userId) throws Exception {
        UsersDao usersDao = new UsersDao();
        Users user = usersDao.getUser(userId);

        ObjectMapper mapper = new ObjectMapper();
        String output = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);

        return Response.status(200).entity(output).build();
    }

/*

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public Response getMessage() {
        // Return a simple message
        String output = "Hello World!";
        System.out.println("Plain: " + output);
        return Response.status(200).entity(output).build();
    }
*/
}
