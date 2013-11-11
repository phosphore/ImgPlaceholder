package test;

import org.TextImage;
import ip.imagegenerator.Margin;
import ip.imagegenerator.imagecallbacks.BackgroundColorCallback;
import ip.imagegenerator.imageexporter.ImageType;
import ip.imagegenerator.imageexporter.ImageWriter;
import ip.imagegenerator.imageexporter.ImageWriterFactory;
import ip.imagegenerator.impl.TextImageImpl;



import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;


public class Bgcolor {

	    public static void main(String[] args) throws Exception {
	        new Bgcolor().imgcreation();
	    }

	    private void imgcreation() throws Exception {

	    	String dircolor = "";
	    	Boolean isgray = false;
	    	int height = 350;
	    	int width = 450;
	    	// TODO Remember to check height and width size!
	    	
	    	String chosencolor = "#FFFFFF";
	    	// TODO Remember to check input text!
	    	Color bColor = Color.decode(chosencolor);
	    	// TODO add alpha gradient & color compatibility!
	    	
	    	//Color.GRAY is #808080
	    	if (bColor.getRGB() == Color.GRAY.getRGB())
	    	{
	    		dircolor = "white";
	    		isgray = true;
	    	}
	    	else
	    		dircolor = "grey";
	    	
	        final TextImage placeholder = new TextImageImpl(width, height, new Margin(0, 0, 0, 0));
	        
	        // Setting up the font
			InputStream isfont = new FileInputStream("fonts/seguisb.ttf");
			Font SegUIsb = null;
			try {
				SegUIsb = Font.createFont(Font.PLAIN, isfont);
				SegUIsb = SegUIsb.deriveFont(20f);
			} catch (FontFormatException e) {
				e.printStackTrace();
			}
			
			// IMAGES   //////////////////////////////////////////////////////////
	        InputStream iswater = this.getClass().getResourceAsStream("/images/ip_"+dircolor+".png");
	        BufferedImage ipwater = ImageIO.read(iswater);
	        
	        InputStream isupl = this.getClass().getResourceAsStream("/images/arrows/"+dircolor+"/up-l.png");
	        BufferedImage upl = ImageIO.read(isupl);
	        
	        InputStream isupr = this.getClass().getResourceAsStream("/images/arrows/"+dircolor+"/up-r.png");
	        BufferedImage upr = ImageIO.read(isupr);
	        
	        InputStream islowl = this.getClass().getResourceAsStream("/images/arrows/"+dircolor+"/low-l.png");
	        BufferedImage lowl = ImageIO.read(islowl);
	        
	        InputStream islowr = this.getClass().getResourceAsStream("/images/arrows/"+dircolor+"/low-r.png");
	        BufferedImage lowr = ImageIO.read(islowr);
	        
	        //END IMAGES /////////////////////////////////////////////////////////
			
	        if (isgray)
	        	placeholder.performAction(new BackgroundColorCallback(bColor, Color.WHITE, placeholder));
	        else
	        	placeholder.performAction(new BackgroundColorCallback(bColor, Color.GRAY, placeholder));
	        
	        
	        //Writing the 4 arrows
	        placeholder.addVSpace(40);
	        placeholder.write(upl);
	        placeholder.addHSpace(width-100);
	        placeholder.write(upr);
	        placeholder.addHSpace(-width);
	        
	        //Logo
	        placeholder.addVSpace(height-height/2-83);
	        placeholder.addHSpace(width-width/2-25);
	        placeholder.write(ipwater);
	        
	        //Size Label
	        placeholder.addHSpace(-61);
	        placeholder.addVSpace(30);
	        placeholder.withFont(SegUIsb).write(width+"x"+height);
	        placeholder.addVSpace(-30);
	        placeholder.addHSpace(61);
	        
	        
	        //Fixing H/V for low arrows
	        placeholder.addHSpace(-width+width/2-101);
	        placeholder.addVSpace(height/2+18);
	      
	        

	        placeholder.write(lowl);
	        placeholder.addHSpace(width-102);
	        placeholder.write(lowr);
	        

	        
	        ImageWriter imageWriter = ImageWriterFactory.getImageWriter(ImageType.PNG);
	        imageWriter.writeImageToFile(placeholder, new File("placehold.png"));
	    }
	}


