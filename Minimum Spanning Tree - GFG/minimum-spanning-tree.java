//{ Driver Code Starts


import java.util.*;
import java.io.*;
import java.lang.*;

public class Main{
	static BufferedReader br;
	static PrintWriter ot;
    public static void main(String args[]) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		ot = new PrintWriter(System.out);
		int t = Integer.parseInt(br.readLine().trim());
		while(t-- > 0){
			String s[] = br.readLine().trim().split(" ");
			int V = Integer.parseInt(s[0]);
			int E = Integer.parseInt(s[1]);
			int edges[][] = new int[E][3];
			for(int i = 0; i < E; i++){
				s = br.readLine().trim().split(" ");
				edges[i][0] = Integer.parseInt(s[0]);
				edges[i][1] = Integer.parseInt(s[1]);
				edges[i][2] = Integer.parseInt(s[2]);
			}
			ot.println(new Solution().spanningTree(V, E, edges));
		}
		ot.close();
	}
}
// } Driver Code Ends


// User function Template for Java
class pair{
    int first;
    int second;
    
    public pair(int first, int second){
        this.first=first;
        this.second =second;
    }
}
class tuple{
    int w;
    int node; 
    int parent;
    
    public tuple(int w, int node, int parent){
        this.w = w;
        this.node = node;
        this.parent = parent;
    }
}

class Solution{
	static int spanningTree(int V, int E, int edges[][]){
	    ArrayList<ArrayList<pair>> adj = new ArrayList<>();
	    for(int i =0; i<V;i++){
	        adj.add(new ArrayList<>());
	    }
	    for(int i=0; i<E;i++){
	        int s = edges[i][0];
	        int d = edges[i][1];
	        int w = edges[i][2];
	        
	        adj.get(s).add(new pair(d,w));
	        adj.get(d).add(new pair(s,w));
	    }
	    int sum =0;
	    PriorityQueue<tuple> pq = new PriorityQueue<>((x,y) -> x.w - y.w);
	    pq.add(new tuple(0,0,-1));
	    int[] vis = new int[V];
	    while(!pq.isEmpty()){
	        tuple t = pq.remove();
	        int node = t.node;
	        int wt = t.w;
	        int parent = t.parent;
	        if(vis[node] == 1){
	            continue;
	        }
	        sum+=wt;
	        vis[node] = 1;
	           
	        for(int i =0; i<adj.get(node).size(); i++){
	            int newN = adj.get(node).get(i).first;
	            int edW = adj.get(node).get(i).second;
	            
	            if(vis[newN] ==0){
	                pq.add(new tuple(edW, newN, node));
	            }
	        }
	    }
	    return sum;
	}
}