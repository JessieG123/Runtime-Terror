import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;

public class Playerhandle extends Player{

    @Override
    public void handle(KeyEvent event){
        if(event.getCode() == keyCode.W){
            Player.setJump(True);  
        }
        if(event.getCode() == keyCode.A){
            Player.setLeft(True);
        }
        if(event.getCode() == keyCode.S){
            Player.setDown(True);
        }
        if(event.getCode() == keyCode.D){
            Player.setRight(True);
        }
        if(event.getCode() == keyCode.SPACE){
            Player.setShootBullets(True);
        }
    }

}