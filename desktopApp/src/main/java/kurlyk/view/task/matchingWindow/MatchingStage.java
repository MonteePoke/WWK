package kurlyk.view.task.matchingWindow;


import kurlyk.transfer.tasks.MatchingDto;
import kurlyk.view.common.stage.BaseStage;

import java.util.Collections;

public class MatchingStage extends BaseStage<MatchingController> {

    public MatchingStage(MatchingDto matchingDto) {
        super();
        controller.setItemsToView(getMixMatching(matchingDto));
    }

    @Override
    public String getPathToMainStage() {
        return "task/matchingWindow/main";
    }

    private MatchingDto getMixMatching(MatchingDto matchingDto){
        MatchingDto newMatchingDto = new MatchingDto(matchingDto);
        Collections.shuffle(newMatchingDto.getLeftPart());
        Collections.shuffle(newMatchingDto.getRightPart());
        return newMatchingDto;
    }
}
