package a0000.camp;
import java.awt.Point;
import java.io.*;
import java.util.*;

public class Solution_d4_1251_하나로_서울_20반_권동원 {
	static int[] parents;
	static StringBuilder sb = new StringBuilder();
	
	public static class Edge implements Comparable<Edge> {
		int from, to;
		long w;
		
		public Edge(int from, int to, long w) {
			super();
			this.from = from;
			this.to = to;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Long.compare(this.w, o.w);
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_d4_1251.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//T
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		System.out.println(T);
		
		for(int tc=1; tc<=T; tc++) {
			// N
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			Point[] land = new Point[N];
			parents = new int[N];
			String answer;
			
			// X
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				land[i] = new Point(0,0); 
				land[i].x = Integer.parseInt(st.nextToken());
			}
			// Y
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				land[i].y = Integer.parseInt(st.nextToken());
			}
			//E
			st = new StringTokenizer(br.readLine());
			double E = Double.parseDouble(st.nextToken());
			
			// 간선 비용 저장
			ArrayList<Edge> edgeList = new ArrayList<>();
			for(int i=0; i<N; i++) {
				for(int j=i+1; j<N; j++) {
					long distX = Math.abs(land[i].x - land[j].x);
					long distY = Math.abs(land[i].y - land[j].y);
					edgeList.add(new Edge(i, j, distX*distX + distY*distY));
				}
			}
			
			edgeList.sort(null);
			for(int i=0; i<N; i++) {
				parents[i]=i;
			}
			
			int cnt=0;
			long res=0;
			for(Edge edge: edgeList) {
				if(union(edge.from, edge.to)) {
					res += edge.w;
					if(++cnt == N-1) break;
				}
			}
			sb.append("#").append(tc).append(" ").append(Math.round(res*E)).append("\n");
					
		}
		System.out.println(sb);
	}

	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}

	private static int find(int a) {
		if(a == parents[a]) return a;
		return parents[a]= find(parents[a]);
	}
}