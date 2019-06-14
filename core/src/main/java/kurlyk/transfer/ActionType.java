package kurlyk.transfer;

public enum ActionType {
    CREATE("Создать"),
    IMPORT("Импортировать");

    private String name;

    ActionType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
