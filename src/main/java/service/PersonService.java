package service;

import dao.EntityManagerHelper;
import dao.PersonDAO;
import domain.Person;
import domain.SupportMember;
import domain.Ticket;
import domain.User;
import dto.PersonDTO;
import dto.SupportMemberDTO;
import dto.TicketDTO;
import dto.UserDTO;

import javax.naming.InvalidNameException;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class PersonService {

    PersonDAO personDAO = new PersonDAO();

    private EntityManager entityManager;

    public PersonService() { entityManager = EntityManagerHelper.getEntityManager();
    }

    public PersonDTO getPersonById(String id) {

        Person person = personDAO.read(Long.valueOf(id));

        if(person != null){

            if(person instanceof User){
                UserDTO userDTO = new UserDTO();
                userDTO.setId(person.getId());
                userDTO.setName(person.getName());

                List<TicketDTO> ticketDTOList = new ArrayList<>();
                for(Ticket ticket : ((User) person).getTicketList()){
                    populateTicketList(ticketDTOList, ticket);
                }
                userDTO.setCreatedTicketsList(ticketDTOList);
                userDTO.setType(PersonDTO.PersonType.USER);

                return userDTO;

            }
            else if (person instanceof SupportMember){
                SupportMemberDTO supportMemberDTO = new SupportMemberDTO();
                supportMemberDTO.setId(person.getId());
                supportMemberDTO.setName(person.getName());
                supportMemberDTO.setType(PersonDTO.PersonType.SUPPORT_MEMBER);
                List<TicketDTO> ticketDTOList = new ArrayList<>();

                for(Ticket ticket : ((SupportMember) person).getTicketList()){
                    populateTicketList(ticketDTOList, ticket);
                }
                supportMemberDTO.setAssignedTicketsList(ticketDTOList);

                return supportMemberDTO;
            }
        }

        return null;
    }

    private void populateTicketList(List<TicketDTO> ticketDTOList, Ticket ticket) {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setId(ticket.getId());
        ticketDTO.setType(ticket.getClass().getSimpleName());
        ticketDTO.setTitle(ticket.getTitle());
        ticketDTO.setState(ticket.getState().toString());
        ticketDTO.setTag(ticket.getTag().toString());
        ticketDTO.setUserName(ticket.getUser().getName());
        ticketDTOList.add(ticketDTO);
    }

    @Transactional
    public PersonDTO createPerson(PersonDTO personDTO) throws InvalidNameException {

        // We verify if there's any user with the same name
        if(isUsernameAvailable(personDTO.getName())){

            Person person = null;

            if(personDTO.getType() == PersonDTO.PersonType.USER){
                person = new User();
            }
            else if (personDTO.getType() == PersonDTO.PersonType.SUPPORT_MEMBER) {
                person = new SupportMember();
            }

            person.setName(personDTO.getName());

            try{
                entityManager.getTransaction().begin();
                Person personTmp = personDAO.save(person);
                entityManager.getTransaction().commit();

                personDTO.setId(personTmp.getId());

            } catch (Exception e){
                entityManager.getTransaction().rollback();
                throw e;
            }


        }
        else{
            throw new InvalidNameException("The username is already taken "+personDTO.getName());
        }

        return personDTO;
    }

    private boolean isUsernameAvailable(String name) {
        return personDAO.getPersonByName(name) == null;
    }
}
