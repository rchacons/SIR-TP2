package dto;

public class TicketDTO {


    public Long id;
    public String title;
    public String type;
    public String userName;
    public String state;
    public String tag;

    public TicketDTO(){
        // Empty constructor
    }

    public TicketDTO(String title, String type,String userName, String state, String tag){
        this.title=title;
        this.type=type;
        this.userName=userName;
        this.state=state;
        this.tag=tag;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TicketDTO{" +
                "id='"+id+'\''+
                "title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", userName='" + userName + '\'' +
                ", state='" + state + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
