package entities;

public enum StateEnum {


    OPEN("OPEN"),RESOLVED("RESOLVED");

    public final String state;
    StateEnum(String s) {
        this.state=s;
    }
}
