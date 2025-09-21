package notes;

//> To create the window you write this in the main class
//  Syntax:
    /* 
      JFrame window = new JFrame();
    */

//> To give the exit functionality
//  Syntax:
    /*
     window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    */

//> If you don't want the window size to be resized
//  Syntax:
    /* 
     window.setResizable(false); // If you want it to keep -> true 
    */

//> For setting the title on the panel
//  Syntax:
    /* 
     window.setTitle("Adventure Hunting"); 
    */

//> To pop the window on the center of the screen
//  Syntax:
    /*
     window.setLocationRelativeTo(null); // Or you can specify the position
    */

//>  ! VERY IMPORTANT --------------------------------

//> Now to make this all visible
//  Syntax:
    /*
     window.setVisible(true);
    */

// --------------------------------------------------

//> Now you make another class (GamePanel Class) and extend it to JPanel to specify the measurements of the window panel
//> In the class you need to declare variable of: 
//       -originalTileSize  (The size of the characters in a tile)
//       -scale             (To magnify the characters)
//       -tileSize          (The actual size visible)
//       -maxScreenCol      (Number of tiles in an column)
//       -maxScreenRow      (Number of tiles in a row)
//       -screenWidth       (Width of the window panel in pixels)
//       -screenHeight      (Height of the window panel in pixels)

//       -playerX           (Initial player X position)
//       -playerY           (Initial player X position)
//       -playerSpeed       (Movement speed)
//       -FPS               

//> You assign the screenWidth and screenHeight by a constructor
//   Syntax:
     /*
      public GamePanel() {
			this.setPreferredSize(new Dimension(screenWidth , screenHeight));
			this.setBackground(Color.black); // optional
			this.setDoubleBuffered(true); // for better rendering
		}
     */

//> Then you link GamePanel Class to the main class by
//   Syntax:
     /*
      GamePanel gamePanel = new GamePanel();
	  window.add(gamePanel);
		
	  window.pack();
     */

//> Now to create a thread in GamePanel Class
//  Syntax
    /*
     Thread gameThread;
		
		public void startGameThread() {
			gameThread = new Thread(this);
			gameThread.start();
		}
		
		public void run() {
			
		}
     */

//> You are creating the thread here itself.
//> You are instantiating a new thread — meaning you're creating a new Thread object
//— and passing it something to run (a class that implements Runnable, which is "this").
//> Here this refers to the class containing the new thread object itself. 
/*
  Thread gameThread = new Thread(this);
 */

//> So simply instead of initiating the thread in another class 
//   we are keeping it ready in the GamePanel Class itself.

//> Now in the run() method you write the Game Loops which are:
//          - 1. UPDATE 
//          - 2. DRAW // draw the screen with updated info. //
//   Syntax:
     /*
      			while(gameThread != null) {
			   
				update(); // update
				repaint(); // draw
				}
      */

//> For that you create the update() method in the GamePanel Class and mention it in the game loop 
//    Syntax:
      /*
       public void update {
       
        }
       */

//> For draw you create the paintComponenet() method which is pre-define in the the JPanel
//    Syntax:
      /*
       public void paintComponenet(Graphics g) {
       
          super.paintComponent(g);
         }
       */

//> To mention this in the run() gameLoop
//  Syntax:
    /*
     repaint();
     */

//> To make it more sophisticated, to make objects we mention
//    Syntax:
      /*
       Graphics2D g2 = (Graphics2D)g;
       */

//> Graphics2D: it is a class that extends the Graphics class to provide 
// more sophisticated control over geometry, coordinate transformations, color management, 
// and text layout.

//> A simple object (rectangle):
//  Syntax:
    /*
      g2.setColor(Color.white);
	  g2.fillRect(playerX, playerY, tileSize, tileSize);
	  
	  g2.dispose();
    */

//> TO ADD KEYBORD CONTROLS:

//> Create a new class (KeyHandler Class)   
//      - Implement KeyListener
//      - Create boolean variables upPressed, downPressed, leftPressed, rightPressed;
//      - Add the unimplementd methods;

//> Under keyPressed write
//    Syntax:
      /*
       		int code = e.getKeyCode();
		
        switch(code) {
        case KeyEvent.VK_W : 
        	upPressed = true;
        	break;
        case KeyEvent.VK_S :
        	downPressed = true;
        	break;
        case KeyEvent.VK_A :
        	leftPressed = true;
        	break;
        case KeyEvent.VK_D :
        	rightPressed = true;
        	break;
       */

//> Under keyReleased write the same code which is under keyPressed and replace true with false.

//> Now in the GamePanel Class you mention the KeyHandler Class
//   Syntax:
     /*
      Keyhandler keyH = new Keyhandler();
      */
// In GamePanel Class, under the constructor write
//  Syntax:
    /*
	    this.addKeyListener(keyH);
		this.setFocusable(true);     
     */

// Under update() method
//  Syntax:
    /*
			if (keyH.upPressed == true) {
				playerY -= playerSpeed;
			}
			else if (keyH.downPressed == true) {
				playerY += playerSpeed;
			}
			else if (keyH.leftPressed == true) {
		    	playerX -= playerSpeed;
		    }
			else if (keyH.rightPressed == true) {
		    	playerX += playerSpeed;
		    }
     */

// Now if you run the program and try to move the playerObject it will. 
// This happens as the computer processes really fast. 
// So by the time you press and release the key (A, W, S, D) the object moves several hundred pixels
// To slow down the computer processing on this program 
// Under the run() method write
//   Syntax:
     /*
      			
			double drawInterval = 1000000000/FPS; // 0.01666 seconds
			double newxDrawTime = System.nanoTime() + drawInterval;// nano sec for being precise and smooth
			
			while(gameThread != null) {
			   
				update(); // update
				repaint(); // draw
			

				try {
					
					double remainingTime = newxDrawTime - System.nanoTime() ;
					remainingTime /= 1000000;                                // 1 sec = 1000000000 nano sec
					                                                         // i sec = 1000 milli sec     
					if (remainingTime < 0) {
						remainingTime = 0;
					}
					
					Thread.sleep((long) remainingTime);  // sleep only accepts in long data type in milli sec
					
					newxDrawTime += drawInterval;
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
      */




       
