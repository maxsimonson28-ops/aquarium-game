import java.awt.*;

public class Orca {
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive;
    public Rectangle hitbox;
    public boolean isCrashing;  //this boolean is for are the characters hitting or not

    public Orca(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx =5;
        dy =2;
        width = 60;
        height = 60;
        isAlive = true;
        hitbox = new Rectangle(xpos,ypos,width,height);
        isCrashing = false;
        //these set my instance variables

    } // constructor

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() {
        if (xpos >= 1000 - width) {
            dx = -dx;
        }
        if (xpos <= 0) {
            dx = -dx;
        }
        if (ypos >= 700 - width) {
            dy = -dy;
        }
        if (ypos <= 0) {            //these if statements make the orca bounce off the walls
            dy = -dy;
        }
        xpos = xpos + dx;
        ypos = ypos + dy;
        xpos = xpos + dx;
        ypos = ypos + dy;
        hitbox = new Rectangle(xpos,ypos,width,height);
    }
}
