package kurlyk.view.computerSystemDiagram;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ComputerSystemPicture extends ImageView {

    public ComputerSystemPicture(ComputerSystemPicturePathEnum computerSystemPicturePathEnum) {
        super(new Image(ImageView.class.getResourceAsStream(computerSystemPicturePathEnum.getPathToImage())));
    }
}
