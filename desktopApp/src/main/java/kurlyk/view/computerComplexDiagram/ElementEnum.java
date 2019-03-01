package kurlyk.view.computerComplexDiagram;

public enum ElementEnum {
    RAM("images/RAM.png"),
    CPU("images/CPU.png"),
    IO("images/IO.png"),
    POINT("images/Point.png");

    private String pathToImage;

    ElementEnum(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    public String getPathToImage() {
        return pathToImage;
    }
}
