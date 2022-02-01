package il.ac.tau.cs.sw1.ex8.wordsRank;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import il.ac.tau.cs.sw1.ex8.histogram.HashMapHistogram;
import il.ac.tau.cs.sw1.ex8.histogram.HashMapHistogramIterator;
import il.ac.tau.cs.sw1.ex8.histogram.IHistogram;
import il.ac.tau.cs.sw1.ex8.histogram.HashMapHistogramIterator.Comprator;
import il.ac.tau.cs.sw1.ex8.wordsRank.RankedWord.rankType;

/**************************************
 *  Add your code to this class !!!   *
 **************************************/

public class FileIndex {
	
	public static final int UNRANKED_CONST = 30; 
	public HashMap<String, HashMapHistogram<String>> FilesHistogram; 
	public HashMap<String, HashMap<String, Integer>> RankedWordsByFile;
	public HashMap<String, RankedWord> RankedWordsTotal; 
	

	/*
	 * @pre: the directory is no empty, and contains only readable text files
	 */
	
	public void indexDirectory(String folderPath) {
		//This code iterates over all the files in the folder. add your code wherever is needed 
		File folder = new File(folderPath);
		File[] listFiles = folder.listFiles();
		HashMap<String, HashMapHistogram<String>> mainHashMap = new HashMap<String, HashMapHistogram<String>>();
		for (File file : listFiles) {
			// for every file in the folder
			if (file.isFile()) { 
				try {
				List<String> tokens= FileUtils.readAllTokens(file); 
				HashMapHistogram<String> fileHashMap = new HashMapHistogram<String>(); 
				fileHashMap.addAll(tokens);
				String fileName= file.toString();
				fileName = fileName.substring(fileName.lastIndexOf("\\")+1); 
				mainHashMap.put(fileName, fileHashMap);
				}
				catch (IOException ex) {
				 //Valid input guaranteed 
				}
				
			}
		}
		
		this.FilesHistogram= mainHashMap;
		this.updateRankedWordsByFile();
		this.updateRankedWordsTotal();
	}
	
  	public void updateRankedWordsByFile() {
  		this.RankedWordsByFile =new HashMap<String, HashMap<String, Integer>>();
  		HashMap<String, HashMap<String, Integer>> temp = new HashMap<String, HashMap<String, Integer>>();
  		for (String file: this.FilesHistogram.keySet()) {
  			for (String word: this.FilesHistogram.get(file).getItemsSet()){
  				if (temp.get(word)==null) {
  					HashMap<String, Integer> map = new HashMap<String, Integer>();
  					temp.put(word, map);}
  				try {
  				temp.get(word).put(file, this.getRankForWordInFile(file, word));
  				}
  				catch (FileIndexException ex) {
  					//Valid input guaranteed 
  					}	
  			}
  		}
  		HashMap<String,Integer> unsortedFileList; 
  		HashMap<String,Integer> reverseSortedMap = new HashMap<String,Integer>(); 
  		for (String word : temp.keySet()) {
  			unsortedFileList = temp.get(word); 
  			unsortedFileList.entrySet()
  		    .stream()
  		    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) 
  		    .forEachOrdered(x -> {
				try {
					reverseSortedMap.put(x.getKey(),getRankForWordInFile(x.getKey(),word));
				} catch (FileIndexException e) {
					//Valid input guaranteed
				}
			});
  			
  		this.RankedWordsByFile.put(word, reverseSortedMap); 
  		}
  	}
  		
  		
  	
  	public void updateRankedWordsTotal() {
  		this.RankedWordsTotal = new HashMap<String, RankedWord>(); 
  		RankedWord rank; 
  		for (String word: this.RankedWordsByFile.keySet()) {
  			rank = new RankedWord(word,this.RankedWordsByFile.get(word));
  			this.RankedWordsTotal.put(word, rank); 
  		}
  	}
  	
  	/*
	 * @pre: the index is initialized
	 * @pre filename is a name of a valid file
	 * @pre word is not null
	 */
	public int getCountInFile(String filename, String word) throws FileIndexException{
		word= word.toLowerCase(); 
		if (this.FilesHistogram.containsKey(filename)) {
			return this.FilesHistogram.get(filename).getCountForItem(word);}
		throw new FileIndexException(filename); 
	}
	
	/*
	 * @pre: the index is initialized
	 * @pre filename is a name of a valid file
	 * @pre word is not null
	 */
	public int getRankForWordInFile(String filename, String word) throws FileIndexException{
		HashMapHistogram<String> fileHistogram = this.FilesHistogram.get(filename); 
		if (fileHistogram==null) {throw new FileIndexException(filename);}
		if (fileHistogram.getItemsSet().contains(word)) {
			// file contains the word, calculating the rank 
			HashMapHistogramIterator<String> iterator = (HashMapHistogramIterator<String>) fileHistogram.iterator();
			int count=0;
			String s; 
			while (iterator.hasNext()) {
				count+=1; 
				s=iterator.next(); 
				if (s.equals(word)) {return count;}
			}
		}
			//(else) word is not in file
			//return how many different words are in the file + 30 
			return fileHistogram.getItemsSet().size()+30; 
	}
	
	/*
	 * @pre: the index is initialized
	 * @pre word is not null
	 */
	public int getAverageRankForWord(String word){
		return this.RankedWordsTotal.get(word).getRankByType(rankType.average); 
	}
	
	public RankedWord getRankedWord(String word) {
		RankedWord s= this.RankedWordsTotal.get(word); 
		return s; 
	}
	
	
	public List<RankedWord> getSortedListByType(rankType rTypr){
		//convert HashMap into List   
		List<RankedWord> list = new LinkedList<RankedWord>(this.RankedWordsTotal.values());  
		//sorting the list elements  
		Collections.sort(list, new RankedWordComparator(rTypr)); 
		return list; 
	}
	
	public List<String> getWordsWithAverageRankSmallerThanK(int k){
		List<String> res = new ArrayList<String>(); 
		Iterator<RankedWord> iter = this.getSortedListByType(rankType.average).iterator();
		RankedWord s; 
		while (iter.hasNext()) {
			s=iter.next();
			if (s.getRankByType(rankType.average)<k) {
				res.add(s.getWord()); 
			}
		}
		return res; 
	}
	
	public List<String> getWordsWithMinRankSmallerThanK(int k){
		List<String> res = new ArrayList<String>(); 
		Iterator<RankedWord> iter = this.getSortedListByType(rankType.min).iterator();
		RankedWord s; 
		while (iter.hasNext()) {
			s=iter.next();
			if (s.getRankByType(rankType.min)<k) {
				res.add(s.getWord()); 
			}
		}
		return res;
	}
	
	public List<String> getWordsWithMaxRankSmallerThanK(int k){
		List<String> res = new ArrayList<String>(); 
		Iterator<RankedWord> iter = this.getSortedListByType(rankType.max).iterator();
		RankedWord s; 
		while (iter.hasNext()) {
			s=iter.next();
			if (s.getRankByType(rankType.max)<k) {
				res.add(s.getWord()); 
			}
		}
		return res;
	}

}
