package kurlyk.view.computerComplexDiagram;

public enum PicturePathEnum {
    RAM("images/RAM.png"),
    CPU("images/CPU.png"),
    IO("images/IO.png"),
    POINT("images/Point.png");

    private String pathToImage;

    PicturePathEnum(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    public String getPathToImage() {
        return pathToImage;
    }
}
