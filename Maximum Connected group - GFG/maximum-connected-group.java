//{ Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int[][] grid = new int[n][n];
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    grid[i][j]=sc.nextInt();
                }
            }
            
            Solution obj = new Solution();
            int ans = obj.MaxConnection(grid);
            System.out.println(ans);
        }
    }
}

// } Driver Code Ends

class DisjointSet{
    ArrayList<Integer> parent = new ArrayList<>();
    ArrayList<Integer> size = new ArrayList<>();
    public DisjointSet(int n){
        for(int i =0; i<=n; i++){
            parent.add(i);
            size.add(1);
        }
    }

    public int findUParent(int node){
        if(parent.get(node) == node){
            return node;
        }
        int uParent = findUParent(parent.get(node));
        parent.set(node,uParent);
        return parent.get(node);
    }
    public void unionBySize(int u, int v){
        int u_parU = findUParent(u);
        int u_parV = findUParent(v);

        if (u_parV == u_parU) {
            return;
        }
        if (size.get(u_parU)<size.get(u_parV)){
            parent.set(u_parU,u_parV);
            size.set(u_parV, size.get(u_parU)+size.get(u_parV));
        }else {
            parent.set(u_parV, u_parU);
            size.set(u_parU,size.get(u_parU)+size.get(u_parV));
        }
    }
}

class Solution {
    public int MaxConnection(int grid[][]) {
        int n = grid.length;
        int mx = 0;
        DisjointSet ds = new DisjointSet(n*n);
        for(int i =0; i<n; i++){
            for(int j =0;j<n; j++){
                if(grid[i][j] == 0){
                    continue;
                }
                int[] delR = {-1,0,1,0};
                int[] delC = {0,1,0,-1};
                
                for(int it = 0; it<4; it++){
                    int newr = i+delR[it];
                    int newc = j+delC[it];
                    
                    if(newr>=0 && newc>=0 && newr<n && newc<n && grid[newr][newc]==1){
                        int cellNo = i*n+j;
                        int adjCellNo = newr*n+newc;
                        ds.unionBySize(cellNo, adjCellNo);
                    }
                }
            }
        }
        for(int row =0; row<n; row++){
            for(int col =0; col<n;col++){
                if(grid[row][col]==1){
                    continue;
                }
                int[] delR = {-1,0,1,0};
                int[] delC = {0,1,0,-1};
                
                HashSet<Integer> compo = new HashSet<>();
                for(int it = 0; it<4; it++){
                    int newr = row+delR[it];
                    int newc = col+delC[it];
                    
                    if(newr>=0 && newc>=0 && newr<n && newc<n && grid[newr][newc]==1){
                        int adjCellNo = newr*n+newc;
                        compo.add(ds.findUParent(adjCellNo));
                    }
                }
                int totalSize = 0;
                for(Integer it : compo){
                    totalSize+= ds.size.get(it);
                }
                mx = Math.max(mx, totalSize+1);
            }
        }
        //if all one in graph
        for(int cellNo =0; cellNo<n*n; cellNo++){
            mx = Math.max(mx, ds.size.get(ds.findUParent(cellNo)));
        }
        return mx;
    }
    
}