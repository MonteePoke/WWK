package kurlyk.view.computerSystemDiagramWindow.computerSystemDiagram;

import javafx.scene.image.Image;

public enum ComputerSystemDiagramPictures {
    RAM("/images/RAM.png"),
    CPU("/images/CPU.png"),
    IO("/images/IO.png"),
    POINT("/images/Point.png");

    private String pathToImage;

    ComputerSystemDiagramPictures(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    public Image getImage() {
        return new Image(getClass().getResourceAsStream(pathToImage));
    }
}
