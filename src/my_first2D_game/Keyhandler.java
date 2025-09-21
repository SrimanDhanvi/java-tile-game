package my_first2D_game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyhandler implements KeyListener{
	
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	boolean F3 = false;
	
	GamePanel gp;
	
	public Keyhandler(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		//TITLE STATE
		if (gp.GameState == gp.titleState) {
			
			if (gp.ui.tileScreenState == 0) {
			
			if(code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 3;
				}
			}
			if(code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 3) {
					gp.ui.commandNum = 0;
				}
			}
			if(code == KeyEvent.VK_UP) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 3;
				}
			}
			if(code == KeyEvent.VK_DOWN) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 3) {
					gp.ui.commandNum = 0;
				}	
			}
			
			if (code == KeyEvent.VK_ENTER) {
			switch(gp.ui.commandNum) {
			case 0:
				gp.GameState = gp.playState;
				gp.ui.commandNum = 0;
				gp.playMusic();
				gp.playWind();
			break;
			case 1:
				gp.ui.commandNum = 0;
			break;
			case 2:
				gp.ui.tileScreenState = 1;
				gp.ui.commandNum = 0;
			break;
			case 3:
				System.exit(0);
				gp.ui.commandNum = 0;
			break;
			    }
			}
		} 
			else if(gp.ui.tileScreenState == 1) {
			if (code == KeyEvent.VK_ESCAPE) {
				gp.ui.tileScreenState = 0;
			}
			if (code == KeyEvent.VK_D) {
				gp.ui.selectNum++;
				if(gp.ui.selectNum > 1) {
					gp.ui.selectNum = 0;
				}
			}
			
			if (code == KeyEvent.VK_RIGHT) {
				gp.ui.selectNum--;
				if(gp.ui.selectNum < 0) {
					gp.ui.selectNum = 1;
				}
			}
			if (code == KeyEvent.VK_LEFT) {
				gp.ui.selectNum++;
				if(gp.ui.selectNum > 1) {
					gp.ui.selectNum = 0;
				}
			}
			
			if (code == KeyEvent.VK_A) {
				gp.ui.selectNum--;
				if(gp.ui.selectNum < 0) {
					gp.ui.selectNum = 1;
				}
			}
			if (code == KeyEvent.VK_ENTER && gp.ui.commandNum<gp.player.arrayImgPath.length) {
				gp.player.characterNum = gp.ui.selectNum;
				gp.player.getplayerImage();
				gp.ui.tileScreenState = 0;
				gp.ui.selectNum = 0;
			}
		}
	}
		
		// PAUSE STATE
		
		if (gp.GameState == gp.pauseState) {
			
			if(gp.ui.pauseScreenState == 0) {
			
			if(code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 2;
				}
			}
			if(code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 2) {
					gp.ui.commandNum = 0;
				}
			}
			if(code == KeyEvent.VK_UP) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 2;
				}
			}
			if(code == KeyEvent.VK_DOWN) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 2) {
					gp.ui.commandNum = 0;
				}	
			}
			
			if (code == KeyEvent.VK_ENTER) {
			switch(gp.ui.commandNum) {
			case 0:
				gp.GameState = gp.playState;
				gp.ui.commandNum = 0;
			break;
			case 1:
				gp.GameState = gp.titleState;
				gp.stopMusic();
				gp.stopSE();
				gp.ui.commandNum = 0;
			break;
			case 2:
				gp.ui.commandNum = 0;
				gp.ui.pauseScreenState = 1;
			break;
			  }
		   }
		} else if (gp.ui.pauseScreenState == 1) {
			if (code == KeyEvent.VK_ESCAPE) {
				gp.ui.pauseScreenState = 0;
			}
		}
	}
		
		//PLAY STATE
		if(gp.GameState == gp.playState) {
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
	        case KeyEvent.VK_UP : 
	         	upPressed = true;
	        	break;
	        case KeyEvent.VK_DOWN :
	         	downPressed = true;
	        	break;
	        case KeyEvent.VK_LEFT :
	         	leftPressed = true;
	        	break;
	        case KeyEvent.VK_RIGHT :
	         	rightPressed = true;
	        	break;	
	        case KeyEvent.VK_ENTER :
	         	gp.ui.dno++;
	        	break;
	        	
	        case KeyEvent.VK_ESCAPE :
	        { if (gp.GameState == gp.playState) {
	         	gp.GameState = gp.pauseState;
	        } else if (gp.GameState == gp.pauseState){
	         	gp.GameState = gp.playState;
	        }
	        }
	        break;
	        
	        	//DEBUG
	        case KeyEvent.VK_F3 :
	        {
	        	if (F3 == false) {
	        	F3 = true;
	        	} else if (F3 == true) {
	        		F3 = false;
	        	}
	        }
	        break;
	        }
		}

		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();

        switch(code) {
        case KeyEvent.VK_W : 
        	upPressed = false;
        	break;
        case KeyEvent.VK_S :
        	downPressed = false;
        	break;
        case KeyEvent.VK_A :
        	leftPressed = false;
        	break;
        case KeyEvent.VK_D :
        	rightPressed = false;
        	break;
        case KeyEvent.VK_UP : 
        	upPressed = false;
        	break;
        case KeyEvent.VK_DOWN :
        	downPressed = false;
        	break;
        case KeyEvent.VK_LEFT :
        	leftPressed = false;
        	break;
        case KeyEvent.VK_RIGHT :
        	rightPressed = false;
        	break;	
        }
		
	}
	
	

}
