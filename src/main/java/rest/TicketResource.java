package rest;

import dto.TicketDTO;
import service.TicketService;

import javax.naming.InvalidNameException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("/ticket")
public class TicketResource {

    TicketService ticketService = new TicketService();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TicketDTO getTicketById(@PathParam("id") String id) {
        return ticketService.getTicketById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTicket(TicketDTO ticket) {
        try {
            TicketDTO ticketDTO = ticketService.createTicket(ticket);
            return Response.ok().
                    entity(ticketDTO).
                    build();
        }
        catch (InvalidNameException e){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }

    }
}
