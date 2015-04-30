/* Kaitlyn Mayberry
   CS 110
   Card Class
*/

/**
   This program represents a single playing card from a standard 52 card
   deck. Each card has a rank and a suit. Two cards are equal if they contain
   the same rank.
*/

public class Card
{
   public final static int SPADES = 1, CLUBS = 2, HEARTS = 3, DIAMONDS = 4; //suits
   public final static int JACK = 11, QUEEN = 12, KING = 13, ACE = 14; //ranks for non-numbered cards
   private int suit; //suit cannot be changed after card is created
   private int rank; //rank cannot be changed after card is created
   
   /**
   *  constructor for card's rank and suit
   *  @param theRank The card's rank
   *  @param theSuit The card's suit
   */
   public Card (int theSuit, int theRank) 
   {
      rank = theRank;
      suit = theSuit;
   }
      
   /**
   *  getSuit method displays the card's suit
   *  @return suit The card's suit
   */
   public int getSuit()
   {
      return suit;
   }
   
   /**
   *  getRank method displays the card's rank
   *  @return rank The card's rank
   */
   public int getRank()
   {
      return rank;
   }
   
   /** 
   *  toString method returns the string rank of the card
   *  @return totalString String with the rank and suit of the card
   */
   public String toString()
   {
      String suitString = "Card"; //to hold suit
      String rankString = "Card"; //to hold rank
      
      //cases for the suit
      switch (suit)
      {
         case SPADES: suitString = "s";
         break;
         
         case CLUBS: suitString = "c";
         break;
         
         case HEARTS: suitString = "h";
         break;
         
         case DIAMONDS: suitString = "d";
         break;
      }
      
      // cases for the rank
      switch (rank)
      {
         case 1: rankString = "ace";
         break;
         
         case 2: rankString = "2";
         break;
         
         case 3: rankString = "3";
         break;
         
         case 4: rankString = "4";
         break;
         
         case 5: rankString = "5";
         break;
         
         case 6: rankString = "6";
         break;
         
         case 7: rankString = "7";
         break;
         
         case 8: rankString = "8";
         break;
         
         case 9: rankString = "9";
         break;
         
         case 10: rankString = "10";
         break;
         
         case 11: rankString = "jack";
         break;
         
         case 12: rankString = "queen";
         break;
         
         case 13: rankString = "king";
         break; 
      }
      String totalString = suitString + rankString;
      return totalString;
   } 
   
   /**
   *  equals method compares card's rank with another card's rank
   *  @param otherCard
   *  @return True if cards are equal and false if they aren't
   */
   public boolean equals(Card otherCard)
   {
      if (getRank() == otherCard.getRank())
      {
         return true;
      }
      else
      {
         return false;
      }
   }
   
   /**
   *  compareTo method compares card's rank with another card's rank
   *  @param otherCard
   *  @return 1 if card is greater than the other card, -1 if card is less than
   *  the other card, and 0 if the cards are equal
   */
   public int compareTo(Card otherCard)
   {
      if (getRank() > otherCard.getRank())
      {
         return 1;
      }
      else if (getRank() < otherCard.getRank())
      {
         return -1;
      }
      else
      {
         return 0;
      }
   }
}