package rest;

import dto.PersonDTO;
import dto.TicketDTO;
import service.PersonService;

import javax.naming.InvalidNameException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/person")
public class PersonResource {

    PersonService personService = new PersonService();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PersonDTO getPersonById(@PathParam("id") String id){
        return personService.getPersonById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPerson(PersonDTO person) {
        try {
            PersonDTO personDTO = personService.createPerson(person);
            return Response.ok().
                    entity(personDTO).
                    build();
        }
        catch (InvalidNameException e){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }

    }

    //TODO: duplicate the method and change the PersonDTO in parameters as concrete
//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response createPerson(PersonDTO person) {
//        try {
//            PersonDTO personDTO = personService.createPerson(person);
//            return Response.ok().
//                    entity(personDTO).
//                    build();
//        }
//        catch (InvalidNameException e){
//            return Response.status(Response.Status.BAD_REQUEST)
//                    .entity(e.getMessage())
//                    .build();
//        }
//
//    }
}
