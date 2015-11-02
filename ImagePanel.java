// this class is used to add the image back ground 

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class ImagePanel extends JPanel {//class called Image Panel

  private Image img; //data memebers

  public ImagePanel(String img) {
    this(new ImageIcon(img).getImage());// gets the image from the source
  }

  public ImagePanel(Image img) {
    this.img = img;//adds the image
  }

  public void paintComponent(Graphics g) {
    g.drawImage(img, 0, 0, null);// draws the image
  }

}
