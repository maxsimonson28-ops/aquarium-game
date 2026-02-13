//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable {

   //Variable Definition Section
   //Declare the variables used in the program 
   //You can set their initial values too
   
   //Sets the width and height of the program window
	final int WIDTH = 1000;
	final int HEIGHT = 700;

   //Declare the variables needed for the graphics
	public JFrame frame;
	public Canvas canvas;
   public JPanel panel;
   
	public BufferStrategy bufferStrategy;
	public Image bassPic;
    public Image sharkPic;
    public Image trashPic;
    public Image orcaPic;
    public Image orcasWinPic;
    public Image backgroundPic;

   //Declare the objects used in the program
   //These are things that are made up of more than one variable type
	private Fish bass;
    private Shark shark;
    private Trash trash;
    private Orca orca;
    private OrcasWin orcasWin;


   // Main method definition
   // This is the code that runs first and automatically
	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
		new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method  
	}


   // Constructor Method
   // This has the same name as the class
   // This section is the setup portion of the program
   // Initialize your variables and construct your program objects here.
	public BasicGameApp() {
      
      setUpGraphics();
        int randx = (int)(Math.random()*10)+1;
        int randy = (int)(Math.random()*10)+1;

        randx = (int)(Math.random()*999)+1;
        randy = (int)(Math.random()*699)+1;
       
      //variable and objects
      //create (construct) the objects needed for the game and load up 
		bassPic = Toolkit.getDefaultToolkit().getImage("fishpic.png");
        sharkPic = Toolkit.getDefaultToolkit().getImage("sharkpic.png");
        trashPic = Toolkit.getDefaultToolkit().getImage("trash.jpeg");
        orcaPic = Toolkit.getDefaultToolkit().getImage("Orca.jpg");
        orcasWinPic = Toolkit.getDefaultToolkit().getImage("OrcasWin.png");


        backgroundPic = Toolkit.getDefaultToolkit().getImage("deeepaOcean.jpeg");
		bass = new Fish(randx,134);
        shark = new Shark(randx,randy);
        trash = new Trash(randx,randy);
        orca = new Orca(23,randy);
        orcasWin = new OrcasWin(100,50);//this is what position the win screen will be


	}// BasicGameApp()

   
//*******************************************************************************
//User Method Section
//
// put your code to do things here.

   // main thread
   // this is the code that plays the game after you set things up
	public void run() {

      //for the moment we will loop things forever.
		while (true) {

         moveThings();  //move all the game objects
         render();  // paint the graphics
         pause(20); // sleep for 10 ms
		}
	}


	public void moveThings() {
      //calls the move( ) code in the objects
		bass.move();
        shark.move();
        trash.move();
        orca.move();
        crashing();

	}


    public void crashing() {
        if (bass.hitbox.intersects(shark.hitbox)) {
            System.out.println("YUM Fish!!!!"); //this is printed everytime the shark eats the fish
            bass.dy = -shark.dy;
            bass.isAlive = false; //the bass dissappears when its eaten by the shark
            shark.dx = shark.dx + 5; //the shark speeds up after eating the fish
        }
        if(orca.hitbox.intersects(bass.hitbox)){
            bass.isAlive = false; //the bass dissappears when its eaten by the orca
            orca.dx = 2;
        }
        if(bass.hitbox.intersects(trash.hitbox)){ //when the fish eats the trash
            System.out.println("Ew");//this prints everytime the bass eats the trash
            bass.dy = 3;

        }
        if(shark.hitbox.intersects(trash.hitbox)){//when the shark eats the trash
            System.out.println("Ew");//this prints everytime the shark eats the trash
            shark.dx = 2;//this makes the shark slower when it eats the trash

        }
        if(orca.hitbox.intersects(trash.hitbox)){ //when the orca hits the trash
            System.out.println("Ew");//this prints everytime the orca eats the trash
            orca.dy = 2;
            orca.dx = 1;//these make the orca slower after eating the trash

        }
        if (shark.hitbox.intersects(orca.hitbox)) {
            System.out.println("Yum Shark!!!!"); //this is printed every time the orca eats the shark
            shark.dy = -orca.dy;
            shark.isAlive = false; //the sjark dissappears when the orca hits it

        }

        if (shark.isAlive == false){ //when the shark is dead the Win Screen shows
            orcasWin.isAlive = true;
        }

        }



   //Pauses or sleeps the computer for the amount specified in milliseconds
   public void pause(int time ){
   		//sleep
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {

			}
   }

   //Graphics setup method
   private void setUpGraphics() {
      frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.
   
      panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
      panel.setLayout(null);   //set the layout
   
      // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
      // and trap input events (Mouse and Keyboard events)
      canvas = new Canvas();  
      canvas.setBounds(0, 0, WIDTH, HEIGHT);
      canvas.setIgnoreRepaint(true);
   
      panel.add(canvas);  // adds the canvas to the panel.
   
      // frame operations
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
      frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
      frame.setResizable(false);   //makes it so the frame cannot be resized
      frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!
      
      // sets up things so the screen displays images nicely.
      canvas.createBufferStrategy(2);
      bufferStrategy = canvas.getBufferStrategy();
      canvas.requestFocus();
      System.out.println("DONE graphic setup");
   
   }


	//paints things on the screen using bufferStrategy
	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);
        g.drawImage(backgroundPic, 0, 0, WIDTH, HEIGHT, null); //shows background pic

      //these make the characters show up on screen
        if(bass.isAlive == true){
		g.drawImage(bassPic, bass.xpos, bass.ypos, bass.width, bass.height, null);}
        if(shark.isAlive == true){
        g.drawImage(sharkPic, shark.xpos, shark.ypos, shark.width, shark.height, null);}
        if(trash.isAlive == true){
            g.drawImage(trashPic, trash.xpos, trash.ypos, trash.width, trash.height, null);}
        g.drawImage(orcaPic, orca.xpos, orca.ypos, orca.width, orca.height, null);
        if(orcasWin.isAlive == true){
        g.drawImage(orcasWinPic, orcasWin.xpos, orcasWin.ypos, orcasWin.width, orcasWin.height, null);}


		g.dispose();


		bufferStrategy.show();
	}
}