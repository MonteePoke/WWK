package kurlyk.view.components;

import javafx.scene.control.Tab;
import lombok.Getter;
import lombok.Setter;

public class UserProgressTab extends Tab {

    @Getter @Setter private UserProgress userProgress;

    public UserProgressTab() {
    }

    public UserProgressTab(String text, UserProgress userProgress) {
        super(text);
        this.userProgress = userProgress;
    }
}
