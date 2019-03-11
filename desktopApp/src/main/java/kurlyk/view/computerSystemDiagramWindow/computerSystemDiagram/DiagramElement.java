package kurlyk.view.computerSystemDiagramWindow.computerSystemDiagram;

import java.util.List;
import java.util.UUID;

public interface DiagramElement {

    UUID getUuid();

    List<? extends DiagramElement> getDiagramElementsForRemove();
}
