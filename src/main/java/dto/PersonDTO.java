package dto;

import javax.validation.constraints.NotNull;
import java.util.List;

public abstract class PersonDTO {

    private Long id;
    @NotNull(message = "Name may not be null")
    private String name;

    private PersonType type;

    public enum PersonType {
        USER, SUPPORT_MEMBER
    }

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

    public PersonType getType() {
        return type;
    }

    public void setType(PersonType type) {
        this.type = type;
    }


}
