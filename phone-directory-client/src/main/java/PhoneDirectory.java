import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class PhoneDirectory extends Application {
    public void start(Stage stage) throws IOException {
        FXMLLoader loaderMain = new FXMLLoader(PhoneDirectory.class.getResource("demon/common/view/main-view.fxml"));
        AnchorPane paneMain = loaderMain.load();

        AnchorPane apWrap = (AnchorPane) paneMain.getChildren()
                .filtered(node -> Objects.equals(node.getId(), "apWrap"))
                .getFirst();
        FXMLLoader loaderPerson = new FXMLLoader(PhoneDirectory.class.getResource("demon/person/view/person-view.fxml"));
        apWrap.getChildren().add(loaderPerson.load());

        Scene sceneMain = new Scene(paneMain, 700, 400);
        stage.setTitle("Phone Directory");
        stage.setScene(sceneMain);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

