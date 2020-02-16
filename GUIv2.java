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

        Walls floor = new Walls(-50,825,10000,50, true);
        Rectangle floorSprite = new Rectangle(10000,50);
        floorSprite.relocate(floor.getX(),floor.getY());
        theFloors.add(floor);
        theFloorsSprites.add(floorSprite);

        Walls wall = new Walls(650,800,100,50, true);
        Rectangle wallSprite = new Rectangle(100,50);
        wallSprite.relocate(wall.getX(),wall.getY());
        theWalls.add(wall);
        theWallsSprites.add(wallSprite);

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
                    if (theFloors.size() > 0 && theFloors.get(0).getY() < 0){
                        player.setIsAlive(false);
                    }
                    if (player.getY_Coordinate() > 950){
                        player.setYCoordinate(0);
                        for (int l = 0; l < theFloors.size(); l++){
                            theFloors.get(l).setY(theFloors.get(l).getY() - 950);
                            theFloorsSprites.get(l).setY(theFloorsSprites.get(l).getY() - 950);
                        }
                        for (int r = 0; r < theWalls.size(); r++){
                            theWalls.get(r).setY(theWalls.get(r).getY() - 950);
                            theWallsSprites.get(r).setY(theWallsSprites.get(r).getY() - 950);
                        }
                    }
                } 
            }
    
        };

        AnimationTimer jump = new AnimationTimer() {

            @Override
            public void handle(long now){

                if (player.getIsAlive()){
                    if (player.getJumping()){
                        player.setYCoordinate(player.getY_Coordinate() - 20);
                        playerSprite.relocate(player.getX_Coordinate(),player.getY_Coordinate());
                    }
                    if (player.getY_Coordinate() < 0){
                        player.setYCoordinate(950);
                        for (int l = 0; l < theFloors.size(); l++){
                            theFloors.get(l).setY(theFloors.get(l).getY() + 950);
                            theFloorsSprites.get(l).setY(theFloorsSprites.get(l).getY() + 950);
                        }
                        for (int r = 0; r < theWalls.size(); r++){
                            theWalls.get(r).setY(theWalls.get(r).getY() + 950);
                            theWallsSprites.get(r).setY(theWallsSprites.get(r).getY() + 950);
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

        for (int i = 0; i < theFloorsSprites.size(); i++){
            root.getChildren().add(theFloorsSprites.get(i));
        }
        for (int i = 0; i < theWallsSprites.size(); i++){
            root.getChildren().add(theWallsSprites.get(i));
        }

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
                        for (int i = 0; i < theWalls.size(); i++){
                            if (player.getUnitHitBox().intersects(theWalls.get(i).getUnitHitBox())){
                                player.setXCoordinate(player.getX_Coordinate() - 6);
                                playerSprite.setX(player.getX_Coordinate());
                            }
                        }
                        if (player.getX_Coordinate() > 1490){
                            player.setXCoordinate(0);
                            for (int l = 0; l < theFloors.size(); l++){
                                theFloors.get(l).setX(theFloors.get(l).getX() - 1500);
                                theFloorsSprites.get(l).setX(theFloors.get(l).getX());
                            }
                            for (int r = 0; r < theWalls.size(); r++){
                                theWalls.get(r).setX(theWalls.get(r).getX() - 1500);
                                theWallsSprites.get(r).setX(theWallsSprites.get(r).getX() - 1500);
                            }
                        }
                    }
                    if (player.getMovingLeft()){
                        player.setXCoordinate(player.getX_Coordinate() - 5);
                        playerSprite.relocate(player.getX_Coordinate(),player.getY_Coordinate());
                        for (int i = 0; i < theWalls.size(); i++){
                            if (player.getUnitHitBox().intersects(theWalls.get(i).getUnitHitBox())){
                                player.setXCoordinate(player.getX_Coordinate() + 6);
                                playerSprite.setX(player.getX_Coordinate());
                            }
                        }
                        if (player.getX_Coordinate() < 10){
                            player.setXCoordinate(1500);
                            for (int l = 0; l < theFloors.size(); l++){
                                theFloors.get(l).setX(theFloors.get(l).getX() + 1500);
                                theFloorsSprites.get(l).setX(theFloors.get(l).getX());
                            }
                            for (int r = 0; r < theWalls.size(); r++){
                                theWalls.get(r).setX(theWalls.get(r).getX() + 1500);
                                theWallsSprites.get(r).setX(theWallsSprites.get(r).getX() + 1500);
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