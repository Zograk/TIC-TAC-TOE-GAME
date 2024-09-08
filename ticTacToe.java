import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class ticTacToe {

    int boardWidht = 650;
    int boardHeight = 600;

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = (false);

    int turns = 0;

    ticTacToe() {
        frame.setVisible(true);
        frame.setSize(boardWidht, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.DARK_GRAY);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setOpaque(true);
        textLabel.setText("Tic-Tac-Toe");

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.BLUE);
        frame.add(boardPanel);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton tile = new JButton();
                board[i][j] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.CYAN);
                tile.setForeground(Color.BLACK);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);

                //tile.setText(currentPlayer);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if (tile.getText() == "") {
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if (!gameOver) {
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                textLabel.setText(currentPlayer + "'S Turn.");

                            }

                        }
                    }
                });


            }
        }

    }

    void checkWinner() {
        //horizontal
        for (int i = 0; i < 3; i++) {
            if (board[i][0].getText() == "") continue;

            if (board[i][0].getText() == board[i][1].getText() &&
                    board[i][1].getText() == board[i][2].getText()) {
                for (int j = 0; j < 3; j++) {
                    setWinner(board[i][j]);
                }
                gameOver = true;
                return;
            }

        }
        //vertical
        for (int c = 0; c < 3; c++) {
            if (board[0][c].getText() == "") continue;

            if (board[0][c].getText() == board[1][c].getText() &&
                    board[1][c].getText() == board[2][c].getText()) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[i][c]);
                }
                gameOver = true;
                return;
            }

            //diagonal

            if (board[0][0].getText() == board[1][1].getText() &&
                    board[1][1].getText() == board[2][2].getText() &&
                    board[0][0].getText() != "") {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[i][i]);
                }
                gameOver = true;
                return;
            }

            //anti-diagonally
            if (board[0][2].getText() == board[1][1].getText() &&
                    board[1][1].getText() == board[2][0].getText() &&
                    board[0][2].getText() != "") {
                setWinner(board[0][2]);
                setWinner(board[1][1]);
                setWinner(board[2][0]);
                gameOver = true;
                return;
            }
            if (turns == 9) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        setTie(board[i][j]);
                    }
                gameOver = true;
                }

            }

        }


    }

    void setWinner(JButton tile) {
        tile.setForeground(Color.RED);
        tile.setBackground(Color.BLACK);
        textLabel.setText(currentPlayer + " is the Winner!");

    }
    void setTie(JButton tile) {
        tile.setForeground(Color.green);
        tile.setBackground(Color.BLACK);
        textLabel.setText("It's a Tie!");
    }
}



