package kurlyk.view.computerComplexDiagram;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kurlyk.graph.GraphElement;

public class Element <T extends Enum> extends ImageView {

    private GraphElement<T> graphElement;

    public Element(String pathToImage) {
        super(new Image(Element.class.getResourceAsStream("")));
    }
}
