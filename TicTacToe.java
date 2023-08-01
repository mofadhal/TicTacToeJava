package TicTacToe;
/*
 * @Version 01.08.2023
 * @Author Mofadhal Al-Manari
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TicTacToe {

    int broardWidth = 600;
    int broardHeight = 650;
    
    JButton restart =  new JButton();
    

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JPanel textPanel = new JPanel();
    JLabel texLabel = new JLabel();
    JPanel bordJPanel = new JPanel(); 
    JButton[][] butten = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;
    Boolean gameOver = false;
    int turns = 0;


  private void anlegen (){

  //----------- Create restart butten -----------------

  restart.setLayout(new BorderLayout());
  restart.setText("Restart");
  restart.setForeground(Color.white);
  restart.setFont(new Font("Arial",Font.BOLD,15));
  restart.setBackground(Color.darkGray);
  restart.setOpaque(true);
  restart.setHorizontalAlignment(JLabel.CENTER);
  restart.setVisible(true);
  frame.add(restart,BorderLayout.BEFORE_FIRST_LINE); 
  restart.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e){
                       restart();
              }
          });

  //----------- Create Frame ------------------
  frame.setVisible(true);
  frame.setSize(broardWidth, broardHeight);
  frame.setResizable(false);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.setLocationRelativeTo(null);
  frame.setLayout(new BorderLayout());

  //----------- Create label ------------------
  texLabel.setBackground(Color.darkGray);
  texLabel.setForeground(Color.white);
  texLabel.setFont(new Font("Arial",Font.BOLD,50));
  texLabel.setHorizontalAlignment(JLabel.CENTER);
  texLabel.setText("Tic-Tac-Toe");
  texLabel.setOpaque(true);

  textPanel.setLayout(new BorderLayout());
  textPanel.add(texLabel);
  frame.add(textPanel,BorderLayout.NORTH);

  bordJPanel.setLayout(new GridLayout(3,3));
  bordJPanel.setBackground(Color.darkGray);
  frame.add(bordJPanel);
  start();
   }

    TicTacToe(){
      anlegen();
    }

     
    public void start(){
         for(int i = 0 ; i < 3 ; i++){
            for (int j = 0; j < 3 ; j++) {
                JButton inButton = new JButton();
                butten[i][j] = inButton;
                bordJPanel.add(inButton);

                inButton.setBackground(Color.darkGray);
                inButton.setForeground(Color.white);
                inButton.setFont(new Font("Arial", Font.BOLD, 100));
                inButton.setFocusable(false);

                inButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        JButton inButton = (JButton) e.getSource();
                        if(gameOver) return;
                        if(inButton.getText() == ""){
                            inButton.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if(!gameOver){
                               currentPlayer = currentPlayer == playerX ? playerO : playerX;
                               texLabel.setText(currentPlayer + "'s turn ");
                            }
                         
                        }
                    }
                });
            }
        }
    }

    /*
     * This Method checks for the winning sitautions "vertical and  horizontal " and Tie
     */
    public void checkWinner(){
        // horizontal
        for (int i = 0; i < 3; i++) {
            if(butten[i][0].getText() == "") continue;

            if(butten[i][0].getText() == butten[i][1].getText() && butten[i][1].getText() == butten[i][2].getText()){
                for (int j = 0; j < 3; j++) {
                    setWinner(butten[i][j]);
                }
                gameOver = true;
                return;
            } 
        }
        // vertical
        for (int c = 0; c < 3; c++) {
            if(butten[0][c].getText() == "") continue;

            if(butten[0][c].getText() == butten[1][c].getText() && butten[1][c].getText() == butten[2][c].getText()){
                for (int j = 0; j < butten.length; j++) {
                    setWinner(butten[j][c]);
                }
                gameOver = true;
                return;
            } 
        }

        if(butten[0][0].getText() == butten[1][1].getText() && butten[1][1].getText() == butten[2][2].getText() && butten[0][0].getText() != ""){
            for (int i = 0; i < 3; i++) {
                setWinner(butten[i][i]);
            }
            gameOver = true;
            return;
        }
        //Tie
        if(butten[0][2].getText() == butten[1][1].getText() && butten[1][1].getText() == butten[2][0].getText() && butten[0][2].getText() != ""){
            setWinner(butten[0][2]);
            setWinner(butten[1][1]);
            setWinner(butten[2][0]);
            gameOver = true;
            return;
        }

        if(turns == 9){
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                   setTie(butten[i][j]);
                }
            }
            gameOver = true;
        }
        
    }

    /*
     *@tempButton get the location of the Button
     * This Method sets the Foreground color to green and the Background color to gray when one of the players wins
     */
    public void setWinner(JButton tempButton){
        tempButton.setForeground(Color.green);
        tempButton.setBackground(Color.gray);

        texLabel.setText(currentPlayer + " is the Winner!");
    } 

    /*
     *@tempButton get the location of the Button
     * This Method sets the Foreground color to orange and the Background color to gray when the game Tie
     */
    public void setTie(JButton tempButton){
        tempButton.setForeground(Color.orange);
        tempButton.setBackground(Color.gray);

        texLabel.setText("Tie!");
    }

    /*
     * This Method sporrt the restart-Butten for the restart
     * It sets all buttons "Backgrounds color and Foregrounds color and text" to default
     */
    public void restart(){
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                   butten[i][j].setText("");
                   butten[i][j].setBackground(Color.darkGray);
                   butten[i][j].setForeground(Color.white);
                }
            }
            turns = 0;
            gameOver = false;

        }
}