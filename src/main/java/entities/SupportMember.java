package entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class SupportMember {


    private Long id;

    private String name;

    private List<Ticket> ticketList;

    public SupportMember(){
        // Constructor
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(targetEntity = Ticket.class, mappedBy = "supportMemberList")
    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }
}
