package kurlyk.view.common.component;

import javafx.scene.control.Tab;
import kurlyk.models.UserProgress;
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
