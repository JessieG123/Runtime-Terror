public abstract class Player extends Character{

    //player class logic. need to be able to move around 
    //check life

    private int lifeRemaining;
    private boolean jump;
    private boolean left;
    private boolean right;
    private boolean down;
    private boolean shootBullets;

    public Player(){

    }
    
    public Player(int lifeRemaining, boolean jump, boolean left, boolean right, boolean down, boolean shootBullets){
        this.lifeRemaning = lifeRemaining;
        this.jump = jump;
        this.left = left;
        this.right = right;
        this.down = down;
        this.shootBullets = shootBullets;
    }
    public int setLifeRemaining(int lifeRemaining){
        this.lifeRemaining = lifeRemaining;
    }

    public boolean setJump(boolean jump){
        this.jump = jump;
    }

    public boolean setLeft(boolean left){
        this.left = left;
    }

    public boolean setRight(boolean right){
        this.right = right;
    }

    public boolean setDown(boolean down){
        this.down = down;
    }

    public boolean setShootBullets(boolean shootBullets){
        this.shootBullets = shootBullets;
    }

    public int getLifeRemaining(){
        return lifeRemaining;
    }

    public boolean getJump(){
        return jump;
    }

    public boolean getLeft(){
        return left;
    }

    public boolean getDown(){
        return down;
    }

    public boolean getRight(){
        return right;
    }
    public boolean getShootBullets(){
        return shootBullets;
    }

    public abstract void handle(KeyEvent event);

    



}