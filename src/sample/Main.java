package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        Parent root = loader.load();
        Scene sc = new Scene(root);

        MenuController window = loader.getController();
        window.setScene(sc);
        window.setStage(stage);

        stage.setScene(sc);
        stage.setTitle("Rebota");
        stage.setFullScreen(false);
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
