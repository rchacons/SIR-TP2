package dto;

public class TicketDTO {


    public Long id;
    public String title;
    public TicketType type;
    public String creator;
    public String state;
    public String tag;

    public TicketDTO(){
        // Empty constructor
    }

    public TicketDTO(String title, TicketType type,String userName, String state, String tag){
        this.title=title;
        this.type=type;
        this.creator =userName;
        this.state=state;
        this.tag=tag;
    }

    public enum TicketType {
        BUG, FEATURE
    }


    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreator() {
        return creator;
    }


    public void setCreator(String creator) {
        this.creator = creator;
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
                ", userName='" + creator + '\'' +
                ", state='" + state + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
