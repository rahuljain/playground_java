/* Move.java : The game movements
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
/**
 * Serve para guardar movimentos
 */
class Move {
  /**
   * Casa de partida
   */
  private int from;
  
  /**
   * Casa destino
   */
  private int to;

  /**
   * Inicializa uma jogada.
   */
  Move (int moveFrom, int moveTo) {
    from = moveFrom;
    to = moveTo;
  }
  
  /**
   * Devolve a casa de partida
   */ 
  public int getFrom () {
    return from;
  }
  
  /**
   * Devolve a casa destino
   */ 
  public int getTo () {
    return to;
  }

  /**
   * Devolve uma representacao string da jogada
   */
  public String toString () {
    return "(" + from + "," + to + ")";
  }
}

