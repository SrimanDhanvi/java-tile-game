package lighting;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import my_first2D_game.GamePanel;

public class light extends Quant{
	
	GamePanel gp;
	
	public light(GamePanel gp) {
		this.gp = gp;
	}


	public void draw(Graphics2D g2) {
		
		darkness = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D gDark = darkness.createGraphics();
		
		gDark.setColor(new Color(0, 0, 0, 80));
		gDark.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		
        if (gp.tileM.colorCode == 1) {
        	
        	gDark.setComposite(AlphaComposite.SrcOver);
    	
        Point2D center = new Point2D.Float(gp.tileM.lightTileX.get(0)+24, gp.tileM.lightTileY.get(0)+24);
        float radius = 100;
        float[] dist = {0.0f, 0.2f, 1.0f};  // Center → Mid-glow → Edge fade
        Color[] colors = {
            new Color(2, 224, 184, 100),  // Bright green at center
            new Color(0, 255, 150, 30),   // Faded green in the middle
            new Color(0, 0, 0, 0)         // Transparent at edge
        };

        RadialGradientPaint rgp = new RadialGradientPaint(center, radius, dist, colors);
        
        gDark.setPaint(rgp);
        gDark.fillOval(gp.tileM.lightTileX.get(0)-76, gp.tileM.lightTileY.get(0)-76, 200, 200);
        

        }

		
		gDark.dispose();
        
		g2.drawImage(darkness, 0, 0, null);
		
	}
	
}
