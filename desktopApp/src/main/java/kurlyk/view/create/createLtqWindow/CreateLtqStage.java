package kurlyk.view.create.createLtqWindow;

import kurlyk.WorkEntityType;
import kurlyk.model.LabWork;
import kurlyk.model.Question;
import kurlyk.model.Task;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.base.BaseStage;

import java.util.Optional;
import java.util.function.Consumer;

public class CreateLtqStage extends BaseStage<CreateLtqController> {


    public CreateLtqStage(WorkEntityType type, Optional<?> model, Consumer<?> saveAction) {
        super(BaseStageDto.allOff());
        if(!model.isPresent()){
            throw new RuntimeException();
        }
        controller.setCloseStage(this::close);
        switch (type){
            case LAB_WORK:
                controller.editLabWork((LabWork)model.get(), (Consumer<LabWork>)saveAction);
                break;
            case TASK:
                controller.editTask((Task)model.get(), (Consumer<Task>)saveAction);
                break;
            case QUESTION:
                controller.editQuestion((Question)model.get(), (Consumer<Question>)saveAction);
                break;
        }
    }

    @Override
    public String getPathToMainStage() {
        return "create/createLtqWindow/main";
    }
}
