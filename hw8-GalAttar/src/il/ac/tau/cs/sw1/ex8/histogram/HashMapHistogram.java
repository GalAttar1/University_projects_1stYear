package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.HashMap;

/**************************************
 *  Add your code to this class !!!   *
 **************************************/
public class HashMapHistogram<T extends Comparable<T>> implements IHistogram<T>{

	private HashMap<T, Integer> Histogram = new HashMap<T, Integer>(); 
	
	public HashMap<T, Integer> getHashMap() {
		return this.Histogram; 
	}
	
	//constructor 
	public HashMapHistogram() {
		this.Histogram = new HashMap<T,Integer>(); 	
	}
	
	@Override
	public Iterator<T> iterator() {
		return new HashMapHistogramIterator<T>(this.Histogram); 
	}
	

	@Override
	public void addItem(T item) {
		if (this.Histogram.containsKey(item)){
			this.Histogram.put(item, this.Histogram.get(item)+1); 
 		}
		else {
			this.Histogram.put(item, 1); 
		}
		
	}

	@Override
	public void removeItem(T item) throws IllegalItemException {
		if (!this.Histogram.containsKey(item)){
			throw new IllegalItemException();}
		else {
			if (this.Histogram.get(item)==1) {this.Histogram.remove(item);}
			else {this.Histogram.put(item, this.Histogram.get(item)-1);}
		}

		
	}

	@Override
	public void addItemKTimes(T item, int k) throws IllegalKValueException {
		if (k<1) {throw new IllegalKValueException(k);}
		else {
			if (this.Histogram.containsKey(item)){
				this.Histogram.put(item, this.Histogram.get(item)+k);}
			else {this.Histogram.put(item,k);}
		}
		
	}

	@Override
	public void removeItemKTimes(T item, int k) throws IllegalItemException, IllegalKValueException {
		if (k<1) {throw new IllegalKValueException(k);}
		if(!this.Histogram.containsKey(item)){throw new IllegalItemException();}
		if (k>this.Histogram.get(item)) {throw new IllegalKValueException(k);}
		for (int i=0;i<k;i++) {
			this.removeItem(item);
		}
	}

	@Override
	public int getCountForItem(T item) {
		if (this.Histogram.containsKey(item)) {return this.Histogram.get(item);}
		return 0; //else 
	}

	@Override
	public void addAll(Collection<T> items) {		
		if (!items.isEmpty()) {
		Iterator<T> iterator = items.iterator();
		do {
			this.addItem(iterator.next());
			}
			while(iterator.hasNext());
		}
		
	}

	@Override
	public void clear() {
		this.Histogram.clear();
	}

	@Override
	public Set<T> getItemsSet() {
		return this.Histogram.keySet();
	}

	@Override
	public void update(IHistogram<T> anotherHistogram) {
		Iterator<T> iterator = anotherHistogram.iterator(); 
		while (iterator.hasNext()) {
			T item = iterator.next(); 
			try {
				this.addItemKTimes(item, anotherHistogram.getCountForItem(item));
			} catch (IllegalKValueException e) {
				
			} 
		}
		}
	
	}
               
	

