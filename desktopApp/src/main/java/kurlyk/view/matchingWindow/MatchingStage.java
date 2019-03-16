package kurlyk.view.matchingWindow;


import kurlyk.transfer.MatchingDto;
import kurlyk.view.common.stage.BaseStage;

import java.util.Collections;

public class MatchingStage extends BaseStage<MatchingController> {

    public MatchingStage(MatchingDto matchingDto) {
        super();
        controller.setItemsToView(getMixMatching(matchingDto));
    }

    @Override
    public String getPathToMainStage() {
        return "matchingWindow/main";
    }

    private MatchingDto getMixMatching(MatchingDto matchingDto){
        MatchingDto newMatchingDto = new MatchingDto(matchingDto);
        Collections.shuffle(newMatchingDto.getLeftPart());
        Collections.shuffle(newMatchingDto.getRightPart());
        return newMatchingDto;
    }
}
