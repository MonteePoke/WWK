package kurlyk.view.task.computerSystemDiagramWindow.computerSystemDiagram;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public abstract class ComputerSystemDiagramElement extends VBox implements DiagramElement {

    @Getter private UUID uuid;

    public ComputerSystemDiagramElement(double x, double y) {
        super();
        setLayoutX(x);
        setLayoutY(y);
        setAlignment(Pos.CENTER);
        this.uuid = UUID.randomUUID();
    }

    @Override
    public List<? extends DiagramElement> getDiagramElementsForRemove() {
        return Collections.emptyList();
    }
}
