import java.awt.Rectangle;

public class Player {
    // The Players instance Variables
    private int x_coordinate;
    private int y_coordinate;
    private int width;
    private int height;
    private boolean isAlive, inAir, jumping, maxJump, movingRight, movingLeft;

    /**
     * Player constructor that takes in the x and y coordinates as well as the width
     * and height of the player
     * 
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public Player(int x, int y, int w, int h) {
        this.x_coordinate = x;
        this.y_coordinate = y;
        this.width = w;
        this.height = h;
        this.isAlive = true;
        this.inAir = false;
        this.maxJump = false;
    }

    // Getters
    /**
     * Returns the player's x-coordinate
     * 
     * @return x_coordinate
     */
    public int getX_Coordinate() {
        return x_coordinate;
    }

    /**
     * Returns the player's y-coordinate
     * 
     * @return
     */
    public int getY_Coordinate() {
        return y_coordinate;
    }

    /**
     * Returns the player's width
     * 
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the player's height
     * 
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the life of the player
     * 
     * @return
     */
    public boolean getIsAlive() {
        return isAlive;
    }

    public boolean getInAir() {
        return inAir;
    }

    public boolean getJumping(){
        return jumping;
    }

    public boolean getMaxJump(){
        return maxJump;
    }

    public boolean getMovingRight(){
        return movingRight;
    }

    public boolean getMovingLeft(){
        return movingLeft;
    }

    // Setters
    /**
     * Sets the player's x-coordinate
     * 
     * @param a
     */
    public void setXCoordinate(int a) {
        x_coordinate = a;
    }

    /**
     * Sets the player's y coordinate
     * 
     * @param a
     */
    public void setYCoordinate(int a) {
        y_coordinate = a;
    }

    /**
     * Sets the player's state
     * 
     * @param state
     */
    public void setIsAlive(boolean state) {
        if (isAlive != state)
            isAlive = state;
    }

    /**
     * Sets the player's width
     * 
     * @param aWidth
     */
    public void setWidth(int aWidth) {
        width = aWidth;
    }

    /**
     * Sets the player's height
     * 
     * @param aHeight
     */
    public void setHeight(int aHeight) {
        height = aHeight;
    }

    public void setInAir(boolean x){
        if (inAir != x){
            inAir = x;
        }
    }

    public void setJumping(boolean x){
        if (jumping != x){
            jumping = x;
        }
    }

    public void setMaxJump(boolean x){
        if (maxJump != x){
            maxJump = x;
        }
    }

    public void setMovingRight(boolean x){
        if (movingRight != x){
            movingRight = x;
        }
    }

    public void setMovingLeft(boolean x){
        if (movingLeft != x){
            movingLeft = x;
        }
    }

    public void usedMaxJump(int ijump){
        if ((ijump - getY_Coordinate()) < (ijump - 500)) 
            maxJump = true;
    }

    // Hitbox
    /**
     * Returns the position and dimensions of the unit's hitbox
     * 
     * @return
     */
    public Rectangle getUnitHitBox() {
        return new Rectangle(x_coordinate, y_coordinate, width, height);
    }
}