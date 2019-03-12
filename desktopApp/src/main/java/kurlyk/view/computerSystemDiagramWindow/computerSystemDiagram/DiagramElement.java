package kurlyk.view.computerSystemDiagramWindow.computerSystemDiagram;

import java.util.List;
import java.util.UUID;

public interface DiagramElement {

    UUID getUuid();

    void prepareForRemoval();
    List<? extends DiagramElement> getDiagramElementsForRemove();
}
