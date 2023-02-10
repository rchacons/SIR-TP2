package service;

import dao.EntityManagerHelper;
import dao.PersonDAO;
import dao.TicketDAO;
import domain.*;
import dto.TicketDTO;

import javax.naming.InvalidNameException;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class TicketService {

    TicketDAO ticketDAO = new TicketDAO();
    private EntityManager entityManager;

    public TicketService(){
        entityManager = EntityManagerHelper.getEntityManager();
    }

    public List<TicketDTO> getTicketsByUser(String userName) {

        List<Ticket> tickets = ticketDAO.getTicketsByUser(userName);

        List<TicketDTO> ticketDTOS = new ArrayList<TicketDTO>();

        for (Ticket ticket : tickets) {
            TicketDTO.TicketType type = (ticket instanceof BugForm) ? TicketDTO.TicketType.BUG : TicketDTO.TicketType.FEATURE;

            ticketDTOS.add(new TicketDTO(ticket.getTitle(), type, ticket.getUser().getName(),
                    ticket.getState().toString(), ticket.getTag().toString()));
        }

        return ticketDTOS;
    }

    @Transactional
    public TicketDTO createTicket(TicketDTO ticketDTO) throws InvalidNameException {
        Ticket ticket = null;


        if (ticketDTO.getType() == TicketDTO.TicketType.BUG)
            ticket = new BugForm();
        else if (ticketDTO.getType() == TicketDTO.TicketType.FEATURE)
            ticket = new FeatureRequestForm();

        PersonDAO personDAO = new PersonDAO();
        User user = (User) personDAO.getUserByName(ticketDTO.creator);

        if(user != null){
            ticket.setUser(user);
            ticket.setTitle(ticketDTO.getTitle());
            ticket.setState(StateEnum.valueOf(ticketDTO.getState()));
            ticket.setTag(TagEnum.valueOf(ticketDTO.tag));

            // TODO how to add different SupportMembers from a dto?
            //ticket.addSupportMemberList..

            try{
                entityManager.getTransaction().begin();
                Ticket ticket1 = ticketDAO.save(ticket);
                entityManager.getTransaction().commit();

                ticketDTO.setId(ticket1.getId());

            } catch (Exception e){
                entityManager.getTransaction().rollback();
                throw e;
            }

        }else{
            throw new InvalidNameException("There are no records of the username: "+ticketDTO.creator);
        }
        return ticketDTO;
    }

    public TicketDTO getTicketById(String id) {
        Ticket ticket = ticketDAO.read(Long.valueOf(id));
        TicketDTO ticketDTO = null;
        if(ticket != null){
            TicketDTO.TicketType type = (ticket instanceof BugForm) ? TicketDTO.TicketType.BUG : TicketDTO.TicketType.FEATURE;
            ticketDTO = new TicketDTO(ticket.getTitle(),type,
                    ticket.getUser().getName(), ticket.getState().toString(), ticket.getTag().toString());
            ticketDTO.setId(ticket.getId());
        }

        return ticketDTO;

    }
}
