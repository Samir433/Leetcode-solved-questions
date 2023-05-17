//{ Driver Code Starts
//Initial Template for Java

import java.util.*;
import java.lang.*;
import java.io.*;


// } Driver Code Ends
//User function Template for Java
class DisjointSet{
    ArrayList<Integer> rank = new ArrayList<>();
    ArrayList<Integer> parent = new ArrayList<>();

    ArrayList<Integer> size = new ArrayList<>();
    public DisjointSet(int n){
        for(int i =0; i<=n; i++){
            rank.add(0);
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

    public void unionByRank(int u, int v){
        int u_parU = parent.get(u);
        int u_parV = parent.get(v);

        if (u_parV == u_parU) {
            return;
        }
        //smaller rank attached to greater does not change in rank
        if (rank.get(u_parU)<rank.get(u_parV)){
            parent.set(u_parU, u_parV);
        }else if(rank.get(u_parU)>rank.get(u_parV)){
            parent.set(u_parV,u_parU);
        }else{
            parent.set(u_parU, u_parV);
            int rankU = rank.get(u_parU);
            rank.set(u_parU,rankU+1);
        }
    }
    public void unionBySize(int u, int v){
        int u_parU = parent.get(u);
        int u_parV = parent.get(v);

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
    public List<Integer> numOfIslands(int rows, int cols, int[][] operators) {
        List<Integer> ans = new ArrayList<>();
        DisjointSet ds = new DisjointSet(rows*cols);
        int[][] vis = new int[rows][cols];
        int count = 0;
        int len = operators.length;
        for(int i =0; i<len; i++){
            int r = operators[i][0];
            int c = operators[i][1];
            
            if(vis[r][c]==1){
                ans.add(count);
                continue;
            }
            vis[r][c] = 1;
            count++;
            
            int[] drow = {-1,0,1,0};
            int[] dcol = {0,1,0,-1};
            
            for(int j =0; j<4; j++){
                int adjr = drow[j] +r;
                int adjc = dcol[j]+c;
                
                if(adjr>=0 && adjc>=0 && adjr<rows && adjc<cols){
                    if(vis[adjr][adjc]==1){
                        int adjNode = adjr*cols + adjc;
                        int nodeNo = r*cols + c;
                        
                        if(ds.findUParent(nodeNo) != ds.findUParent(adjNode)){
                            count--;
                            ds.unionByRank(nodeNo, adjNode);
                        }
                    }
                }
            }
            ans.add(count);
        }
        return ans;
    }
}

//{ Driver Code Starts.

class GFG {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int  k= sc.nextInt();
            int[][] a = new int[k][2];
            for(int i=0;i<k;i++){
            
                a[i][0] = sc.nextInt();
                a[i][1] = sc.nextInt();
            }
            
            Solution obj = new Solution();
            List<Integer> ans = obj.numOfIslands(n,m,a);
           
            for(int i:ans){
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }
}

// } Driver Code Ends