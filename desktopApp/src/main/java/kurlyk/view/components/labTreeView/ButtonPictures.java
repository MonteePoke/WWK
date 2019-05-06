package kurlyk.view.components.labTreeView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public enum ButtonPictures {
    ADD("/imagesForTree/add.png"),
    DELETE("/imagesForTree/delete.png"),
    EDIT("/imagesForTree/edit.png");

    private String pathToImage;

    ButtonPictures(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    public Image getImage() {
        return new Image(getClass().getResourceAsStream(pathToImage));
    }

    public ImageView getImageView() {
        ImageView imageView = new ImageView(getImage());
        imageView.setFitWidth(10);
        imageView.setFitHeight(10);
        return imageView;
    }
}
