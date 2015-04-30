/* Kaitlyn Mayberry
   CS 110
   WarGame Class
*/

/**
   This program creates the war card game. It checks if there is a 
   winner, counts the number of rounds played, and counts the number 
   of cards player 1 and player 2 have left
*/

import java.util.ArrayList;

public class WarGame
{
   Deck player1; //create deck for player 1
   Deck player2; //create deck for player 2
   int roundCount; //to hold number of rounds played
   
   /**
   *  default constructor
   */
   public WarGame()
   {
      player1 = new Deck(); //create new deck for player 1
      player2 = player1.halfDeck(); //half of deck is used
      roundCount = 0; //count starts at 0 before round begins
   }
   
   /**
   *   warRound method plays a round of war
   *   @return out Array of cards played during the round
   */
   public Card[] warRound()
   {
      Card[] out; //create array to hold cards
      if (checkForWinner() == 0) //continue round while there is no winner
      {
         ArrayList<Card> player1Card, player2Card; //first half from player 1, second half from player 2
         player1Card = new ArrayList<Card>(); //create ArrayList of cards for player 1
         player2Card = new ArrayList<Card>(); //create ArrayList of cards for player 2
         boolean isWar = true; //declare war
         while (isWar)
         {
            player1Card.add(0, player1.draw()); //player 1 deals first card facedown
            player2Card.add(0, player2.draw()); //player 2 deals first card facedown
            
            //player 1's card is higher than player 2's (player 1 wins)
            if (player1Card.get(0).compareTo(player2Card.get(0)) > 0)
            {
               player1.addCard(player2Card); //player 1 takes player 2's card
               player1.addCard(player1Card); //player takes their card
               isWar = false; //war is over
            }
            
            //player 1's card is less than player 2's (player 2 wins)
            else if (player1Card.get(0).compareTo(player2Card.get(0)) < 0)
            {
               player2.addCard(player1Card); //player 2 takes player 1's card
               player2.addCard(player2Card); //player 2 takes their card
               isWar = false; //war is over
            }
            
            //there is a tie
            else
            {
               //player 1 does not have enough cards for war
               if (player1.cardsLeft() < 2)
               {
                  if (!player1.isEmpty()) //player 1 is not empty
                  {
                     player1Card.add(0, player1.draw()); //player 1 deals first card facedown
                     player2Card.add(0, player2.draw()); //player 2 deals first card facedown
                  }
                  player2.addCard(player1Card); //player 2 takes player 1's card
                  player2.addCard(player2Card); //player 2 takes their card
                  isWar = false; //war is over
               }
               
               //player 2 does not have enough cards for war
               else if (player2.cardsLeft() < 2)
               {
                  if (!player2.isEmpty()) //player 2 is not empty
                  {
                     player1Card.add(0, player1.draw()); //player 1 deals first card facedown
                     player2Card.add(0, player2.draw()); //player 2 deals first card facedown
                  }
                  player1.addCard(player2Card); //player 1 takes player 2's card
                  player1.addCard(player1Card); //player 1 takes their card
                  isWar = false; //war is over
               }
               else
               {
                  player1Card.add(0, player1.draw()); //player 1 deals first card facedown
                  player2Card.add(0, player2.draw()); //player 2 deals first card facedown
               }
            }
         }
         roundCount++; //round count is incremented
         out = new Card[player1Card.size() + player2Card.size()];
         int j = 0;
         for (int i = out.length/2-1; i >= 0; i--)
         {
            out[i*2] = player1Card.get(j);
            out[i*2+1] = player2Card.get(j);
            j++; //increment j
         }
         return out; //return cards
      }
      else //there is a winner
      {
         out = new Card[0];
         return out; //return cards
      }
   }
   
   /**
   *   checkForWinner method sees if there is a winner
   *   @return 1 if the player 1 won, -1 if player 2 won, 0 if there is no winner
   */
   public int checkForWinner()
   {
      //player 1 won
      if (player2.isEmpty()) //player 2 is out of cards
      {
         return 1;
      }
      
      //player 2 won
      else if (player1.isEmpty()) //player 1 is out of cards
      {
         return -1;
      }
      
      //no winner
      else //player 1 and player 2 still have cards
      {
         return 0;
      }
   }
   
   /**
   *   getRoundCount method returns the number of rounds played
   *   @return round count
   */
   public int getRoundCount()
   {
      return roundCount;
   }
   
   /**
   *   getCardNumbers method returns the number of cards player 1 and player 2 have left
   *   @return numbers Two element array displaying number of cards players have left
   */
   public int[] getCardNumbers()
   {
      int[] numbers = new int[2]; //two element array
      numbers[0] = player1.cardsLeft(); //player 1's cards left in first element
      numbers[1] = player2.cardsLeft(); //player 2's cards left in second element
      return numbers; //return number of cards player 1 and player 2 have left
   }
   
   /**
   *   toString method returns a string with the number of cards players have and rounds played
   *   @return string with the number of cards players have and rounds played
   */
   public String toString()
   {
      return "Player: " + getCardNumbers()[0] + " Computer: " + getCardNumbers()[1] +
         "\nRounds played: " + getRoundCount() + "\n";
   }
}