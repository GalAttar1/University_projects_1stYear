package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.Collections;  
import java.util.Comparator;  
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;  
import java.util.LinkedList;  
import java.util.List;  
import java.util.Map.Entry; 


/**************************************
 *  Add your code to this class !!!   *
 **************************************/
public class HashMapHistogramIterator<T extends Comparable<T>> implements Iterator<T>{
	
	private HashMap<T, Integer> Map = new HashMap<T, Integer>(); 
	private List<Entry<T, Integer>> list;               
	private Integer pos;
			
	public HashMapHistogramIterator(HashMap<T, Integer> histogram) {
		this.Map= Mapsort(histogram); 
		this.pos=0; 
		this.list = new LinkedList<Entry<T, Integer>>(Map.entrySet());
		
		
	}
	public class Comprator implements Comparator<Entry<T, Integer>> {
		public int compare(Entry<T, Integer> o1, Entry<T, Integer> o2){
			if (o1.getValue()>o2.getValue()) {return -1;}
			if (o1.getValue()<o2.getValue()) {return 1;}
			//if they are equally common, their inner order will call
			return o1.getKey().compareTo(o2.getKey()); 
		}
	}
	
	public HashMap<T, Integer> Mapsort(HashMap<T, Integer> Map) {
		//convert HashMap into List   
		List<Entry<T, Integer>> list = new LinkedList<Entry<T, Integer>>(Map.entrySet());  
		//sorting the list elements  
		Collections.sort(list, new Comprator()); 
		HashMap<T, Integer> sortedMap = new LinkedHashMap<T, Integer>();  
		for (Entry<T, Integer> entry : list){  
			sortedMap.put(entry.getKey(), entry.getValue());  
			}  
		return sortedMap; 
	}

	@Override
	public boolean hasNext() {
		return (this.pos<this.list.size());
	}

	@Override
	public T next() {
		pos+=1; 
		return this.list.get(pos-1).getKey();
		
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException(); //no need to change this
	}
}
