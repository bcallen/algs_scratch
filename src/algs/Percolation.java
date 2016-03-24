package algs;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	private final static UF_Algorithm ACTIVE_ALGORITHM = UF_Algorithm.Weighted_Union_Find;
		
	private int[] grid;
	private int[] grid_depth;
	private int N;
	private int TOP;
	private int BOTTOM;
	private static final int EDGE = -1;
	
    // create N-by-N grid, with all sites blocked
	public Percolation(int N)  {
		boolean track_depth=false;
		if (ACTIVE_ALGORITHM == UF_Algorithm.Weighted_Quick_Union_With_Path_Compression ||
				ACTIVE_ALGORITHM == UF_Algorithm.Weighted_Union_Find){
			track_depth = true;
		}
		
		this.N = N;
		if (N <= 0) throw new IllegalArgumentException();
		//n by n grid, plus two virtual nodes top and bottom
		int cellCount = (N * N) + 2;
		
		//Labels for virtual top and bottom nodes
		TOP = N * N;
		BOTTOM = (N * N) + 1;
		
		grid = new int[cellCount];
		grid_depth = new int[cellCount];
		
		for (int i=0; i < cellCount; i++){
			grid[i] = -i;
			if(track_depth){grid_depth[i]=0;}
		}
		
	}
    // open site (row i, column j) if it is not open already
	   public void open(int i, int j){
		   int pos = get_position(i,j);
		   int[] neighbors; 
		   if (grid[pos] < 0) {
			   grid[pos] = -grid[pos];
		   }
		   neighbors = get_neighbors(pos);
		   for(i=0; i<=neighbors.length; i++){
			  int neighbor = neighbors[i];
			  if (neighbor >= 0){
				  union(pos,neighbor);
			  }
		   }
	   }
	   // is site (row i, column j) open?
	   public boolean isOpen(int i, int j){
		   int pos = get_position(i,j);
		   return grid[pos] >= 0;
	   }
	     // is site (row i, column j) full?
	   public boolean isFull(int i, int j){
		   int pos = get_position(i,j);
		   return find(pos, TOP);
	   }
	    // does the system percolate?
	   public boolean percolates(){
		   return find(TOP, BOTTOM);
	   }

	   // test client (optional)
	   public static void main(String[] args){
		   
	   }
	   
	   //route union calls to active algorithm
	   private void union(int pos1, int pos2){
		   if (ACTIVE_ALGORITHM == UF_Algorithm.Weighted_Union_Find){
			   union_wqu(pos1, pos2);
		   }
	   }
	   
	   private boolean find(int pos1, int pos2){
		   boolean b_find = false;
		   if (ACTIVE_ALGORITHM == UF_Algorithm.Weighted_Union_Find){
			   b_find = find_wqu(pos1, pos2);
		   }
		   
		   return b_find;
	   }
	   
	   
	   //check whether point is valid, else throws exception. 
	   //returns position in grid represented by coords
	   private int get_position(int i, int j){
		   if (i < 1 || j < 1 || i > N || j > N){
			   throw new IndexOutOfBoundsException();
		   }
		   int pos = (i - 1) + ((j - 1) * N);
		   return pos;
	   }
	   
	   
	   private int[] get_neighbors(int pos){
		   int No, Ea, So, We;
		   
		   if(pos % N == 0){
			   No = TOP;
		   } else {
			   No = pos - 1;
		   }
		   
		   if(pos % N == N-1){
			   So = BOTTOM;
		   } else {
			   So = pos + 1;
		   }
		   
		   if(pos - N < 0){
			   We = EDGE;
		   } else {
			   We = pos - N;
		   }
		   
		   if(pos + N >= N*N){
			   Ea = EDGE;
		   } else {
			   Ea = pos + N;
		   }
		   
		  return new int[] {No, Ea, So, We};
		   
	   }

	   //find operation - weighted quick union form
	   private boolean find_wqu(int pos1, int pos2){
		   return get_root_wqu(pos1) == get_root_wqu(pos2);
	   }
	   
	   private int get_root_wqu(int pos){
		   while(pos != grid[pos]){
			   pos = grid[pos];
		   }
		   return pos;
	   }
	   
	   //union operation - weighted quick union form
	   private void union_wqu(int pos1, int pos2){
		   if(grid_depth[pos1] < grid_depth[pos2]){
			   grid[pos1] = grid[pos2];
			   grid_depth[pos1] += 1;
		   } else {
			   grid[pos2] = grid[pos1];
			   grid_depth[pos2] += 1;
		   }		   
	   }
	   
	   private void union_wquwpc(int pos1, int pos2){
		   
	   }
	   
	   private void union_lqu(int pos1, int pos2){
		   grid[pos1] = grid[pos2];
	   }
}
