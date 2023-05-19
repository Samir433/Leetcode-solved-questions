//{ Driver Code Starts
//Initial Template for Java

import java.util.*;
import java.io.*;
import java.lang.*;

class Gfg
{
    public static void main (String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        
        while(t-- > 0)
        {
            // arraylist of arraylist to represent graph
            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
            
            int V = Integer.parseInt(sc.next());
            int E = Integer.parseInt(sc.next());
            
            for(int i =0; i < V; i++)
                adj.add(i, new ArrayList<Integer>());
                
            for(int i = 1; i <= E; i++)
            {    int u = Integer.parseInt(sc.next());
                 int v = Integer.parseInt(sc.next());
                 
                 // adding directed edgese between 
                 // vertex 'u' and 'v'
                 adj.get(u).add(v);
            }
            
            Solution ob = new Solution();
            System.out.println(ob.kosaraju(V, adj));
		}
    }
}

// } Driver Code Ends


//User function Template for Java


class Solution
{
    private void dfs(int node, int[] vis, Stack<Integer> st, ArrayList<ArrayList<Integer>> adj){
        vis[node] = 1;
        for(int it : adj.get(node)){
            if(vis[it] == 0){
                dfs(it, vis, st, adj);
            }
        }
        
        st.push(node);
    }
    private void dfs2(int node, int[] vis, ArrayList<ArrayList<Integer>> adj){
        vis[node] = 1;
        for(int it : adj.get(node)){
            if(vis[it] == 0){
                dfs2(it, vis, adj);
            }
        }
    }
    //Function to find number of strongly connected components in the graph.
    public int kosaraju(int V, ArrayList<ArrayList<Integer>> adj)
    {
       int[] vis = new int[V];
       Stack<Integer> st = new Stack<>();
       for(int i =0; i<V; i++){
           if(vis[i] ==0){
               dfs(i, vis, st, adj);
           }
       }
       ArrayList<ArrayList<Integer>> rev = new ArrayList<>();
       for(int i =0; i<V;i++){
           rev.add(new ArrayList<>());
       }
       
       for(int i = 0; i<V; i++){
           vis[i] =0;
           for(int it : adj.get(i)){
               rev.get(it).add(i);
           }
       }
       
       int scc =0;
       while(!st.isEmpty()){
           int it = st.pop();
           if(vis[it] ==0){
               scc++;
               dfs2(it, vis, rev);
           }
       }
       return scc;
    }
}
