package il.ac.tau.cs.sw1.ex7;
import java.util.*;

public class FractionalKnapSack implements Greedy<FractionalKnapSack.Item>{
    int capacity;
    List<Item> lst;

    FractionalKnapSack(int c, List<Item> lst1){
        capacity = c;
        lst = lst1;
    }

    public static class Item {
        double weight, value, ratio;
        Item(double w, double v) {
            weight = w;
            value = v;
            ratio = (v/w);
        }

        @Override
        public String toString() {
        	if (this.equals(null)){return "null";}
            return "{" + "weight=" + weight + ", value=" + value + '}';
        	}
        
        }
    public class ItemComprator implements Comparator<Item> {
   	 public int compare(Item i1, Item i2) {
   		 //Comparison by the ratios Double.compare(i1.ratio, i2.ratio);
   		 return i1.ratio > i2.ratio ? -1 : (i1.ratio < i2.ratio) ? 1 : 0;
   	 }
    }

    @Override
    public Iterator<Item> selection() {
    	Collections.sort(lst, new ItemComprator());
        return lst.iterator();
    }

    @Override
    public boolean feasibility(List<Item> candidates_lst, Item element) {
        if (sum(candidates_lst) + element.weight <= capacity) {return true;} 
        else {
        	double partial = capacity-sum(candidates_lst);
        	if (partial>0 && partial<element.weight) {element.weight=partial; return true;}
        }
    	return false;
    }

    private double sum(List<Item> lst) {
    	double sum = 0;
        for (Item element : lst){
            sum += element.weight;
        }
        return sum;
	}

	@Override
    public void assign(List<Item> candidates_lst, Item element) {
		candidates_lst.add(element);}
    

    @Override
    public boolean solution(List<Item> lst) {
    	if (lst.isEmpty()) {return true;}
        return (sum(lst)==capacity);
    }
    
    public String toString() {
    	String st=""; 
    	for (Item element : this.lst){
    		st+=element.toString();}
    	return st; 
    }
}
