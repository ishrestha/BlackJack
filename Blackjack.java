//This class called Blackjack should have a actual GUI where the game is actually played
//Ivaan Shrestha
//November 12 2014
import java.awt.image.BufferedImage; 	// need for buffered image
import javax.imageio.ImageIO;			// needed for ImageIO.read, to find the file
import javax.swing.*;					// needed for JLabel
import java.awt.BorderLayout;			// needed for BorderLayout, but only for main debugging
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.net.*; 
import java.io.*;


public class Blackjack extends JFrame implements ActionListener {

   private JLabel imageBase, lImage, text, dealerScore, playerScore, playerName, winImage; // images for cards and label text
   private JButton button, button1, button2, button3; // three buttons for hit,  and deal
   private ImageIcon image, imageWin, imageLose; // image icon
   private Card first, second, third, fourth;
   private Card[] newPlayerCard;//declaring array of card
   private Card[] newDealerCard; // declaring array of new card 
   private static final int FRAME_WIDTH = 800; // width for frame
   private static final int FRAME_HEIGHT   = 600;//height for frame
   private static final int FRAME_X_ORIGIN = 100;// x coordinate origin for frame
   private static final int FRAME_Y_ORIGIN = 100;// y coordinate origin for frame
   private Container contentPane; //for a content pane
   private Hand player, dealer;//declaring two new hands
   private JPanel panelPlayer, panelDealer, panelButton;// panel to store player and dealer hand and button
   private Deck newDeck;//creates a new deck
   private int scorePlayer, scoreDealer, countPlayer, countDealer;//counts how many times the card has been dealt after first four
   private String storePlayer, storeScore;// stores the player score
   public static final int NUM = 26; // constant for handling cards that has already been dealt
   private AudioClip clip1, clip2, clip3, clip4, clip5; // for a audio clips
   private Color myColor;
   
   
   public Blackjack() {
      newPlayerCard = new Card[NUM];//initializing a new card for dealing after first four
      newDealerCard= new Card[NUM];// initializing a new card for dealing after first four
      for(int i =1; i < NUM; i++){// adding null values to initialize cards
         newPlayerCard[i] = null;//null
         newDealerCard[i] = null;//null
      
      } 
      setSize(FRAME_WIDTH, FRAME_HEIGHT); // setting up height and width
      setTitle("Black Jack");//setitng title of frame to Black Jack
      setLocation(FRAME_X_ORIGIN, FRAME_Y_ORIGIN);// setting up origin
      
      ImagePanel panel = new ImagePanel(new ImageIcon("imageBackground.jpg").getImage());
      myColor= new Color(0,100,0);
      contentPane = getContentPane( );// getting the content pane
      contentPane.setBackground(Color.GREEN); // setting back ground color
      contentPane.setLayout(new BorderLayout());//setting layout
      
      String input = new String(); // declaring new string as input to store the value input by user
      input = JOptionPane.showInputDialog(null, "Please input your name: ");// Asks for numbers and operators in JOptionPane window and stores it in variable input
      if(input == null) { // when input is null
         JOptionPane.showMessageDialog(null, "No name entered", "Null value", 
         JOptionPane.WARNING_MESSAGE, null); // displays user hit null throught JOptionPane window
         
      }

      countPlayer = 0; //initialzing the value
      countDealer = 0; //initialzing the value
      
      player = new Hand(); // new Hand
      dealer = new Hand();// new Hand
      
      newDeck = new Deck();// new deck
      newDeck.fullDeck(); //adding cards to deck to make 52 cards
      newDeck.fullDeck();
      newDeck.shuffle();// shuffling the deck
      
      text = new JLabel("Dealer's Cards"); // new label with dealers name
      
      first = new Card(); // new card player
		first = newDeck.del();//dequeing the card from deck to player
      
            
      second = new Card(); // new card to dealer
      second= newDeck.del();// //dequeing the card from deck to dealer
		
      third = new Card();// new card to player
      third = newDeck.del();// //dequeing the card from deck to player
            
      fourth = new Card();// new card to dealer
      fourth = newDeck.del(); ////dequeing the card from deck to dealer

      panelPlayer = new JPanel(); // new JPanel
      panelDealer = new JPanel(); // new JPanel
      panelButton = new JPanel();
      //panelButton.setLayout(new BorderLayout());
      
      
      panelDealer.add(text);// adding the JLabel text
      
      
      image = new ImageIcon("Cards.png"); // adding to image to image icon
      lImage = new JLabel(image);//adding image to label
      //lImage.setSize(50, 50);// setting size for image
      contentPane.add(lImage, BorderLayout.WEST);//adding the label to contentpane
      
      imageWin = new ImageIcon("winner.jpg");
      imageLose= new ImageIcon("loser.jpg"); 
      winImage = new JLabel(imageWin);

      player.plus(first);//adding card to hand
      player.plus(third);//adding card to hand
      dealer.plus(second);//adding card to hand
      dealer.plus(fourth);//adding card to hand
      
      scorePlayer = 0; //initializing the scorePlayer to 0
      scoreDealer =0;//initializing the scorePlayer to 0
      
      scorePlayer = player.score(); //score for player
      scoreDealer = dealer.score();//score for dealer
      
      String sPlayer = Integer.toString(scorePlayer);//converting Integer to String
      String sDealer = Integer.toString(scoreDealer);//converting Integer to String
      playerScore = new JLabel("Score: " + sPlayer);//assigning text value to JLabel
      dealerScore = new JLabel("Score: " + sDealer);//assigning text value to JLabel
      playerName = new JLabel (input + "'s");//assigning values to player name

            
      
      //System.out.println(scorePlayer);
      panelPlayer.add(playerName);//adding players name
      panelPlayer.add(playerScore);//adding score of the player
      //panelDealer.add(dealerScore);
     
      
      
      panelPlayer.add(baseImage());//adding face down card to the player panel
      panelDealer.add(baseImage());//adding face down card to the dealer panel
      panelPlayer.add(baseImage());// adding face down card to the player panel
      panelDealer.add(fourth.getImage());//adding card to the panel
      
      
      
      button = new JButton("Hit");// Butotn with hit text
      button.setSize(10,10);
      button.addActionListener(this); //adding to the action listener
      button1 = new JButton("Stand");// button with stand text
      button1.addActionListener(this);// adding to the action listener
      button2 = new JButton("Show"); // butotn with show text
      button2.addActionListener(this);//adding to the action listener
      button3 = new JButton("Hide"); // butotn with show text
      button3.addActionListener(this);//adding to the action listener
     
     
      panelButton.add(button); //  button in JPanel
      panelButton.add(button1);//  button in JPanel
      panelButton.add(button2);// button in JPanel
      panelButton.add(button3);// button in JPanel  
      //panelButton.add(winImage);    
      
      button.setSize(10,10);
      // panel.add(text);
      contentPane.add(panelPlayer, BorderLayout.SOUTH);// adding the JPanel to the content pane south
      contentPane.add(panelDealer, BorderLayout.NORTH);// adding the JPanel to the content pane north
      contentPane.add(panelButton, BorderLayout.CENTER);// adding the JPanel to the content pane center
      panelPlayer.setSize(100,100); // size of the panel.
      panelDealer.setSize(100,100);// size of the panel
      panelPlayer.setBackground(Color.GREEN);//color of panel
      panelDealer.setBackground(Color.GREEN);//color of panel
      panelButton.setBackground(Color.GREEN);//color of panel
      
      URL url_1 = null;//initialzing url
      URL url_2 = null;//initialzing url
      URL url_3 = null;//initialzing url
      URL url_4 = null;//initialzing url

      try { 
         url_1 = new URL("file", "localhost", "tick.wav"); //location for url
         url_2 = new URL("file", "localhost", "tock.wav");//location for url
         url_3 = new URL("file", "localhost", "applause.wav");//location for url
         url_4 = new URL("file", "localhost", "lose.wav");//location for url

      }
      catch (Exception exception){ } // if file not found
      clip1 = JApplet.newAudioClip(url_1);//adding newAudioClip to clip1 from url_1
      clip2 = JApplet.newAudioClip(url_2);//adding newAudioClip to clip2 from url_2
      clip3 = JApplet.newAudioClip(url_3);//adding newAudioClip to clip3 from url_3
      clip4 = JApplet.newAudioClip(url_4);//adding newAudioClip to clip3 from url_3      
      
      clip2.loop();
      
      
       if (scorePlayer == 21) {//if first deal player score 21, player wins
          addAll(); // removing all in panel and then adding back
          clip3.play();
          JOptionPane.showMessageDialog(null, "Blackjack!! Congrats, you won", "Message",JOptionPane.INFORMATION_MESSAGE, imageWin);// showing message that they have won
          repeat();// loop the game                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
      }
      if (scoreDealer == 21) {//if first deal dealer score 21, player wins

         addAll();// removing all in panel and then adding back
         clip4.play();
         JOptionPane.showMessageDialog(null, "Blackjack for the dealer, you lose", "Message",JOptionPane.INFORMATION_MESSAGE, imageLose);// showing message that they have won
         repeat();//loop the game
      }
      if(scoreDealer ==21 && scorePlayer ==21) { // if both get 21 while dealing at first
         addAll();// removing all in panel and then adding back
         JOptionPane.showMessageDialog(null, "Game tied at 21");// showing message that its tied
         repeat();//loop the game

      }

      
      //register 'Exit upon closing' as a default close operation
      setDefaultCloseOperation( EXIT_ON_CLOSE );
   }
   
   
   public void actionPerformed(ActionEvent event) {  
      if (event.getSource() == button ){// when event is button 1
         clip1.play();
         countPlayer = countPlayer + 1;//adds 1 to countPLayer
         //newPlayerCard = new Card[NUM];
         newPlayerCard[countPlayer] = newDeck.del();//deque the card from deck and add it to player card
         panelPlayer.add(newPlayerCard[countPlayer].getImage());//gets image of that card
         
         int b = dealer.score();//dealers's score
         player.plus(newPlayerCard[countPlayer]);//adds the card to the hand
         int a = player.score();// gets player score
         System.out.println(a);//test purpose
         String sPlayer = Integer.toString(a);//converts the intrger score to String
         playerScore.setText("Score: " + sPlayer);//sets text of label
         panelPlayer.validate();// validates
         panelPlayer.repaint();//repaint          
         if (a >21) {// if score greater than 21 player loses
            addAll();//// removing all in panel and then adding back
            clip4.play();
            JOptionPane.showMessageDialog(null, "You got over 21, busted you lose. Dealer's score: " +b, "Message",JOptionPane.INFORMATION_MESSAGE, imageLose); //showing the result to user
            repeat();// loops the game
         }
         else if( a ==21) { // if score equal to 21 player loses
            addAll();//// removing all in panel and then adding back
            clip3.play();
            JOptionPane.showMessageDialog(null, "Congrats, you have a Blackjack. You won. Dealer's score: " + b, "Message",JOptionPane.INFORMATION_MESSAGE, imageWin);//showing the result to user
            repeat();   // loops the game         
         }

         System.out.println(player);//test purpose
         
         b = dealer.score();//dealer score
         if ( b < 17){//if less than 17
           countDealer = countDealer+1;//add counter
           newDealerCard[countDealer] = newDeck.del();//queue from the deck
           panelDealer.add(newDealerCard[countDealer].getImage());//get image
           dealer.plus(newDealerCard[countDealer]);//add card to hand
           b = dealer.score();//dealer score
           String sDealer = Integer.toString(b);//convert to string
           dealerScore.setText("Score: " + sDealer);//seting the score in label
           panelDealer.validate();//validating
           panelDealer.repaint(); // repianting
           if (b >21) {//if greater than 21
            addAll();// repaint and revalidate all
            clip3.play();
            JOptionPane.showMessageDialog(null, "Congrats, you won. Dealer has a score of " +b, "Message",JOptionPane.INFORMATION_MESSAGE, imageWin);// message showing you won
            repeat();  // loops the game
           }
           else if (b ==21) {//if 21
            addAll();//repaint and revalidate all
            clip4.play();
            JOptionPane.showMessageDialog(null, "Dealer got a Black Jack, you lose!! Dealer's score: " + b,"Message",JOptionPane.INFORMATION_MESSAGE, imageLose);//message showing you lost
            repeat();// loops the game
           }
            
         }
            
         //System.out.println(newPlayerCard);
        
         
      
     }     
    
     if(event.getSource() == button1 ){ // if event source is from button 1
       clip1.play();
       int b = dealer.score();//dealer score
       int a = player.score();//player score
       while( b < 17){//if less than 17
           
           countDealer = countDealer+1;//increase the count by 1
           newDealerCard[countDealer] = newDeck.del();// queue the card from deck
           panelDealer.add(newDealerCard[countDealer].getImage());//get image
           dealer.plus(newDealerCard[countDealer]);//add the card to hand
           b = dealer.score();//dealer score
           String sDealer = Integer.toString(b);// convert to string
           dealerScore.setText("Score: " + sDealer);//setting the label

           addAll();
           
         }
         
         String sDealer = Integer.toString(b);
         dealerScore.setText("Score: " + sDealer);
         panelDealer.validate();
         panelDealer.repaint();
         
           
         if (b >21) { //if greater than 21            
          addAll();//repaint and revalidate all
          sDealer = Integer.toString(b);
          dealerScore.setText("Score: " + sDealer);
          panelDealer.validate();
          panelDealer.repaint();
          clip3.play();    
          JOptionPane.showMessageDialog(null, "Congrats, you won. Dealer has a score of " +b,"Message",JOptionPane.INFORMATION_MESSAGE, imageWin);// show message you won
          repeat();// loops the game
               
         }
         if (b ==21) { //if less than 21
          addAll();//repaint and revalidate all
            sDealer = Integer.toString(b);
            dealerScore.setText("Score: " + sDealer);
            panelDealer.validate();
            panelDealer.repaint();

            clip4.play();  
            JOptionPane.showMessageDialog(null, "Dealer got a Black Jack, you lose!! Dealer's score: " +b, "Message",JOptionPane.INFORMATION_MESSAGE, imageLose);//show you lose
            repeat();// loops the game
             
         }
         if( b > a){// if b greater a
            addAll();//repaint and revalidate all
            sDealer = Integer.toString(b);
            dealerScore.setText("Score: " + sDealer);
            panelDealer.validate();
            panelDealer.repaint();

            clip4.play();  
            JOptionPane.showMessageDialog(null, "Dealer has better score of " + b + ". You lose","Message",JOptionPane.INFORMATION_MESSAGE, imageLose);//show you lose
            repeat();// loops the game
            
           }  
         if( a > b){// if a greater than b
            addAll();//repaint and revalidate all
            sDealer = Integer.toString(b);
            dealerScore.setText("Score: " + sDealer);
            panelDealer.validate();
            panelDealer.repaint();

            
            clip3.play();
            JOptionPane.showMessageDialog(null, "Congrats, you won. Dealer has a score of " +b,"Message",JOptionPane.INFORMATION_MESSAGE, imageWin);//show you won
            repeat();// loops the game
         
          }
         if( b == a){//if ais equal to b
            addAll();//repaint and revalidate
            sDealer = Integer.toString(b);
            dealerScore.setText("Score: " + sDealer);
            panelDealer.validate();
            panelDealer.repaint();

            JOptionPane.showMessageDialog(null, "Game tied as you and dealer has equal score of" + b);//show game tied
            repeat();// loops the game
            
          }  
                
        System.out.println(b);
        System.out.println(dealer);
        panelDealer.validate();//revalidate
        panelDealer.repaint();//repaint
 
        

     }
     
     if(event.getSource() == button2 ){ //if event source is button2
      clip1.play();
      showAll();//shows all card
     
     
     }
     
     if(event.getSource() == button3 ){ //if event source is button2
       clip1.play();
       hideAll();//hides all card
     
     
     }
     

    
   }
    public void showAll() {//shows all card
      panelPlayer.removeAll();//removes all card
      panelPlayer.add(playerScore);//adds player score
      panelPlayer.add(first.getImage());//adds first image
      panelPlayer.add(third.getImage());//adds third image
      if (countPlayer >0) {//if countPlayer is greater than 0
         for (int i =1; i<countPlayer+1; i++) {//loops through vailable cards
          panelPlayer.add(newPlayerCard[i].getImage());//adds to panel
         }

      
      }
      panelPlayer.validate();//revalidates
      panelPlayer.repaint();//repaint

      
    
    }
    
    public void hideAll() {
      panelPlayer.removeAll();//removes all card
      panelPlayer.add(playerScore);//adds player score
      panelPlayer.add(baseImage());//adds first image
      panelPlayer.add(baseImage());//adds third image
      
      if (countPlayer >0) {//if countPlayer is greater than 0
         for (int i =1; i<countPlayer+1; i++) {//loops through vailable cards
          panelPlayer.add(newPlayerCard[i].getImage());//adds to panel
         }

      
      }
      panelPlayer.validate();//revalidates
      panelPlayer.repaint();//repaint
      
      
       
    
    }
    public void addAll() {
      panelPlayer.removeAll();//removes all card
      panelPlayer.add(playerScore);//adds player score
      panelPlayer.add(first.getImage());//adds first image
      panelPlayer.add(third.getImage());//adds third image
      if (countPlayer >0){
         for (int i =1; i<countPlayer+1; i++) {//if countPlayer is greater than 0
            panelPlayer.add(newPlayerCard[i].getImage());//adds to panel
         }
      }   
      panelPlayer.validate();//revalidates
      panelPlayer.repaint();//repaints
      
      panelDealer.removeAll();//removes everything
      panelDealer.add(dealerScore);//adds delaer score
      panelDealer.add(second.getImage());//adds second image
      panelDealer.add(fourth.getImage());//adds third image
      //if(countDealer > 0) {
         for (int i =1; i<countDealer+1; i++) {//loops through cards
            panelDealer.add(newDealerCard[i].getImage());//adds to panel
         }
      //}   
      panelPlayer.validate();//repaint
      panelPlayer.repaint();//revalidates

      
    }
    
      
    
    
    public void repeat() {//for looping the game
    
      if (JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Message",
         JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) { //message showing if you wnat to play
            this.setVisible(false);// sets this frame to invisible
            this.dispose(); //disposes the frame
            clip2.stop();
            Blackjack blackjack = new Blackjack();// new Black Jack
            blackjack.setVisible(true);//sets the visibility to true
      }   
      else {//other wise
         System.exit(0);//exits the system
      }
    }
    
    
    public JLabel baseImage(){//base image of back of card
    
      imageBase = new JLabel(new ImageIcon("Cards.png"));//gets back image
      return imageBase; //returns image
    }  
    
    //main
    public static void main(String[] args) {
	
	      
		
		// this is a super-basic JFrame, used only for really basic testing
		
      Blackjack frame = new Blackjack();//new black jack frame

      frame.setVisible(true); //setting visibility is true
      
       
       //frame.getContentPane().add(panel);
       //frame.pack();               
     
  	}



}