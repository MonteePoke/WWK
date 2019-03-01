package kurlyk.view.computerSystemDiagram;

public enum ComputerSystemPicturePathEnum {
    RAM("images/RAM.png"),
    CPU("images/CPU.png"),
    IO("images/IO.png"),
    POINT("images/Point.png");

    private String pathToImage;

    ComputerSystemPicturePathEnum(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    public String getPathToImage() {
        return pathToImage;
    }
}
