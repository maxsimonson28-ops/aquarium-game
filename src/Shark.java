import java.awt.*;

public class Shark {
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


    // METHOD DEFINITION SECTION

    // Constructor Definition
    // A constructor builds the object when called and sets variable values.


    //This is a SECOND constructor that takes 3 parameters.  This allows us to specify the hero's name and position when we build it.
    // if you put in a String, an int and an int the program will use this constructor instead of the one above.
    public Shark(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx =5;
        dy =0;
        width = 60;
        height = 60;
        isAlive = true;
        hitbox = new Rectangle(xpos,ypos,width,height);
        isCrashing = false;

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
        if (ypos <= 0) {  //these if statements make the shark bounce off the walls
            dy = -dy;
        }
        xpos = xpos + dx;
        ypos = ypos + dy;
        hitbox = new Rectangle(xpos,ypos,width,height);

    }
}

