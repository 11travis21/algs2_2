import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    //consider left side of queue the tail and the right side the head
    private Item[] a = (Item[]) new Object[1];
    private int N = 0;

    public RandomizedQueue(){}

    private void resize(int max)
    {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < this.N; i++)
            temp[i] = this.a[i];
        this.a = temp;
    }

    public boolean isEmpty()
    {
        if (this.N == 0)
            return true;

        return false;
    }

    public int size()
    {
        return this.N;
    }

    public void enqueue(Item item)
    {
        if (item == null)
            throw new NullPointerException();

        if (this.N == a.length)
            this.resize(2*this.a.length);

        a[this.N++] = item;

        //perform insert shuffle
        int index = StdRandom.uniform(this.N);
        Item temp = a[index];
        a[index] = item;
        a[this.N - 1] = temp;
    }

    public Item dequeue()
    {
        if (this.N == 0)
            throw new NoSuchElementException();

        Item item = a[--N];
        a[N] = null;
        if (N > 0 && N == a.length/4)
            resize(a.length/2);

        return item;
    }

    public Item sample()
    {
        if (this.N == 0)
            throw new NoSuchElementException();

        int index = StdRandom.uniform(this.N);
        return this.a[index];
    }

    public Iterator<Item> iterator()
    {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item>
    {
        private int[] indexAry = null;
        int i;

        public RandomizedQueueIterator()
        {
            this.indexAry = new int[N];
            this.i = 0;

            for (int i = 0; i < indexAry.length; i++)
                this.indexAry[i] = i;

            for (int i = 0; i < indexAry.length; i++)
            {
                int index = StdRandom.uniform(i+1);
                int temp = indexAry[index];
                indexAry[index] = indexAry[i];
                indexAry[i] = temp;
            }
        }

        public boolean hasNext()
        {
            return this.i < this.indexAry.length;
        }

        public Item next()
        {
            if (this.i >= this.indexAry.length)
                throw new NoSuchElementException();

            Item item = a[indexAry[i++]];
            return item;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args)
    {
        RandomizedQueue<Integer> rq1 = new RandomizedQueue<Integer>();
        rq1.enqueue(1);
        rq1.enqueue(2);
        rq1.enqueue(3);
        rq1.enqueue(4);
        rq1.enqueue(5);
        rq1.enqueue(6);
        rq1.enqueue(7);
        rq1.enqueue(8);
        rq1.enqueue(9);
        rq1.enqueue(10);

        Iterator<Integer> it1 = rq1.iterator();
        Iterator<Integer> it2 = rq1.iterator();

        System.out.println("Iterator 1:");

        while (it1.hasNext())
            System.out.print(it1.next() + " ");

        System.out.println();
        System.out.println("Iterator 2:");

        while (it2.hasNext())
            System.out.print(it2.next() + " ");

        System.out.println();
        System.out.println("Dequeue:");

        while (!rq1.isEmpty())
            System.out.print(rq1.dequeue() + " ");
    }
}