package kurlyk.view.common.draw;

import java.util.List;
import java.util.UUID;

public interface DiagramElement {

    UUID getUuid();

    List<? extends DiagramElement> getDiagramElementsForRemove();
}
