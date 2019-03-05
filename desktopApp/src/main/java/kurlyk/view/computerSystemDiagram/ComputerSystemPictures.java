package kurlyk.view.computerSystemDiagram;

import javafx.scene.image.Image;

public enum ComputerSystemPictures {
    RAM("/images/RAM.png"),
    CPU("/images/CPU.png"),
    IO("/images/IO.png"),
    POINT("/images/Point.png");

    private String pathToImage;

    ComputerSystemPictures(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    public Image getImage() {
        return new Image(getClass().getResourceAsStream(pathToImage));
    }
}
