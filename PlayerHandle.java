import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;

public class Playerhandle{

    @Override
    public void handle(KeyEvent event){
        if(event.getCode() == keyCode.W){
            //do something when up key is pressed
        }
        if(event.getCode() == keyCode.A){
            //do something when down key is pressed
        }
        if(event.getCode() == keyCode.S){
            //do something when left key is pressed
        }
        if(event.getCode() == keyCode.DT){
            //do something when left key 
        }
        if(event.getCode() == keyCode.SPACE){
            //do something when space is pressed 
        }
    }

}