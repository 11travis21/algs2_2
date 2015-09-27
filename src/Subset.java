import edu.princeton.cs.algs4.StdIn;

public class Subset {

    public static void main(String[] args)
    {
        int K = Integer.parseInt(args[0]);

        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        String line = null;

        while (!StdIn.isEmpty())
        {
            rq.enqueue(StdIn.readString());
        }

        for (int i = 0; i < K; i++)
            System.out.println(rq.dequeue());

    }

}