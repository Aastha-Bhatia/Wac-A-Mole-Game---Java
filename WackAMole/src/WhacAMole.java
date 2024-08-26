
//imported all the libraries
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class WhacAMole {
   // dimentions of our game
   int boardWidth = 600;
   int boardHeight = 650; // 50 px for the text on the top which will show score.
   // title of the game
   JFrame frame = new JFrame("Mario: Whac A Mole");
   // panel for my text
   JLabel textLabel = new JLabel();
   JPanel textPanel = new JPanel();
   // panel for my game
   JPanel gamePanel = new JPanel();
   // 9 sized array
   JButton[] board = new JButton[9];
   // image icons declare
   ImageIcon moleIcon;
   ImageIcon plantIcon;
   // these will keep a record of the current tile
   JButton currMoleTile;
   JButton currPlantTile;
   // to place the mole and plant randomly
   Random random = new Random();
   // after a certain time, we want to move the mole and plant to the new tile, so
   // for this we need a timer.
   Timer setMoleTimer;
   Timer setPlantTimer;
   // score
   int score = 0;
   
   // constructor having properties
   WhacAMole() {
      // will set it at the end so that after all the components load, then our frame
      // becomes visible.
      // frame.setVisible(true);
      frame.setSize(boardWidth, boardHeight);
      ImageIcon icon = new ImageIcon(getClass().getResource("/monty.png"));
      frame.setIconImage(icon.getImage());
      // will be centered regardless of what the screen size is
      frame.setLocationRelativeTo(null);
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLayout(new BorderLayout());

      textPanel.setBackground(Color.BLACK);
      textLabel.setBackground(Color.BLACK);
      textLabel.setForeground(Color.decode("#FFFFFF"));

      textLabel.setFont(new Font("Arial", Font.PLAIN, 60));
      textLabel.setHorizontalAlignment(JLabel.CENTER);
      textLabel.setText("SCORE : 0");
      textLabel.setOpaque(true);
      textPanel.setLayout(new BorderLayout());
      textPanel.add(textLabel);
      frame.add(textPanel, BorderLayout.NORTH);

      gamePanel.setLayout(new GridLayout(3, 3));
      gamePanel.setBackground(Color.decode("#F5F5DC"));
      frame.add(gamePanel);

      // instead of directly getting the image icon from the source, we are going to
      // get the image, and then scale it, and the create an image icon out of that
      // scaled image.
      // plantIcon = new ImageIcon(getClass().getResource("./piranha.png"));
      // moleIcon = new ImageIcon(getClass().getResource("./monty.png"));
      Image plantImage = new ImageIcon(getClass().getResource("./piranha.png")).getImage();
      plantIcon = new ImageIcon(plantImage.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH));

      Image moleImage = new ImageIcon(getClass().getResource("./monty.png")).getImage();
      moleIcon = new ImageIcon(moleImage.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH));

      for (int i = 0; i < 9; i++) {
         JButton tile = new JButton();
         board[i] = tile;
         tile.setBackground(Color.decode("#F5F5DC"));
         gamePanel.add(tile);
         // so that on clicking the button,no border like focus is made.
         tile.setFocusable(false);
         tile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               JButton tile = (JButton) e.getSource();
               if (tile == currMoleTile) {
                  score += 10;
                  textLabel.setText("SCORE : " + Integer.toString(score));
               } else if (tile == currPlantTile) {
                  textLabel.setText("GAME OVER : " + Integer.toString(score));
                  setMoleTimer.stop();
                  setPlantTimer.stop();
                  for (int i = 0; i < 9; i++) {
                     board[i].setEnabled(false);
                  }
                 
            // Clear the game panel and reset layout to BorderLayout
            gamePanel.removeAll();
            JLabel imageLabel= new JLabel(moleIcon);
            gamePanel.setLayout(new BorderLayout());
            gamePanel.add(imageLabel, BorderLayout.CENTER);

            gamePanel.revalidate();
            gamePanel.repaint();

               }
            }
         });
      }

      // the value of timer is in millisecond, for eg. 1000ms=1second;
      setMoleTimer = new Timer(400, new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (currMoleTile != null) {
               currMoleTile.setIcon(null);
               currMoleTile = null;
            }

            int num = random.nextInt(9); // it will choose a random number till 9
            JButton tile = board[num];

            // if the tile is already taken by the plant, skip for this turn
            if (currPlantTile == tile) {
               return;
            }
            // we can still have only a single picture in the game but we wont have any
            // conflict.
            currMoleTile = tile;
            currMoleTile.setIcon(moleIcon);
         }
      });
      setPlantTimer = new Timer(500, new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (currPlantTile != null) {
               currPlantTile.setIcon(null);
               currPlantTile = null;
            }
            int num = random.nextInt(9); // it will choose a random number till 9
            JButton tile = board[num];
            // if the tile is already expired by the mole, skip for this turn
            if (currMoleTile == tile) {
               return;
            }
            currPlantTile = tile;
            currPlantTile.setIcon(plantIcon);
         }
      });
      // at some cases, we will ee only one i.e either moleplanttile or plattile. this
      // happens because of overriding. i.e we have a very limited array. and chances
      // of coming the same tile are very high. in that case, we have mole and plant
      // tile same on a single tile. as we have two timers. to avoid this, we need to
      // add a jack.

      setMoleTimer.start();
      setPlantTimer.start();
      frame.setVisible(true);
   }
}
