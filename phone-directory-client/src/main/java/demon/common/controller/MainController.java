package demon.common.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainController {

    @FXML private AnchorPane apWrap;

    public void onBtnPhone() throws IOException {
        setOnWrap("/demon/phone/view/phone-view.fxml");
    }

    public void onBtnPerson() throws IOException {
        setOnWrap("/demon/person/view/person-view.fxml");
    }

    private void setOnWrap(String url) throws IOException {
        FXMLLoader loaderPhone = new FXMLLoader(MainController.class.getResource(url));
        apWrap.getChildren().clear();
        apWrap.getChildren().add(loaderPhone.load());
    }
}
