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

public class GUI extends Application{

    private ArrayList<Walls> theWalls = new ArrayList<Walls>();
    private ArrayList<Rectangle> theWallsSprites = new ArrayList<Rectangle>();

    private ArrayList<Walls> theFloors = new ArrayList<Walls>();
    private ArrayList<Rectangle> theFloorsSprites = new ArrayList<Rectangle>();


    @Override
    public void start(Stage stage) {
        
        Pane root = new Pane();
        Scene scene = new Scene(root, 1500,950);

        Player player = new Player(750,425,80,20);
        Rectangle playerSprite = new Rectangle(25,60);
        playerSprite.relocate(player.getX_Coordinate(), player.getY_Coordinate()-40);

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
                            theFloors.get(i).setY(theFloors.get(i).getY() - 15);
                            theFloorsSprites.get(i).relocate(theFloors.get(i).getX(),theFloors.get(i).getY());
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
                            theFloors.get(i).setY(theFloors.get(i).getY() + 20);
                            theFloorsSprites.get(i).relocate(theFloors.get(i).getX(),theFloors.get(i).getY());
                        }
                    }    
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

                if (player.getIsAlive()){
                    for (int i = 0; i < theFloors.size(); i++){
                        if (player.getMovingRight() == true) {
                            theFloors.get(i).setX(theFloors.get(i).getX() - 10);
                            theFloorsSprites.get(i).relocate(theFloors.get(i).getX(),theFloors.get(i).getY());
                            for (int j = 0; j < theWalls.size(); j++){
                                if (player.getUnitHitBox().intersects(theWalls.get(i).getUnitHitBox())){
                                    theWalls.get(i).setX(theWalls.get(i).getX() + 1);
                                    theWallsSprites.get(i).relocate(theWalls.get(i).getX(),theWalls.get(i).getY());
                                }
                            }
                        }
                        if (player.getMovingLeft() == true) {
                            theFloors.get(i).setX(theFloors.get(i).getX() + 10);
                            theFloorsSprites.get(i).relocate(theFloors.get(i).getX(),theFloors.get(i).getY());
                            for (int j = 0; j < theWalls.size(); j++){
                                if (player.getUnitHitBox().intersects(theWalls.get(i).getUnitHitBox())){
                                    theWalls.get(i).setX(theWalls.get(i).getX() - 1);
                                    theWallsSprites.get(i).relocate(theWalls.get(i).getX(),theWalls.get(i).getY());
                                }
                            }
                        }
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