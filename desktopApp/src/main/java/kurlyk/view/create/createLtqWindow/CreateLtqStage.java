package kurlyk.view.create.createLtqWindow;

import kurlyk.WorkEntityType;
import kurlyk.models.LabWork;
import kurlyk.models.Question;
import kurlyk.models.Task;
import kurlyk.view.common.dto.BaseStageDto;
import kurlyk.view.common.stage.base.BaseStage;

import java.util.Optional;
import java.util.function.BiConsumer;

public class CreateLtqStage extends BaseStage<CreateLtqController> {


    public CreateLtqStage(WorkEntityType type, Optional<?> model, Integer number, BiConsumer<?, Integer> saveAction) {
        super(BaseStageDto.allOff());
        if(!model.isPresent()){
            throw new RuntimeException();
        }
        controller.setCloseStage(this::close);
        switch (type){
            case LAB_WORK:
                controller.editLabWork((LabWork)model.get(), (BiConsumer<LabWork, Integer>)saveAction, number);
                break;
            case TASK:
                controller.editTask((Task)model.get(), (BiConsumer<Task, Integer>)saveAction, number);
                break;
            case QUESTION:
                controller.editQuestion((Question)model.get(), (BiConsumer<Question, Integer>)saveAction, number);
                break;
        }
    }

    @Override
    public String getPathToMainStage() {
        return "create/createLtqWindow/main";
    }
}
