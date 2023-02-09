package dto;

import java.util.List;

public class SupportMemberDTO extends PersonDTO{

    private List<TicketDTO> assignedTicketsList;

    public List<TicketDTO> getAssignedTicketsList() {
        return assignedTicketsList;
    }

    public void setAssignedTicketsList(List<TicketDTO> assignedTicketsList) {
        this.assignedTicketsList = assignedTicketsList;
    }


}
