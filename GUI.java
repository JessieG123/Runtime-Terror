import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.stage.*;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.animation.AnimationTimer;

import java.io.*;

public class GUI extends Application{

    @Override
    public void start(Stage stage) {
        
        Pane root = new Pane();
        Scene scene = new Scene(root, 1500,950);

        Player player = new Player(750,425,80,20);
        Rectangle playerSprite = new Rectangle(25,60);
        playerSprite.relocate(player.getX_Coordinate(), player.getY_Coordinate()-30);

        Walls floor = new Walls(0,525,10000,100, true);
        Rectangle floorSprite = new Rectangle(10000,100);
        floorSprite.relocate(floor.getX(),floor.getY());

        root.getChildren().add(playerSprite);
        root.getChildren().add(floorSprite);

        stage.setTitle("Cowboy");
        stage.setScene(scene);
        stage.show();

        AnimationTimer gravity = new AnimationTimer() {

            @Override
            public void handle(long now){
    
                if (player.getUnitHitBox().intersects(floor.getUnitHitBox()) == false){
                    floor.setY(floor.getY() - 2);
                    floorSprite.relocate(floor.getX(),floor.getY());
                }
    
            }
    
        };

        gravity.start();
    }

    public static void main(String[] args) {
        launch();
    }

}