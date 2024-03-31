package homework;

import java.io.*;
import java.util.*;


/*
 * 결국 다익스트라로 구한 최단 경로가 해당 경로를 포함하고 있는지 아닌지 확인하는 문제
 * 1. 간선의 개수가 훨씬 많고 노드가 적어서 인접행렬 불가능 -> 인접리스트
 * 2. 방법 :  1. 구차하게 확인하기 (1 ->g -> h-> 도착지)  (플로이드 워셜은 n이 너무 커서 불가능)
 * 			 2. 간선의 가중치 변경 (짝,홀)
 * 			 3. 경로 리스트 만들어서 들어가 있는지 확인 ?
 * 
 */
public class Main_g2_9370_미확인도착지_서울_20반_김도한 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static final int INF = Integer.MAX_VALUE;
	private static int T;
	private static int V, E, t;
	private static List<List<Node>> list;
	private static int dist[];
	private static List<Integer> candidate;

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());

			dist = new int[V + 1];
			Arrays.fill(dist, INF);

			list = new ArrayList<>();
			for (int j = 0; j < V + 1; j++)
				list.add(new ArrayList<>());

			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());

			for (int j = 0; j < E; j++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());

				if ((u == g && v == h) || (u == h && v == g)) {
					list.get(u).add(new Node(v, weight * 2 - 1));
					list.get(v).add(new Node(u, weight * 2 - 1));
				} else {
					list.get(u).add(new Node(v, weight * 2));
					list.get(v).add(new Node(u, weight * 2));
				}
			}

			// 목적지 후보를 저장한다.
			candidate = new ArrayList<>();
			for (int j = 0; j < t; j++)
				candidate.add(Integer.parseInt(br.readLine()));

			dijkstra(start);

			Collections.sort(candidate);

			for (int num : candidate) {
				if (dist[num] % 2 == 1)
					bw.write(num + " ");
			}
			bw.write("\n");
		}

		br.close();
		bw.close();
	}

	private static void dijkstra(int u) {
		boolean[] visited = new boolean[V + 1];
		PriorityQueue<Node> pq = new PriorityQueue<>();

		dist[u] = 0;
		pq.add(new Node(u, 0));

		while (!pq.isEmpty()) {
			Node currentNode = pq.poll();

			if (visited[currentNode.v])
				continue;
			visited[currentNode.v] = true;

			for (Node node : list.get(currentNode.v)) {
				if (!visited[node.v] && dist[node.v] > dist[currentNode.v] + node.weight) {
					dist[node.v] = dist[currentNode.v] + node.weight;
					pq.add(new Node(node.v, dist[node.v]));
				}
			}
		}

	}

	static class Node implements Comparable<Node> {

		int v, weight;

		public Node(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return weight - o.weight;
		}
	}

}
