package kurlyk.view.computerComplexDiagram;

public enum ComputerComplexElementEnum {
    RAM("images/RAM.png"),
    CPU("images/CPU.png"),
    IO("images/IO.png"),
    POINT("images/Point.png");

    private String pathToImage;

    ComputerComplexElementEnum(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    public String getPathToImage() {
        return pathToImage;
    }
}
