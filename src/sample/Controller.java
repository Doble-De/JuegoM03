package sample;

import com.sun.javafx.perf.PerformanceTracker;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;



import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML private Canvas mainCanvas;
    private Stage stage;

    Image fondo = new Image("sample/recources/fondo.png");
    private String s = getClass().getClassLoader().getResource("sample/recources/music.mp3").toExternalForm();
    private Media sound = new Media(s);
    private MediaPlayer audioClip = new MediaPlayer(sound);
    private AudioClip g = new AudioClip(getClass().getClassLoader().getResource("sample/recources/gota.wav").toExternalForm());
    private AudioClip a = new AudioClip(getClass().getClassLoader().getResource("sample/recources/acido.wav").toExternalForm());
    private Font tamaño = new Font("Chinyen", 20);
    private Cubo cubo;
    private Gota gota1, gota2, gota3, acido;
    private GraphicsContext gc;
    private Scene scene;
    double posX1, posX2, posX3;
    boolean dere, izqui;
    public int score = 0;
    private String textScore = "Score: ";

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.017), new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event) {
            PerformanceTracker perfTracker = PerformanceTracker.getSceneTracker(mainCanvas.getScene());
            // System.out.println(("FPS (Timeline) = " + perfTracker.getInstantFPS()));

            //cubo.move();

            posX1 = gota1.caer(posX3, "gota");

            posX2 = gota2.caer(posX1,"gota");

            cubo.clear(gc);
            gota1.clear(gc);
            gota2.clear(gc);
            acido.clear(gc);
            gc.setFill(Color.BLACK);
            gc.setFont(tamaño);
            gc.drawImage(fondo,0,0 );
            posX3 = acido.caer(posX2,"acido");
            cubo.render(gc);
            gota1.render(gc);
            gota2.render(gc);
            acido.render(gc);
            gc.fillText("Score: " + score, 380, 20);

            cubo.setDirection(dere, izqui);
            colision(gota1, cubo, "gota");
            colision(gota2, cubo, "gota");
            colision(acido, cubo, "acido");

        }
    })
    );


    public void colision(Gota gota, Cubo cubo, String tipo){

        if (tipo.equals("gota")){
            if (gota.getBoundary().intersects(cubo.getBoundary())){
                g.play();
                gota.clear(gc);
                gc.drawImage(fondo,0,0 );
                gota.reset();
                this.cubo.render(gc);
                gota1.render(gc);
                gota2.render(gc);
                acido.render(gc);
                score += 100;
            }
        }
        else if (tipo.equals("acido")){
            if (gota.getBoundary().intersects(cubo.getBoundary())){
                a.play();
                gota.clear(gc);
                gc.drawImage(fondo,0,0 );
                gota.reset();
                this.cubo.render(gc);
                gota1.render(gc);
                gota2.render(gc);
                acido.render(gc);
                score = 0;
            }
        }
    }


    public void setScene(final Scene sc) {
        scene = sc;
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Point2D point = new Point2D(mouseEvent.getX(),mouseEvent.getY());
                if(cubo.isClicked(point)) cubo.changeDir();
                System.out.println("click");
            }
        });

        scene.setOnKeyPressed(new EventHandler <KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                cubo.clear(gc);
                if (event.getCode() == KeyCode.LEFT){
                    dere = true;
                }
                if (event.getCode() == KeyCode.RIGHT){
                    izqui = true;
                }
                gc.drawImage(fondo,0,0 );
                cubo.render(gc);
                gota1.render(gc);
                gota2.render(gc);
                acido.render(gc);
            }
        });

        scene.setOnKeyReleased(new EventHandler <KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.LEFT){
                    dere = false;
                }
                if (event.getCode() == KeyCode.RIGHT){
                    izqui = false;
                }
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = mainCanvas.getGraphicsContext2D();
        gc.setFill(Color.RED);

        cubo = new Cubo();
        gota1 = new Gota(Controller.this);
        gota2 = new Gota(Controller.this);
        gota3 = new Gota(Controller.this);
        acido = new Gota(Controller.this);
        //audioClip.setCycleCount(MediaPlayer.INDEFINITE);
        cubo.setImage(new Image("sample/recources/cubo.png"));
        gota1.setImage(new Image("sample/recources/gota.png"));
        gota2.setImage(new Image("sample/recources/gota.png"));
        gota3.setImage(new Image("sample/recources/gota.png"));
        acido.setImage(new Image("sample/recources/acido.png"));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        //audioClip.play();


    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
