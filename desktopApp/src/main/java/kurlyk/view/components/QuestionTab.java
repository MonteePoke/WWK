package kurlyk.view.components;

import javafx.scene.control.Tab;
import kurlyk.model.Question;
import lombok.Getter;
import lombok.Setter;

public class QuestionTab extends Tab {

    @Getter @Setter private Question question;

    public QuestionTab(String text, Question question) {
        super(text);
        this.question = question;
    }
}
