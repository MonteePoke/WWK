package kurlyk.view.textWindow;


import kurlyk.view.common.stage.BaseStage;

public class TextStage extends BaseStage<TextController> {

    public TextStage() {
        super();
    }

    @Override
    public String getPathToMainStage() {
        return "textWindow/main";
    }
}
