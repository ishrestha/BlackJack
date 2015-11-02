/* Node:  A class to represent a card
 * 
 * To interface with the image file "classic-playing-cards.png", we need to have the
 * suits and face values in the following order, so that we can extract the correct
 * subimage:
 * 
 * Suits:      0 = club, 1 = spade, 2 = heart, 3 = diamond
 * Face Value: 0 = ace, 1 = two card, 2 = three card  (off by one!) ... 9 = ten card
 *             10 = jack, 11 = queen, 12 = king
 * 
 * Jennifer Coy, based partially on McAllister book code
 * November 2013
 * 
 * Credit:
 * Playing card images from http://www.jfitz.com/, free for personal use.
 * BufferedImage code adapted from from 
 * http://stackoverflow.com/questions/621835/how-to-extract-part-of-this-image-in-java
 */

import java.awt.image.BufferedImage; 	// need for buffered image
import javax.imageio.ImageIO;			// needed for ImageIO.read, to find the file
import java.io.File;					// needed for File, to open the file
import javax.swing.*;					// needed for JLabel
import java.awt.BorderLayout;			// needed for BorderLayout, but only for main debugging

public class Card {
	
	//// card definitions ////
	public static final int DUMMY = 9999999;	// a constant indicating a dummy node
	public static final int CLUB = 0;		// clubs
	public static final int SPADE = 1;		// spades
	public static final int HEART = 2;		// hearts
	public static final int DIAMOND = 3;	// diamonds
	public static final String IMAGEFILENAME = "classic-playing-cards.png";	// image file
	public static final int CARDWIDTH = 73;	// card width
	public static final int CARDHEIGHT = 98; 	// card height
	
	//// class member ////
	private static BufferedImage img = null;

	//// data members ////
	private int suit;			// the suit  
	private int value;			// the card value, 0=ace, 1=deuce, 2=3 ... 10=9, Jack=10, Queen=11, King=12
	private JLabel image;		// the card image file
	private Card next;			// a link to the next node
	
	//// Constructors ////
	public Card() {
		// initialize to be a dummy node by default
		suit = DUMMY;
		value = DUMMY;
		image = null;
		next = null;
		
		// check to see if the buffered image is loaded
		if (img == null) {
			// not yet loaded, so load it now!
			loadBufferedImage();
		}
	}
	
	//// Set up the buffered image
	public void loadBufferedImage() {
		try {
			// get the path to the image file name
		    //java.net.URL imgURL = getClass().getResource(IMAGEFILENAME);
		    //if (imgURL != null) {
		    	// read the image file, using the path
		    	img = ImageIO.read(new File(IMAGEFILENAME));
		    // else {
		    //    System.out.println("Couldn't find file: " + IMAGEFILENAME);
		   // }
		} catch (Exception e) {
			System.out.println("Error opening file.");
		    image = new JLabel("Error");
		}
	}

	/**
	 * @return the suit
	 */
	public int getSuit() {
		return suit;
	}

	/**
	 * @return the card
	 */
	public int getCard() {
		return value;
	}

	/**
	 * @return the card's image
	 */
	public JLabel getImage() {
	    // as long as it's not a dummy card
	    if (value != DUMMY && suit != DUMMY) {
	    	// create a JLabel for display, for just the card that we want
	    	image = new JLabel(new ImageIcon(img.getSubimage(CARDWIDTH*value,CARDHEIGHT*suit,CARDWIDTH,CARDHEIGHT)));
	    } else {
	    	// oops, error!  just display the text "DUMMY"
	    	image = new JLabel("DUMMY");
	    }
		return image;
	}
	
	/**
	 * @return the next
	 */
	public Card getNext() {
		return next;
	}

	/**
	 * @param suit the suit to set
	 */
	public void setSuit(int suit) {
		this.suit = suit;
	}

	/**
	 * @param card the card to set
	 */
	public void setCard(int card) {
		this.value = card;
	}
	
	/**
	 * @param next the next to set
	 */
	public void setNext(Card next) {
		this.next = next;
	}

	//// deepCopy ////
	/* create a duplicate copy of this node */
	public Card deepCopy() {
		Card copy = new Card();  // the duplicate node
		
		// fill contents
		copy.setSuit(suit);  // duplicate the data
		copy.setCard(value);
		// do not need to set the image, since we recalculate it each time
		copy.setNext(null);  // set next to be a null pointer, this node is not in a list
		
		// return the copy
		return copy;
	}

	//// Auto generate toString() using Eclipse ////
	public String toString() {
		return "Node [suit=" + suit + ", card =" + value + ", next=" + next + "]";
	}
	
	//// main() for testing ////
	public static void main(String[] args) {
		
		// create a card:  the 3 of diamonds
		// REMEMBER:  the card # is off-by-one from the face value
		Card firstnode = new Card();
		firstnode.setSuit(Card.DIAMOND);
		firstnode.setCard(2);
		System.out.println("The first node is: " + firstnode);
		
		// this is a super-basic JFrame, used only for really basic testing
		JFrame frame = new JFrame("Card");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(firstnode.getImage(), BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);		
	}
}
