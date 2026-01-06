public class Orca {
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive;

    public Orca(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx =5;
        dy =2;
        width = 60;
        height = 60;
        isAlive = true;

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
        if (ypos <= 0) {
            dy = -dy;
        }
        xpos = xpos + dx;
        ypos = ypos + dy;
        xpos = xpos + dx;
        ypos = ypos + dy;
    }
}
