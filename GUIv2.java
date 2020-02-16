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
import java.util.*;

public class GUIv2 extends Application{

    private ArrayList<Walls> theWalls = new ArrayList<Walls>();
    private ArrayList<Rectangle> theWallsSprites = new ArrayList<Rectangle>();

    private ArrayList<Walls> theFloors = new ArrayList<Walls>();
    private ArrayList<Rectangle> theFloorsSprites = new ArrayList<Rectangle>();


    @Override
    public void start(Stage stage) {
        
        Pane root = new Pane();
        Scene scene = new Scene(root, 1500,950);

        Player player = new Player(750,425,25,60);
        Rectangle playerSprite = new Rectangle(25,60);
        playerSprite.relocate(player.getX_Coordinate(), player.getY_Coordinate());

        Walls floor = new Walls(0,525,10000,100, true);
        Rectangle floorSprite = new Rectangle(10000,100);
        floorSprite.relocate(floor.getX(),floor.getY());
        theFloors.add(floor);
        theFloorsSprites.add(floorSprite);

        AnimationTimer gravity = new AnimationTimer() {

            @Override
            public void handle(long now){
                
                if (player.getIsAlive()){
                    for (int i = 0; i < theFloors.size(); i++){
                        if (player.getUnitHitBox().intersects(theFloors.get(i).getUnitHitBox()) == false){
                            player.setYCoordinate(player.getY_Coordinate() + 15);
                            playerSprite.relocate(player.getX_Coordinate(),player.getY_Coordinate());
                        } else {
                            player.setInAir(false);
                        }
                    }
                    if (theFloors.size() > 0 && theFloors.get(0).getY() < 0)
                        player.setIsAlive(false);
                } 
            }
    
        };

        AnimationTimer jump = new AnimationTimer() {

            @Override
            public void handle(long now){

                if (player.getIsAlive()){
                    for (int i = 0; i < theFloors.size(); i++){
                        if (player.getJumping()){
                            player.setYCoordinate(player.getY_Coordinate() - 20);
                            playerSprite.relocate(player.getX_Coordinate(),player.getY_Coordinate());
                        }
                    }    
                }
            }

        };

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

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

                if (player.getIsAlive()){
                    if (player.getMovingRight()){
                        player.setXCoordinate(player.getX_Coordinate() + 5);
                        playerSprite.relocate(player.getX_Coordinate(),player.getY_Coordinate());
                    }
                    if (player.getMovingLeft()){
                        player.setXCoordinate(player.getX_Coordinate() - 5);
                        playerSprite.relocate(player.getX_Coordinate(),player.getY_Coordinate());
                    }
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