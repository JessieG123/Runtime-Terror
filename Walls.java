import java.awt.Rectangle;

public class Walls{

    int width, height, x, y;
    boolean isSolid;

    public Walls(int wX, int wY, int wWidth, int wHeight, boolean solid){

        this.x = wX;
        this.y = wY;
        this.width = wWidth;
        this.height = wHeight;
        this.isSolid = solid;

    }
    
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }

    public boolean getIsSolid(){
        return isSolid;
    }

    public void setX(int wX){
        x = wX;
    }

    public void setY(int wY){
        y = wY;
    }

    public void setWidth(int wWidth){
        if (wWidth > 0)
            width = wWidth;
    }

    public void setHeight(int wHeight){
        if (wHeight > 0)
            height = wHeight;
    }

    public void setIsSolid(boolean solid){
        if(solid != isSolid)
            isSolid = solid;
    }

    public Rectangle getUnitHitBox() {
        return new Rectangle(x, y, width, height);
    }

}