package algs;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	private final static UF_Algorithm ACTIVE_ALGORITHM = UF_Algorithm.Weighted_Union_Find;
	
	private int[] grid;
	private int N;
    // create N-by-N grid, with all sites blocked
	public Percolation(int N)  {
		this.N = N;
		//n by n grid, plus two virtual nodes top and bottom
		int cellCount = (N * N) + 2; 
		grid = new int[cellCount];
		for (int i=0; i < cellCount; i++){
			grid[i] = -i;
		}
	}
    // open site (row i, column j) if it is not open already
	   public void open(int i, int j){
		   int pos = getPosition(i,j);
		   if (grid[pos] < 0) {
			   grid[pos] = -grid[pos];
		   }
	   }
	   // is site (row i, column j) open?
	   public boolean isOpen(int i, int j){
		   int pos = getPosition(i,j);
		   return grid[pos] >= 0;
	   }
	     // is site (row i, column j) full?
	   public boolean isFull(int i, int j){
		   int pos = getPosition(i,j);
		   return true;
	   }
	    // does the system percolate?
	   public boolean percolates(){
		   
		   return true;
	   }

	   // test client (optional)
	   public static void main(String[] args){
		   
	   }
	   
	   private void union(int pos1, int pos2){
		   
	   }
	   
	   private boolean find(int pos1, int pos2){
		
		   return true;
	   }
	   
	   
	   //check whether point is valid, else throws exception. 
	   //returns position in grid represented by coords
	   private int getPosition(int i, int j){
		   if (i < 1 || j < 1 || i > N || j > N){
			   throw new IndexOutOfBoundsException();
		   }
		   int pos = (i - 1) + ((j - 1) * N);
		   return pos;
	   }
	   
	   private void weighted_quick_union(int pos1, int pos2){
		   
	   }
	   
	   private void weighted_quick_union_wpc(int pos1, int pos2){
		   
	   }
	   
	   private void lazy_quick_union(int pos1, int pos2){
		   
	   }
	   
}
