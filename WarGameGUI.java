/* Kaitlyn Mayberry
   CS 110
   WarGameGUI Class
*/

/**
   This program creates the war card game in a GUI interface.
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class WarGameGUI extends JFrame implements ActionListener
{
   private WarGame game; //war game
   private JPanel headerPanel, buttonPanel, gamePanel; //panels
   private JLabel title, status, player, computer, gameInfo; //labels
   private JButton play, exitGame; //buttons
   private Timer warTimer, playTimer; //timers
   private int timerCount, warCount; //counts
   private Card[] cards; //holds cards to be displayed in gui
   
   public static void main(String [] args)
   {
      new WarGameGUI();
   }
   
   /**
   *   WarGameGUI constructor sets up a GUI for a war game
   */
   public WarGameGUI()
   {
      //super constructor
      super("War Card Game");
      
      //set default close operation
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      
      //set GUI layout
      setLayout(new BorderLayout());
      
      //new war game
      game = new WarGame();
      
      //timer
      warTimer = new Timer(500, this);
      warTimer.setRepeats(true);
      
      //timer for Play button
      playTimer = new Timer(500, this);
      playTimer.setRepeats(true);
      
      //create header panel
      headerPanel = new JPanel(new BorderLayout());
      headerPanel.setBackground(Color.BLUE);
      //set title bar
      title = new JLabel("War Card Game", SwingConstants.CENTER);
      title.setFont(new Font("HELVETICA", Font.PLAIN, 32));
      headerPanel.add(title, BorderLayout.NORTH);
      //set status bar
      status = new JLabel(" ", SwingConstants.CENTER);
      status.setFont(new Font("HELVETICA", Font.PLAIN, 18));
      headerPanel.add(status, BorderLayout.CENTER);
      add(headerPanel, BorderLayout.NORTH);
      //set game info bar
      gameInfo = new JLabel("", SwingConstants.CENTER);
      gameInfo.setFont(new Font("HELVETICA", Font.PLAIN, 18));
      headerPanel.add(gameInfo, BorderLayout.SOUTH);
      updateGameInfo();
      
      //create button panel
      buttonPanel = new JPanel(new GridLayout(4,1));
      buttonPanel.setBackground(Color.BLUE);
      //create play button
      play = new JButton("Play");
      play.addActionListener(this);
      buttonPanel.add(play);
      //create exit game button
      exitGame = new JButton("Exit Game");
      exitGame.addActionListener(this);
      buttonPanel.add(exitGame);
      add(buttonPanel, BorderLayout.CENTER);
      
      //create game panel
      gamePanel = new JPanel(new GridLayout(1,2));
      gamePanel.setBackground(Color.BLUE);
      player = new JLabel(new ImageIcon("cardPics/back.jpg"));
      gamePanel.add(player);
      computer = new JLabel(new ImageIcon("cardPics/back.jpg"));
      gamePanel.add(computer);
      add(gamePanel, BorderLayout.SOUTH);
      
      //pack GUI and make visible
      pack();
      setVisible(true);
   }

   /**
   *   getImagePath method displays the image of a card
   *   @return filepath Image of card
   */
   private String getImagePath(Card c)
   {
      String filepath = "cardPics/";
      switch (c.getRank())
      {
         case Card.ACE:
            filepath += "ace";
            break;
         case 2:
            filepath += "2";
            break;
         case 3:
            filepath += "3";
            break;
         case 4:
            filepath += "4";
            break;
         case 5:
            filepath += "5";
            break;
         case 6:
            filepath += "6";
            break;
         case 7:
            filepath += "7";
            break;
         case 8:
            filepath += "8";
            break;
         case 9:
            filepath += "9";
            break;
         case 10:
            filepath += "10";
            break;
         case Card.JACK:
            filepath += "jack";
            break;
         case Card.QUEEN:
            filepath += "queen";
            break;
         case Card.KING:
            filepath += "king";
            break;
      }
      switch (c.getSuit())
      {
         case Card.SPADES:
            filepath += "s.jpg";
            break;
         case Card.CLUBS:
            filepath += "c.jpg";
            break;
         case Card.HEARTS:
            filepath += "h.jpg";
            break;
         case Card.DIAMONDS:
            filepath += "d.jpg";
            break;
      }
      return filepath;
   }

   /**
   *   updateGameInfo method displays player's and computer's scores as well as number of rounds played
   *   @return string with player's and computer's scores and number of rounds played
   */
   private void updateGameInfo()
   {
      gameInfo.setText("Player: " + game.getCardNumbers()[0] + "    Computer: " + game.getCardNumbers()[1] + 
         "    Rounds Played: " + game.getRoundCount());
   }

   /**
   *   checkforWinner method checks if there is a winner
   *   @return string with winner of war game
   */
   private void checkForWinner()
   {
      if (game.checkForWinner() != 0)
      {
         play.setEnabled(false);
         if (game.checkForWinner() > 0)
         {
            status.setText("Player won the game");
         }
         else if (game.checkForWinner() < 0)
         {
            status.setText("Computer won the game");
         }
      }
   }

   /**
   *   actionPerformed method plays or exits game
   *   @return play or exit game
   */
   public void actionPerformed (ActionEvent e)
   {
      if (e.getSource() == play)
      {
         play.setEnabled(false);
         playTimer.start();
      }
      else if (e.getSource() == exitGame)
      {
         System.exit(0);
      }
      else if (e.getSource() == warTimer)
      {
         timerCount++;
         
         //for even iterations card faces up
         if (timerCount % 2 == 0)
         {
            player.setIcon(new ImageIcon(getImagePath(cards[timerCount*2])));
            computer.setIcon(new ImageIcon(getImagePath(cards[timerCount*2+1])));
            if (timerCount >= warCount * 2)
            {
               warTimer.stop();
               updateGameInfo();
               play.setEnabled(true);
               checkForWinner();
            }
         }
         
         //for odd iterations card faces down
         else
         {
            player.setIcon(new ImageIcon("cardPics/back.jpg"));
            computer.setIcon(new ImageIcon("cardPics/back.jpg"));
         }
      }
      else if (e.getSource() == playTimer)
      {
         if (game.checkForWinner() == 0)
         {
            String statusText;
            cards = game.warRound();
            player.setIcon(new ImageIcon(getImagePath(cards[cards.length-2])));
            computer.setIcon(new ImageIcon(getImagePath(cards[cards.length-1])));
            updateGameInfo();
         }
         else
         {
            playTimer.stop();
            checkForWinner();
         }
      }
   }
}