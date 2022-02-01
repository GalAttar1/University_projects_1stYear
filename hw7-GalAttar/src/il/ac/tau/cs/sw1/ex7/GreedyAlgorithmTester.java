package il.ac.tau.cs.sw1.ex7;
import java.util.*;

public class GreedyAlgorithmTester {
    public static void main(String[] args){
    	/*System.out.println("Coins");
        coinsTest();
        System.out.println("FKS");
        fksTest();
        */
        System.out.println("MST");
        mstTest();
    }
    public static void coinsTest(){
        System.out.println("  0 " + (new Coins(0)).greedyAlgorithm().equals(Arrays.asList()));
        System.out.println("  1 " + (new Coins(1)).greedyAlgorithm().equals(Arrays.asList(1)));
        System.out.println(" 19 " + (new Coins(19)).greedyAlgorithm().equals(Arrays.asList(10,5,2,2)));
        System.out.println(" 30 " + (new Coins(30)).greedyAlgorithm().equals(Arrays.asList(10,10,10)));
        System.out.println(" 36 " + (new Coins(36)).greedyAlgorithm().equals(Arrays.asList(10,10,10,5,1)));
        System.out.println("105 " + (new Coins(105)).greedyAlgorithm().equals(Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 5)));
    }
    public static void fksTest(){
        FractionalKnapSack.Item s1 = new FractionalKnapSack.Item(10,60);
        FractionalKnapSack.Item s2 = new FractionalKnapSack.Item(20,100);
        FractionalKnapSack.Item s3 = new FractionalKnapSack.Item(30,120);
        FractionalKnapSack s = new FractionalKnapSack(50, Arrays.asList(s3,s1,s2));
        System.out.println("fks s " + s.greedyAlgorithm().toString().equals("[{weight=10.0, value=60.0}, {weight=20.0, value=100.0}, {weight=20.0, value=120.0}]"));
        // check fks where there is more capacity than needed
        FractionalKnapSack.Item t1 = new FractionalKnapSack.Item(3, 50);
        FractionalKnapSack.Item t2 = new FractionalKnapSack.Item(2, 20);
        FractionalKnapSack.Item t3 = new FractionalKnapSack.Item(2, 5);
        FractionalKnapSack t = new FractionalKnapSack(30, Arrays.asList(t3, t1, t2));
        System.out.println("fks t " + t.greedyAlgorithm().toString().equals("[{weight=3.0, value=50.0}, {weight=2.0, value=20.0}, {weight=2.0, value=5.0}]"));
    }
    public static void mstTest(){
        Graph.Edge s1 = new Graph.Edge(0,1,1);
        Graph.Edge s2 = new Graph.Edge(1,2,4);
        Graph.Edge s3 = new Graph.Edge(2,3,1);
        Graph.Edge s4 = new Graph.Edge(2,4,3);
        Graph.Edge s5 = new Graph.Edge(3,4,3);
        Graph.Edge s6 = new Graph.Edge(0,4,4);
        Graph s = new Graph(5, Arrays.asList(s1,s2,s3,s4,s5,s6));
        System.out.println("s Graph= " + s.lst.toString()); 
        List<Graph.Edge> lst = s.greedyAlgorithm();
        List<Graph.Edge> sol1 = Arrays.asList(s1,s3,s5,s6);
        List<Graph.Edge> sol2 = Arrays.asList(s1,s3,s4,s2);
        List<Graph.Edge> sol3 = Arrays.asList(s1,s3,s4,s6);
        List<Graph.Edge> sol4 = Arrays.asList(s1,s2,s3,s5);
        
        if (lst.equals(sol3))
            System.out.println("example from worksheet true");
        else if (lst.equals(sol1) || lst.equals(sol2) || lst.equals(sol4))
            System.out.println("example from worksheet false (This is an MST, but not in the right order)");
        else
            System.out.println("example from worksheet false");
        // let's try a triangle, which also does not use all the numbers
        Graph.Edge t1 = new Graph.Edge(0, 2, 1);
        Graph.Edge t2 = new Graph.Edge(1, 2, 5);
        Graph.Edge t3 = new Graph.Edge(0, 1, 2);
        Graph t = new Graph(3, Arrays.asList(t1, t2, t3));
        lst = t.greedyAlgorithm();
        System.out.println("    Trinangle t1,t2,t3 " + lst.equals(Arrays.asList(t1, t3)));
        t = new Graph(3, Arrays.asList(t3, t1, t2));
        lst = t.greedyAlgorithm();
        System.out.println("    Trinangle t3,t1,t2 " + lst.equals(Arrays.asList(t1, t3)));
    }
}
