package kurlyk.view.task.computerSystemDiagramWindow.computerSystemDiagram;

import javafx.scene.image.Image;

public enum ComputerSystemDiagramPictures {
    RAM("/imagesForDraw/RAM.png"),
    CPU("/imagesForDraw/CPU.png"),
    IO("/imagesForDraw/IO.png"),
    POINT("/imagesForDraw/Point.png");

    private String pathToImage;

    ComputerSystemDiagramPictures(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    public Image getImage() {
        return new Image(getClass().getResourceAsStream(pathToImage));
    }
}
