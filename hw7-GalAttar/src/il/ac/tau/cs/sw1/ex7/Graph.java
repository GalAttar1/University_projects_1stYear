package il.ac.tau.cs.sw1.ex7;
import java.util.*;


public class Graph implements Greedy<Graph.Edge>{
    List<Edge> lst; //Graph is represented in Edge-List. It is undirected. Assumed to be connected.
    int n; //nodes are in [0,...,n-1]

    Graph(int n1, List<Edge> lst1){
        lst = lst1;
        n = n1;
    }

    public static class Edge{
        int node1, node2;
        double weight;

        Edge(int n1, int n2, double w) {
            node1 = n1;
            node2 = n2;
            weight = w;
        }

        @Override
        public String toString() {
            return "{" + "(" + node1 + "," + node2 + "), weight=" + weight + '}';
        }
    }
    
    public class EdgeComprator implements Comparator<Edge> {
      	 public int compare(Edge e1, Edge e2) {
      		 //Comparison by the weights / order n1 / order n2
      		 if (e1.weight>e2.weight) {return 1;}
      		 if (e1.weight<e2.weight) {return -1;}
      		 if (e1.node1 > e2.node1) {return -1;}
      		 if (e1.node1 < e2.node1) {return 1;}
      		 return (e1.node2 > e2.node2) ? -1 : (e1.node2 < e2.node2) ? 1 : 0;
      	 }
       }

    @Override
    public Iterator<Edge> selection() {
    	Collections.sort(lst, new EdgeComprator());
        return lst.iterator();
    }

    @Override
    public boolean feasibility(List<Edge> candidates_lst, Edge element) {
    	int n1= element.node1;
    	int n2= element.node2; 
    	return (!this.hasPath(n1,n2,candidates_lst));
    	//if there is already a path between n1 & n2, adding an edge between them will close a circle. 
    }

    private boolean hasPath(int n1, int n2, List<Edge> candidates_lst) {
		// Checks if the is a path between two nodes
    	if (candidates_lst.size()==0) {return false;}  
		if (contains(n1,n2, candidates_lst)) {;return true;} 
		//if ((n1>=this.n)||(n2>=this.n)) {return false;}
    	return hasPathRec (n1, n2, n1, candidates_lst); 
	}

    private boolean hasPathRec (int n1, int n2, int cur,List<Edge> list) {
		if (cur==n2) {return true;}
		if (list.isEmpty()) {return false;}
    	for (Edge edge : list) {
			if ((edge.node1==cur)||(edge.node2==cur)){
				List<Edge> partialList = new ArrayList<Edge>(list);
				partialList.remove(edge);
				if (edge.node1==cur) {cur=edge.node2;}
				else {cur=edge.node1;} 
				//System.out.println("cur=" + cur + ",partialList = " + partialList.toString()); 
				if (cur==n1) {break;}
				if (contains(cur, n2, partialList)) {return true;} 
				if (hasPathRec(n1, n2, cur, partialList)) {return true;} 
			}
		}
    	return false;
	}
    
    private static boolean contains (int n1, int n2, List<Edge> candidates_lst) {
    	if (n1==n2) {return true;}
    	for (Edge edge : candidates_lst) {
    		if (((edge.node1==n1)&&(edge.node2==n2))||((edge.node1==n2)&&(edge.node2==n1))){return true;}}
    	return false; 
    }

	@Override
    public void assign(List<Edge> candidates_lst, Edge element) {
		candidates_lst.add(element); 
    }

    @Override
    public boolean solution(List<Edge> candidates_lst) {
    	return (candidates_lst.size()==(n-1)); 
    }
}
