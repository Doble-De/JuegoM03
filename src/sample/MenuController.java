package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Platform.exit;

public class MenuController {
    private AudioClip music = new AudioClip(getClass().getClassLoader().getResource("sample/recources/menumusic.mp3").toExternalForm());


    private Scene scene;
    private Stage stage;


    public void setScene(Scene scene){this.scene = scene;
    music.play();}

    public void setStage(Stage stage){this.stage = stage;}

    public void playgame(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);

        Controller window = loader.getController();
        window.setScene(scene);
        window.setStage(stage);

        stage.setScene(scene);
        stage.setTitle("Drops in the Club Yakuza");
        stage.show();
        music.stop();
    }

    public void exitgame(ActionEvent event){
        exit();
    }
}
