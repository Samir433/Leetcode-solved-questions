//{ Driver Code Starts
// Initial Template for Java

import java.util.*;
import java.io.*;
import java.lang.*;

class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {

            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] adj = new int[m][3];

            for (int i = 0; i < m; i++) {

                for (int j = 0; j < 3; j++) {
                    adj[i][j] = sc.nextInt();
                }
            }

            int dist = sc.nextInt();
            Solution obj = new Solution();
            int ans = obj.findCity(n, m, adj, dist);
            System.out.println(ans);
        }
    }
}

// } Driver Code Ends


// User function Template for Java

class Solution {
      int findCity(int n, int m, int[][] edges,int distanceThreshold)
      {
         int[][] mat = new int[n][n];
         for(int i = 0; i<n; i++){
             for(int j =0; j<n; j++){
                 mat[i][j] = (int)(1e9);
             }
         }
         
         for(int i = 0; i<edges.length; i++){
             int u = edges[i][0];
             int v = edges[i][1];
             int wt = edges[i][2];
             
             mat[u][v] = wt;
             mat[v][u] = wt;
         }
         for(int i =0;i<n;i++){
             mat[i][i] = 0;
         }
         
         for(int via =0; via<n; via++){
             for(int i = 0;i<n;i++){
                 for(int j =0;j<n;j++){
                     mat[i][j] = Math.min(mat[i][j], mat[i][via]+mat[via][j]);
                 }
             }
         }
         int count = Integer.MAX_VALUE;
         int ans = 0;
         for(int i =0; i<n; i++){
             int temp =0;
             for(int j=0; j<n; j++){
                 if(mat[i][j]<=distanceThreshold){
                     temp++;
                 }
             }
             if(temp<=count){
                 count = temp;
                 ans = i;
             }
         }
         return ans;
      }
}
