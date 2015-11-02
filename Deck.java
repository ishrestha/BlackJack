// This is for shuffling a deck of cards. This class will shuffle the deck of cards
//Ivaan Shrestha
// October 31 2014


import java.awt.image.BufferedImage; 	// need for buffered image
import javax.imageio.ImageIO;			// needed for ImageIO.read, to find the file
import java.io.File;					// needed for File, to open the file
import javax.swing.*;					// needed for JLabel
import java.awt.BorderLayout;			// needed for BorderLayout, but only for main debugging


public class Deck {

   public String[] cards;// new array of Strings so that i can store the card suit and value in here
   private static final int NUM = 52; // number of the cards
   private static final int NUMSUITS = 4;
   private Card newCard;// new card 
   private Card front;
   private Card rear;
   
   
   public Deck(){
      cards = new String[NUM]; //new array of srting String with assigned constant NUM
      //newCard = new Card(); //initializing newCard type card
      for (int i=0; i < NUM; i++) { //opening for statement
         cards[i] = "No Cards";  // assigning value for the card array to No cards
             
      }
      front = new Card();// rear of the list
      rear = front;//front and rear pointing dummy   
   }
   
   public boolean add(int num, int suit) { // this method is like enqueue method but with two variables
      Card newCard = new Card();/// declaring a new card
     
     //temp = front.getNext();
      if (newCard == null) {// checking if it has memory or not
			// out of memory
			return false;
	   } 
      else {
			// filling in the data for the newest node
			newCard.setCard(num); 
         newCard.setSuit(suit);
         newCard.setNext(null); // setting the next value of new Node to null
         rear.setNext(newCard);//setting the next value of rear pointer to newNode
         rear = newCard; // moving rear itself to point to the new Node            
			return true; // returning true value, data was added
	   }
   
   }
   
   public Card del() {
      Card temp; // declaring a new temp variable
      temp = front.getNext();// gettting started
      Card data; // declaring a varibale and initialzing it to 0
      if( temp == null) {
         //underflow error
         return null;// returns null 
      }  
      else {
         //data = temp.getCard();// gets data from deleted node
         front.setNext(temp.getNext());// sets the next value for front to node after deleted node
         temp.setNext(null); // sets the deleted node variable to null
         if (front.getNext() == null) {
            rear = front; // moving rear since it is empty
         }
         return temp; // returns the deleted data
      
      }
   }
   
   public boolean fullDeck(){
   
      //trying to add all the 52 cards to the deck. I am trying to do it as a linked list but something is not working      
      for (int j=0; j<13; j++) { //loop for card values
         for (int i=0; i< NUMSUITS; i++) {//loop for suit values
            
            this.add(j,i);//set the card number and suit
         } 
      }   
 
     return true; // if full deck was created
   }
    public boolean quarterDeck() {//quarter deck
      
      for (int i=0; i<13; i++) {//loop
         this.add(i, 3);//adds card with same suit
      
      }
      
      return true;//returns true
    } 
    
   public int shuffle(){
      // here after i can fix the constructor i will add the card value and suit to array and shuffle the array.
      Card temp; //declaring temp card
      Card previous;// declaring previous card
      int ranNum = 0;
      for(int j =0; j <150; j++) {// 100 times
         int value = (int)(Math.random()*100); // random number generated
         ranNum = value%52; //dividing random number by 52 and remainder stored in ranNum
         //ranNum=12;
         temp = front.getNext();//stores next value after front 
         previous = front;// sets previous to front
         int cardNum = 0;
         int suitNum = 0; 
         for (int i=0; i<ranNum; i++) {
            temp = temp.getNext();//gets next
            previous = previous.getNext();//gets next
         }  
         //System.out.println(temp);  
         cardNum = temp.getCard();//gets card value
         suitNum = temp.getSuit(); //gers suit value
         previous.setNext(temp.getNext());//sets previous to next
         if(previous.getNext() == null) {
            rear = previous;//adding back card
         }   
         this.add(cardNum, suitNum);//setting the card value
         
      }
      return ranNum; //returning Num
         
   }
   //// toString ////
	public String toString() {
		String outString = new String("The deck contains: \n");	// output string
		Card temp;							// a temporary reference to a card
		
		// start at the beginning
		temp = front.getNext();
		
		// if the list is empty, say so
		if (temp == null) {
			outString += "nothing"; //return nothing as list is empty
			return outString;
		}
		
		// loop until we reach the end 
		while (temp != null) {
			// visit the node
			//this checks if the card is any face card or an ace.
         if (temp.getCard() == 0){
            outString += "Ace of "; // converting 0 to Ace
         }
         else if (temp.getCard() == 10){//converting 10 Jack
            outString += "Jack of ";
         }
         else if (temp.getCard() == 11){//Converting 11 to Queen
            outString += "Queen of ";
         }
         else if (temp.getCard() == 12){//Converting 12 to queen
            outString += "King of ";
         }
         else {
            outString += temp.getCard()+ 1 + " of";//storing value of card
         }
                 

         // this converts the suit number to values
         if(temp.getSuit() == 0) { 
			outString +=" CLUB" + "\n "; //converting numbers to values
         }
         else if(temp.getSuit() == 1) { 
			outString +=" SPADE" + " \n ";   //converting numbers to values
         }
         else if(temp.getSuit() == 2) { 
			outString +=" HEART" + "\n ";   //converting numbers to values
         }
         else if(temp.getSuit() == 3) { 
			outString +=" DIAMOND" + " \n";  //converting numbers to values         }
         }         
         // move to the next node
			temp = temp.getNext();		// move to next item
		}
		
      
		return outString; //returns outstring
	}

   //main
   public static void main (String[] args) {
   
      Deck newDeck = new Deck(); // creating a new deck
      //testing the Deck class
      newDeck.quarterDeck();  
      System.out.println(newDeck); // printing to see the new deck	
      System.out.println(newDeck.shuffle());	
      System.out.println(newDeck); 	
   
   
   }



}