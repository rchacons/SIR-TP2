package entities;

import javax.persistence.*;
import java.util.List;

@Entity
//@Table(name="Users")
public class User {

    private Long id;

    private String name;

    private List<Ticket> ticketList;

    public User(){
        //Constructor
    }

    @OneToMany(targetEntity = Ticket.class,mappedBy = "user", fetch = FetchType.EAGER)
    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public User(String name){
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
