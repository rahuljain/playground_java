/* List.java : A general purpose list
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
import java.util.*;


/**
 * Nodos da lista
 */
class ListNode {
  ListNode prev, next;
  Object   value;

  public ListNode (Object elem, ListNode prevNode, ListNode nextNode) {
    value = elem;
    prev = prevNode;
    next = nextNode;
  }
}

/**
 * Classe para listas genericas
 */
public class List implements Cloneable {
  private ListNode head;
  private ListNode tail;
  private int count;

  public List () {
    count = 0;
  }
  
  /**
   * Adiciona um elemento no inicio da lista
   */
  public void push_front (Object elem) {
    ListNode node = new ListNode (elem, null, head);
    
    if (head != null)
      head.prev = node;
    else
      tail = node;

    head = node;
    count++;
  }

  /**
   * Adiciona um elemento na cauda da lista
   */
  public void push_back (Object elem) {
    ListNode node = new ListNode (elem, tail, null);

    if (tail != null)
      tail.next = node;
    else
      head = node;

    tail = node;
    count++;
  }

  /**
   * Remove o elemento do inicio da lista e devolve-o
   */
  public Object pop_front () {
    if (head == null)
      return null;

    ListNode node = head;
    head = head.next;

    if (head != null)
      head.prev = null;
    else
      tail = null;

    count--;
    return node.value;
  }

  /**
   * Remove o elemento do fim da lista e devolve-o
   */
  public Object pop_back () {
    if (tail == null)
      return null;

    ListNode node = tail;
    tail = tail.prev;

    if (tail != null)
      tail.next = null;
    else
      head = null;

    count--;
    return node.value;
  }


  /**
   * Diz se a lista e' vazia
   */
  public boolean isEmpty () {
    return head == null;
  }


  /**
   * Devolve o numero de elementos na lista
   */
  public int length () {
    return count;
  }

  /**
   * Acrescenta outra lista a this
   * @param other list that is to be appended.
   */
  public void append (List other) {
    ListNode node = other.head;
    
    while (node != null) {
      push_back (node.value);
      node = node.next;
    }
  }
  

  /**
   * Fica a lista vazia
   */
  public void clear () {
    head = tail = null;
  }


  /**
   * Devolve o elemento 'a cabeca da lista sem o remover
   */
  public Object peek_head () {
    if (head != null)
      return head.value;
    else
      return null;
  }

  /**
   * Devolve o elemento 'a cauda da lista sem o remover
   */
  public Object peek_tail () {
    if (tail != null)
      return tail.value;
    else
      return null;
  }
  
    
  /**
   * Verifica se o elemento existe na lista
   */
  public boolean has (Object elem) {
    ListNode node = head;

    while (node != null && !node.value.equals (elem))
      node = node.next;

    return node != null;
  }

  /**
   * Duplica a lista (em shallow copy)
   */
  public Object clone () {
    List temp = new List ();
    ListNode node = head;

    while (node != null) {
      //temp.push_back (node.value.clone ());
      temp.push_back (node.value);
      node = node.next;
    }

    return temp;
  }

  /**
   * Devolve uma representacao em string
   */
  public String toString () {
    String temp = "[";
    ListNode node = head;

    while (node != null) {
      temp += node.value.toString ();
      node = node.next;
      if (node != null)
        temp += ", ";
    }
    temp += "]";

    return temp;
  }

  /**
   * Classe para fazer enumeracoes
   */
  class Enum implements Enumeration {
    /**
     * Elemento corrente
     */
    private ListNode node;

    Enum (ListNode start) {
      node = start;
    }

    /**
     * Indica se ainda existem elementos
     */
    public boolean hasMoreElements () {
      return node != null;
    }

    /**
     * Devolve o proximo elemento
     */
    public Object nextElement () throws NoSuchElementException {
      Object temp;

      if (node == null)
        throw new NoSuchElementException ();

      temp = node.value;
      node = node.next;

      return temp;
    }
  }

  /**
   * Devolve uma enumeracao dos elementos da lista
   */
  public Enumeration elements () {
    return new Enum (head);
  }
  
  
}

  
