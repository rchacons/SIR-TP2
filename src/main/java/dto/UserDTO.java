package dto;

import java.util.List;

public class UserDTO extends PersonDTO {

    private List<TicketDTO> createdTicketsList;

    public List<TicketDTO> getCreatedTicketsList() {
        return createdTicketsList;
    }

    public void setCreatedTicketsList(List<TicketDTO> createdTicketsList) {
        this.createdTicketsList = createdTicketsList;
    }

}
