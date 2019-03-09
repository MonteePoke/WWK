package kurlyk.view.drawComputerSystemWindow.computerSystemDiagram;

import java.util.List;
import java.util.UUID;

public interface DiagramElement {

    UUID getUuid();

    List<? extends DiagramElement> getDiagramElementsForRemove();
}
