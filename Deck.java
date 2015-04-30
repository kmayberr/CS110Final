/* Kaitlyn Mayberry
   CS 110
   Deck Class
*/

/**
   This program represents a deck of 52 playing card. Cards can be added, drawn
   or shuffled. The deck can be split in half. One can also check how many
   cards are left in the deck or if it is empty.
*/

import java.util.Random;
import java.util.ArrayList;

public class Deck 
{
   private final int NUM_CARDS = 52; //deck has 52 cards
   private ArrayList<Card> deck;
   
   /**
   *   default constructor
   */
   public Deck()
   {
      newDeck();
      shuffle();
   }
   
   /**
   *   The newDeck method creates a new of cards
   */
   public void newDeck()
   {
      deck = new ArrayList<Card>(NUM_CARDS);
      for (int rank = 2; rank <= Card.ACE; rank++)
      {
         for (int suit = Card.SPADES; suit <= Card.DIAMONDS; suit++)
         {
           deck.add(new Card(suit, rank));
         }
      }
   }
   
   /**
   *   addCard method adds a card to the bottom of the deck
   *   @param otherCard The card that is added to the bottom of the deck
   */
   public void addCard(Card otherCard)
   {
      deck.add(otherCard);
   }
   
   /**
   *   addCard method adds multiple cards to the bottom of the deck
   *   @param cards The collection of cards to be added to the deck
   */
   public void addCard(ArrayList<Card> cards)
   {
      for (Card card : cards)
      {
         deck.add(card);
      }
   }
   
   /**
   *   draw method removes the top card from the deck
   *   @return nextCard The card at the top of the deck
   */
   public Card draw()
   {
      Card nextCard = deck.get(0);
      deck.remove(0);
      return nextCard;
   }
   
   /**
   *   shuffle method shuffles the full deck
   */
   public void shuffle()
   {
      int randNum;
      Card temp;
      Random random = new Random();
      for (int i = 0; i < deck.size(); i++)
      {
         randNum = random.nextInt(deck.size());
         temp = deck.get(i);
         deck.set(i,deck.get(randNum));
         deck.set(randNum,temp);
      }      
   }
   
   /**
   *   isEmpty method checks if the deck is empty
   *   @return True if the deck is empty or false if it isn't
   */
   public boolean isEmpty()
   {
      if (deck.size() == 0)
      {
         return true;
      }
      
      else
      {
         return false;
      }
   }
   
   /**
   *   cardsLeft method returns the number of cards in the deck
   *   @return Number of cards in the deck
   */
   public int cardsLeft()
   {  
      return deck.size();
   }
   
   /**
   *   halfDeck method splits the deck in half
   *   @return otherDeck The other half of the deck
   */
   public Deck halfDeck()
   {
      Deck otherDeck = new Deck();
      for (int i = 1; i <= NUM_CARDS; i++)
      {
         otherDeck.draw();
      }
      for (int i = 0; i < (NUM_CARDS/2); i++)
      {
         otherDeck.addCard(deck.remove(0));
      }
      return otherDeck;
   }
   
   /**
   *   toString method returns a string with the cards in the deck
   *   @return string A string with all the cards in the deck
   */
   public String toString()
   {
      String string = "";
      for (Card card : deck)
      {
         string += card.toString() + "\n";
      }
      return string;
   }
}