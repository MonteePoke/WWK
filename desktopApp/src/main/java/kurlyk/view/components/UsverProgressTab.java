package kurlyk.view.components;

import javafx.scene.control.Tab;
import lombok.Getter;
import lombok.Setter;

public class UsverProgressTab extends Tab {

    @Getter @Setter private UserProgress userProgress;

    public UsverProgressTab() {
    }

    public UsverProgressTab(String text, UserProgress usverProgress) {
        super(text);
        this.userProgress = usverProgress;
    }
}
