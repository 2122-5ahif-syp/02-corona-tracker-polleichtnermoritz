package at.htl.boundary;

import at.htl.control.PersonRepository;
import at.htl.entity.Person;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Instant;

@Path("/api/person")
@Transactional
public class PersonResource {
    @Inject
    PersonRepository repo;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createEntry( @FormParam("firstName") String firstName,
                                 @FormParam("lastName") String lastName,
                                 @FormParam("email") String email,
                                 @FormParam("telephoneNo") String telephoneNo) {
        Person p = new Person(firstName, lastName, email, telephoneNo, Instant.now());
        repo.persist(p);
        return Response.status(201).entity(p).build();
    }
}