import java.awt.*;

public class OrcasWin { //this is for the WIN SCREEN
    /**
     * Created by chales on 11/6/2017.
     */
    //I copied the Fish code into here just so the picture would have the size



        //VARIABLE DECLARATION SECTION
        //Here's where you state which variables you are going to use.
        public String name;
        public int xpos;
        public int ypos;
        public int dx;
        public int dy;
        public int width;
        public int height;
        public boolean isAlive;
        public Rectangle hitbox;
        public boolean isCrashing;  //this boolean is for are the characters hitting or not






        public OrcasWin(int pXpos, int pYpos) {
            xpos = pXpos;
            ypos = pYpos;
            dx =0;
            dy =0;
            width = 800;  // these make the winscreen big
            height = 800;
            isAlive = false;
            hitbox = new Rectangle(xpos,ypos,width,height);
            isCrashing = false;

        } // constructor


        public void move() {
            if(xpos>1000){
                xpos = 1;
            }
            if(ypos>700){
                ypos = 1;
            }
            xpos = xpos + dx;
            ypos = ypos + dy;
            hitbox = new Rectangle(xpos,ypos,width,height);

        }
    }








