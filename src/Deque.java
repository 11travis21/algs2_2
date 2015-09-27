//this is my test

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>
{
    //consider left side of deque the tail and the right side the head
    private Node tail = null;
    private Node head = null;
    private int size = 0;

    private class Node
    {
        Item item = null;
        Node left = null;
        Node right = null;
    }

    public Deque(){}

    public boolean isEmpty()
    {
        return (this.tail == null);
    }

    public int size()
    {
        return this.size;
    }

    private void addToEmptyDeque(Item item)
    {
        this.head = new Node();
        this.head.item = item;
        this.tail = this.head;
    }

    public void addFirst(Item item)
    {
        if (item == null)
            throw new NullPointerException();

        if (this.head == null)
            addToEmptyDeque(item);
        else
        {
            Node oldHead = this.head;
            Node newHead = new Node();

            newHead.item = item;
            oldHead.right = newHead;
            newHead.left = oldHead;

            this.head = newHead;
        }
        this.size++;
    }

    public void addLast(Item item)
    {
        if (item == null)
            throw new NullPointerException();

        if (this.tail == null)
            addToEmptyDeque(item);
        else
        {
            Node oldTail= this.tail;
            Node newTail = new Node();

            newTail.item = item;
            oldTail.left = newTail;
            newTail.right = oldTail;

            this.tail = newTail;
        }
        this.size++;
    }

    public Item removeFirst()
    {
        if (this.head == null)
            throw new NoSuchElementException();

        Item item = this.head.item;
        this.head = this.head.left;

        if (this.head == null)
            this.tail = null;
        else
            this.head.right = null;

        this.size--;
        return item;
    }

    public Item removeLast()
    {
        if (this.tail == null)
            throw new NoSuchElementException();

        Item item = this.tail.item;
        this.tail = this.tail.right;

        if (this.tail == null)
            this.head = null;
        else
            this.tail.left = null;

        this.size--;
        return item;
    }

    public Iterator<Item> iterator()
    {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item>
    {
        private Node current = head;

        public boolean hasNext()
        {
            return current != null;
        }

        public Item next()
        {
            if (current == null)
                throw new NoSuchElementException();

            Item item = current.item;
            current = current.left;
            return item;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }

    private void printDeque()
    {
        System.out.println("-----FRONT-----");
        Iterator<Item> it = this.iterator();
        while (it.hasNext())
        {
            Item i = it.next();
            System.out.println(i.toString());
        }
        System.out.println("-----BACK-----");
        System.out.println();
    }

    public static void main(String[] args)
    {
        Deque<Integer> d1 = new Deque<Integer>();
        d1.addFirst(1);
        d1.addFirst(2);
        d1.addFirst(3);
        d1.addFirst(4);
        d1.addLast(5);

        d1.printDeque();

        System.out.println(d1.removeLast());
        System.out.println(d1.removeLast());
        System.out.println(d1.removeFirst());
        System.out.println(d1.removeLast());
        System.out.println();

        d1.printDeque();

        //System.out.println(d1.removeFirst());
        System.out.println(d1.removeLast());

        d1.printDeque();
    }
}