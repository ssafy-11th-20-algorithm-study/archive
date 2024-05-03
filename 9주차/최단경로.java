package a0430;

import java.io.*;
import java.util.*;

class Node {
	int vertex, weight;
	Node next;
	
	Node(int vertex, int weight, Node next) {
		this.vertex = vertex;
		this.weight = weight;
		this.next = next;
	}
}

public class 최단경로 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/0430/최단경로.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int start = Integer.parseInt(br.readLine()) - 1;
		int[] minDist = new int[V];
		Arrays.fill(minDist, Integer.MAX_VALUE);
		minDist[start] = 0;
		boolean[] visited = new boolean[V];
		
		Node[] adj = new Node[V];
		
		for (int e = 0; e < E; e ++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			
			adj[u] = new Node(v, w, adj[u]);
		}
		
		for (int i = 0; i < V; i ++) {
			int minV = -1;
			int min = Integer.MAX_VALUE;
			
			for (int j = 0; j < V; j ++) {
				if (!visited[j] && minDist[j] < min) {
					minV = j;
					min = minDist[j];
				}
			}
			if (minV == -1) break;
			visited[minV] = true;
			
			for (Node temp = adj[minV]; temp != null; temp = temp.next) {
				if (!visited[temp.vertex] && temp.weight + min < minDist[temp.vertex]) {
					minDist[temp.vertex] = temp.weight + min;
				}
			}
		}
		
		for (int v = 0; v < V; v ++) {
			if (minDist[v] == Integer.MAX_VALUE) {
				sb.append("INF").append("\n");
			} else {
				sb.append(minDist[v]).append("\n");
			}
		}
		System.out.println(sb.toString());
		
		br.close();
	}

}
