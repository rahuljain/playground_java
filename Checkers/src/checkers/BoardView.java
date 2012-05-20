/* BoardView.java : Where the board is drawn
 * Copyright (C) 1998-2002  Paulo Pinto
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA 02111-1307, USA.
 */
package checkers;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.*;
import java.io.*;
import java.util.*;


    
/**
 * Janela que representa o tabuleiro e processa os eventos do utilizador.
 */
public class BoardView extends JPanel {
  /**
   * Recursos
   */
  static ResourceBundle resources;

  /**
   * Tabuleiro que contem os dados apresentados por BoardView
   */
  private CheckersBoard board;

  /**
   * Posicao do canto superior esquerdo.
   */
   int startX;
   int startY;

  /**
   * Largura das casas
   */
  int cellWidth;


  /**
   * Casas selecionadas, o primeiro elemento e' a peca selecionada
   */
  List selected;


  /**
   * Computador
   */
  Computer computer;
  
  /**
   * Dimensao da peca
   */
  private static final int SIZE = 0;
  

  /**
   * Janela onde o tabuleiro se encontra
   */
  private JFrame parent;

  /**
   * Eventos do rato
   */
  private MouseHandler handler;
  


  /**
   * Constructor
   * @param parentComponent Componente onde o tabuleiro
   * vai desenhar
   * @param b Classe que guarda o estado do tabuleiro
   * @param bundle Recurso com as strings
   */
  public BoardView (JFrame parentComponent, CheckersBoard b,
                    ResourceBundle bundle) {
    selected = new List ();
    board = b;
    parent = parentComponent;
    resources = bundle;
    computer = new Computer (b);
    handler = new MouseHandler (this, parent);
    addMouseListener (handler);
  }

  /**
   * Devolve o tabuleiro associado
   */
  public CheckersBoard getBoard () {
    return board;
  }

  /**
   * Recome�a um novo jogo
   */
  public void newGame () {
    board.clearBoard ();
    selected.clear ();
    repaint ();
    handler.reset ();
    computer.play ();
    ChangeTitle ();
  }


  /**
   * Muda o t�tulo para reflectir o jogador corrente
   */
   public void ChangeTitle () {
    if (board.getCurrentPlayer () == CheckersBoard.WHITE)
     parent.setTitle (resources.getString("whiteTitleLabel"));
    else
      parent.setTitle (resources.getString("blackTitleLabel"));
  }

  /**
   * Grava um tabuleiro
   * @param fileName Path para o ficheiro
   */
  public void saveBoard (String fileName) {
    try {
      FileOutputStream ostream = new FileOutputStream (fileName);
      ObjectOutputStream p = new ObjectOutputStream(ostream);
      p.writeObject(board);
      p.flush();
      ostream.close();
    }
    catch (IOException e) {
      e.printStackTrace ();
      System.exit (1);
    }
  }

  /**
   * Carrega um tabuleiro
   * @param fileName Path para o ficheiro
   */
  public void loadBoard (String fileName) {
    try {
      FileInputStream istream = new FileInputStream(fileName);
      ObjectInputStream p = new ObjectInputStream(istream);
      board = (CheckersBoard) p.readObject();
      istream.close();
      repaint ();
      
      computer.setBoard (board);
      ChangeTitle ();
    }
    catch (Exception e) {
      e.printStackTrace ();
      System.exit (1);
    }    
  }
  
  
    
    
  /**
   * Desenha o tabuleiro.
   * @param g Local onde o desenho � efectuado
   */
  public void paintComponent (Graphics g) {
    Dimension d = getSize ();
    int marginX;
    int marginY;
    int incValue;
    
    // Limpa o buffer
    
    g.setColor (Color.lightGray);
    g.fillRect (0, 0, d.width, d.height);
    g.setColor (Color.black);
    
    //  Calcula os incrementos de forma a obter um tabuleiro
    // quadrado
    if (d.width < d.height) {
      marginX = 0;
      marginY = (d.height - d.width) / 2;
      
      incValue = d.width / 8;
    }
    else  {
      marginX = (d.width - d.height) / 2;
      marginY = 0;
      
      incValue = d.height / 8;
    }

    startX = marginX;
    startY = marginY;
    cellWidth = incValue;

    drawBoard (g, marginX, marginY, incValue);
    drawPieces (g, marginX, marginY, incValue);
  }

  /**
   * Desenha a parte do tabuleiro
   *
   * @param g Contexto onde desenha as pecas
   * @param marginX Margem horizontal do tabuleiro
   * @param marginY Margem vertical do tabuleiro
   * @param incValue Factor de incremento entre as casas do tabuleiro
   */
  private void drawBoard (Graphics g, int marginX, int marginY, int incValue) {
    int pos;
    
    for (int y = 0; y < 8; y++)
      for (int x = 0; x < 8; x++) {
        if ((x + y) % 2 == 0)
          g.setColor (Color.white);
        else {
          pos = y * 4 + (x + ((y % 2 == 0) ? - 1 : 0)) / 2;
          
          if (selected.has (new Integer (pos)))
            g.setColor (Color.green);
          else
            g.setColor (Color.black);
        }
        

        g.fillRect (marginX + x * incValue, marginY + y * incValue, incValue - 1, incValue - 1);    
      }
  }


  /**
   * Margem para as pecas que sao damas
   */
  private static final int KING_SIZE = 3;
  
  /**
   * Desenha as pecas existentes no tabuleiro
   *
   * @param g Contexto onde desenha as pecas
   * @param marginX Margem horizontal do tabuleiro
   * @param marginY Margem vertical do tabuleiro
   * @param incValue Factor de incremento entre as casas do tabuleiro onde as pecas sao
   *                desenhadas                
   */
  private void drawPieces (Graphics g, int marginX, int marginY, int incValue) {
    int x, y;
    for (int i = 0; i < 32; i++)
      try {
        if (board.getPiece (i) != CheckersBoard.EMPTY) {
          if (board.getPiece (i) == CheckersBoard.BLACK ||
              board.getPiece (i) == CheckersBoard.BLACK_KING)
            g.setColor (Color.red);
          else 
            g.setColor (Color.white);

          y = i / 4;
          x = (i % 4) * 2 + (y % 2 == 0 ? 1 : 0);
          g.fillOval (SIZE + marginX + x * incValue, SIZE + marginY + y * incValue,
                      incValue - 1 - 2 * SIZE, incValue - 1 - 2 * SIZE);

          if (board.getPiece (i) == CheckersBoard.WHITE_KING) {
            g.setColor (Color.black);
            g.drawOval (KING_SIZE + marginX + x * incValue, KING_SIZE + marginY + y * incValue,
                        incValue - 1 - 2 * KING_SIZE, incValue - 1 - 2 * KING_SIZE);
          }
          else if (board.getPiece (i) == CheckersBoard.BLACK_KING) {
            g.setColor (Color.white);
            g.drawOval (KING_SIZE + marginX + x * incValue, KING_SIZE + marginY + y * incValue,
                        incValue - 1 - 2 * KING_SIZE, incValue - 1 - 2 * KING_SIZE);
          }
          
            
          
        }
      }
      catch (BadCoordException bad) {
        bad.printStackTrace ();
        System.exit (1);
      }
  }
 
}
    
  

/**
 * Classe para processar os eventos do rato para o tabuleiro
 */
class MouseHandler extends MouseAdapter {
  /**
   * Tabuleiro a que o evento esta associado
   */
  private BoardView view;

  /**
   * Pai do componente a que este evento esta' associado
   */
  private JFrame parent;
  


  /**
   * tabuleiro temporario para jogadas multiplas
   */
  Stack boards;
  
  /**
   * @param boardView Tabuleiro que se encontra associado a estes eventos
   */
  public MouseHandler (BoardView boardView, JFrame parentComponent) {
    view = boardView;
    parent = parentComponent;
    boards = new Stack ();
  }

  
  /**
   *  Processa a mensagem de pressao num botao
   *  Se for "clicado" numa casa com uma peca, se essa peca for do jogador,
   * ela fica selecionada.
   */
  public void mouseClicked (MouseEvent e) {
    int pos;
   


    pos = getPiecePos (e.getX (), e.getY ());
    if (pos != -1)
      try {
        CheckersBoard board = view.getBoard ();

        int piece = board.getPiece (pos);
        
        if (piece != CheckersBoard.EMPTY &&
            (((piece == CheckersBoard.WHITE || piece == CheckersBoard.WHITE_KING) &&
              board.getCurrentPlayer () == CheckersBoard.WHITE) ||
              ((piece == CheckersBoard.BLACK || piece == CheckersBoard.BLACK_KING) &&
              board.getCurrentPlayer () == CheckersBoard.BLACK))) {
          if (view.selected.isEmpty ())  // Se nao havia nenhuma selecionada
            view.selected.push_back (new Integer (pos));
          else {
            int temp = ((Integer) view.selected.peek_tail ()).intValue ();

            if (temp == pos) // Se estava selecionada, desceleciona
              view.selected.pop_back ();
            else
	      JOptionPane.showMessageDialog (parent,
                                             view.resources.getString("notSelectedLabel"),
                                             view.resources.getString("errorLabel"),
                                             JOptionPane.ERROR_MESSAGE);
          }
          
          
          view.repaint ();
          return;
        }
        else {
          boolean good = false;
          CheckersBoard tempBoard;
                    
          if (!view.selected.isEmpty ()) {
            // Obtem o tabuleiro corrente
            if (boards.empty ()) {
              tempBoard = (CheckersBoard) board.clone ();
              boards.push (tempBoard);
            }
            else
              tempBoard = (CheckersBoard) boards.peek ();
            

            int from = ((Integer) view.selected.peek_tail ()).intValue ();
            if (tempBoard.isValidMove (from, pos)) {
              tempBoard = (CheckersBoard) tempBoard.clone ();

              boolean isAttacking = tempBoard.mustAttack ();
              
              tempBoard.move (from, pos);
              
              if (isAttacking && tempBoard.mayAttack (pos)) {
                view.selected.push_back (new Integer (pos));
                boards.push (tempBoard);
                view.repaint ();
              }
              else {
                view.selected.push_back (new Integer (pos));
                makeMoves (view.selected, board);
                boards = new Stack ();
              }
              
              good = true;
            }
            else if (from == pos) {
              view.selected.pop_back ();
              boards.pop ();
              view.repaint ();
              good = true;
            }
          }
          
          if (!good)
            JOptionPane.showMessageDialog (parent,
                                           view.resources.getString("invalidMoveLabel"),
                                           view.resources.getString("errorLabel"),
                                           JOptionPane.ERROR_MESSAGE);
        }
      }
      catch (BadCoordException bad) {
        bad.printStackTrace ();
        System.exit (1);        
      }
      catch (BadMoveException bad) {
        bad.printStackTrace ();
        System.exit (1);        
      }
    }


  /**
   * Limpa as estruturas temporarias
   */
  public void reset () {
    boards = new Stack ();
  }
  


  /**
   * Efectua as jogadas do jogador humano, seguido do computador
   */
  private void makeMoves (List moves, CheckersBoard board) throws BadMoveException {
    List moveList = new List ();
    int from, to = 0;

    from = ((Integer) moves.pop_front ()).intValue ();
    while (!moves.isEmpty ()) {
      to = ((Integer) moves.pop_front ()).intValue ();
      moveList.push_back (new Move (from, to));
      from = to;
    }

    board.move (moveList);
    view.repaint (1);
    view.selected.clear ();
    reset ();
        

    if (!gameEnded ()) {
      view.ChangeTitle ();
      view.computer.play ();
      view.repaint ();

      if (!gameEnded ())
        view.ChangeTitle ();
    }
  }
    
  /**
   * Devolve o indice da peca seleciona
   * @returns Indice (0-31) da peca selecionada.
   *          No caso de nao estar nenhuma selecionada devolve -1.
   */
  private int getPiecePos (int currentX, int currentY) {
    for (int i = 0; i < 32; i++) {
      int x, y;

      y = i / 4;
      x = (i % 4) * 2 + (y % 2 == 0 ? 1 : 0);
      if (view.startX + x * view.cellWidth < currentX &&
          currentX < view.startX + (x + 1) * view.cellWidth &&
          view.startY + y * view.cellWidth < currentY &&
          currentY < view.startY + (y + 1) * view.cellWidth)
        return i;
    }

    return -1;
  }

  /**
   * Trata a situacao de fim de jogo
   */
  private boolean gameEnded () {
    CheckersBoard board = view.getBoard ();
    boolean result;

    int white = board.getWhitePieces ();
    int black = board.getBlackPieces ();
    if (board.hasEnded ()) {
      if (board.winner () == CheckersBoard.BLACK)
        JOptionPane.showMessageDialog (parent, view.resources.getString("blackWinLabel"),
                                       view.resources.getString("endGameLabel"),
                                       JOptionPane.INFORMATION_MESSAGE);
      else
        JOptionPane.showMessageDialog (parent, view.resources.getString("whiteWinLabel"),
                                       view.resources.getString("endGameLabel"),
                                       JOptionPane.INFORMATION_MESSAGE);
      result = true;
    }
    else
      result = false;

    return result;
  }
  
}





