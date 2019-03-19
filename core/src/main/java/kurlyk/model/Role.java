package kurlyk.model;

public enum Role {
    ADMIN("admin"),
    PROFESSOR("professor"),
    STUDENT("student");

    private String role;

    Role(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }
}
