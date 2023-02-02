package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(name="Tickets")
public class Ticket {

    private Long id;

    private String title;

    @Transient
    private ArrayList<String> comments;


    private List<SupportMember> supportMemberList;


    private User user;
    private StateEnum state;


    private TagEnum tag;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Enumerated(EnumType.STRING)
    public StateEnum getState() {
        return state;
    }

    public void setState(StateEnum state) {
        this.state = state;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

    @ManyToMany(targetEntity = SupportMember.class)
    public List<SupportMember> getSupportMemberList() {
        return supportMemberList;
    }

    public void setSupportMemberList(List<SupportMember> supportMemberList) {
        this.supportMemberList = supportMemberList;
    }

    @ManyToOne()
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Enumerated(EnumType.STRING)
    public TagEnum getTag() {
        return tag;
    }

    public void setTag(TagEnum tag) {
        this.tag = tag;
    }


}
