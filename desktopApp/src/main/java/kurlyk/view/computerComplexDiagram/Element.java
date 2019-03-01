package kurlyk.view.computerComplexDiagram;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kurlyk.graph.GraphElement;

public class Element extends ImageView {

    private GraphElement<ElementEnum> graphElement;

    public Element(ElementEnum elementEnum) {
        super(new Image(Element.class.getResourceAsStream(elementEnum.getPathToImage())));
        this.graphElement = new GraphElement<>(elementEnum);
    }
}
