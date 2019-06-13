package kurlyk.model.enums;

public enum  RoleEnum {
    ADMIN("ADMIN"),
    STUDENT("STUDENT");

    private String name;


    RoleEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
