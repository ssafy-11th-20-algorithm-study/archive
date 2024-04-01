package a0000.study;

import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
	int end, weight;
	
	public Node(int end, int weight) {
		this.end = end;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Node o) {
		return weight - o.weight;
	}
	
}
public class Solution_bj_9370_미확인도착지_서울_20반_권동원 {
	
	static int n,m,t,s,g,h;
	static ArrayList<Node>[] list;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_14502.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			list = new ArrayList[n+1];
			for(int j=1; j<=n; j++) {
				list[j] = new ArrayList<Node>();
			}
			
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			for(int j=0; j<m; j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				list[a].add(new Node(b, d));
				list[b].add(new Node(a, d));
			}
			int[] dest = new int[t];
			for(int j=0; j<t; j++) {
				dest[j] = Integer.parseInt(br.readLine());
			}
			
			PriorityQueue<Integer> pq = new PriorityQueue<>();
			for(int x: dest) {
				long res1 = dijkstra(s, h) + dijkstra(h, g) + dijkstra(g, x);
				long res2 = dijkstra(s, g) + dijkstra(g, h) + dijkstra(h, x);
				long res3 = dijkstra(s, x);
				
				if(Math.min(res1, res2) == res3) {
					pq.offer(x);
				}
			}
			while(!pq.isEmpty()) {
				sb.append(pq.poll() + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static long dijkstra(int start, int end) {
		boolean[] check = new boolean[n+1];
		int[] dist = new int[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Node curNode = pq.poll();
			int curTo = curNode.end;
			
			if(check[curTo]) continue;
			check[curTo] = true;
			
			for(Node node : list[curTo]) {
				if(dist[node.end] > dist[curTo] + node.weight) {
					dist[node.end] = dist[curTo] + node.weight;
					pq.offer(new Node(node.end, dist[node.end]));
				}
			}
		}
		return dist[end];
	}

}

