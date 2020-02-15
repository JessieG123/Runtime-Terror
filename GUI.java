import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.stage.*;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

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

        AnimationTimer gravity = new AnimationTimer() {

            @Override
            public void handle(long now){
    
                if (player.getUnitHitBox().intersects(floor.getUnitHitBox()) == false){
                    floor.setY(floor.getY() - 15);
                    floorSprite.relocate(floor.getX(),floor.getY());
                } else {
                    player.setInAir(false);
                    player.setMaxJump(false);
                }
    
            }
    
        };

        AnimationTimer jump = new AnimationTimer() {

            @Override
            public void handle(long now){

                if (player.getJumping() == true && player.getMaxJump() == false){
                    floor.setY(floor.getY() + 20);
                    floorSprite.relocate(floor.getX(),floor.getY());
                }

            }

        };

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                int initalY = player.getY_Coordinate();

                if(event.getCode() == KeyCode.W){
                    if (player.getInAir() == false){
                        player.setInAir(true);
                        player.setJumping(true);
                        gravity.stop();
                    }
                }
                if(event.getCode() == KeyCode.A){
                    player.setMovingLeft(true);
                }
                if(event.getCode() == KeyCode.S){
                    //Player.setDown(True);
                }
                if(event.getCode() == KeyCode.D){
                    player.setMovingRight(true);
                }
                if(event.getCode() == KeyCode.SPACE){
                    
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.W){
                    player.setJumping(false);
                    gravity.start();
                }
                if(event.getCode() == KeyCode.A){
                    player.setMovingLeft(false);
                }
                if(event.getCode() == KeyCode.S){
                    //Player.setDown(True);
                }
                if(event.getCode() == KeyCode.D){
                    player.setMovingRight(false);
                }
                if(event.getCode() == KeyCode.SPACE){
                    
                }
            }
        });

        root.getChildren().add(playerSprite);
        root.getChildren().add(floorSprite);

        stage.setTitle("Cowboy");
        stage.setScene(scene);
        stage.show();

        AnimationTimer move = new AnimationTimer() {

            @Override
            public void handle(long now){
                if (player.getMovingRight() == true) {
                    floor.setX(floor.getX() - 10);
                    floorSprite.relocate(floor.getX(),floor.getY());
                }
                if (player.getMovingLeft() == true) {
                    floor.setX(floor.getX() + 10);
                    floorSprite.relocate(floor.getX(),floor.getY());
                }
            }

        };

        gravity.start();
        jump.start();
        move.start();
    }

    public static void main(String[] args) {
        launch();
    }

}