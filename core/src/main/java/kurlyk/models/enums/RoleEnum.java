package kurlyk.models.enums;

public enum  RoleEnum {
    ADMIN("admin"),
    STUDENT("student");

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
