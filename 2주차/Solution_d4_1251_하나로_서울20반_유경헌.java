package com.algorithm.study.eclipse;

import java.io.*;
import java.util.*;

public class Solution_d4_1251_하나로_서울20반_유경헌 {
	static double e;
	static int[] parent; // 크루스칼(유니온 + 파인드)에 사용할 부모 저장 배열

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			parent = new int[N]; // 부모 배열 초기화
			for (int i = 0; i < N; i++) {
				parent[i] = i;
			}
			Point[] points = new Point[N]; // 좌표들 저장
			for (int i = 0; i < N; i++) {
				points[i] = new Point(); // 좌표 배열 초기화
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				points[i].x = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				points[i].y = Integer.parseInt(st.nextToken());
			}
			e = Double.parseDouble(br.readLine());
			PriorityQueue<Edge> pq = new PriorityQueue<>(); 
			for (int i = 0; i < N - 1; i++) { // 우선 순위 큐에 각 좌표 사이의 값 저장
				for (int j = i; j < N; j++) {
					pq.offer(new Edge(i, j, calcCost(points[i], points[j])));
				}
			}
			int cnt = 0;
			double ans = 0;
			while (cnt < N - 1) { // 정점 - 1 만큼 간선 선택하면 끝 (트리)
				Edge cur = pq.poll();
				if (union(cur.s, cur.e)) {
					ans += cur.v;
					cnt++;
				}
			}
			sb.append("#").append(tc).append(" ").append(String.format("%.0f", ans)).append("\n");
		}
		System.out.print(sb);
	}

	// 값 계산
	static double calcCost(Point p1, Point p2) {
		return e * ((Math.pow((p1.x - p2.x), 2)) + (Math.pow((p1.y - p2.y), 2)));
	}

	// find 부모 찾기
	static int find(int cur) {
		if (cur != parent[cur]) {
			parent[cur] = find(parent[cur]);
		}
		return parent[cur];
	}

	// union 부모 다르면 한쪽에 합치면서 합쳤는지 리턴
	static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x == y) {
			return false;
		}
		parent[y] = x;
		return true;
	}

	// 간선 클래스 시작, 끝, 값 가지고 값으로 비교
	static class Edge implements Comparable<Edge> {
		int s, e;
		double v;

		public Edge(int s, int e, double v) {
			this.s = s;
			this.e = e;
			this.v = v;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(v, o.v);
		}

	}

	// 정점 클래스
	static class Point {
		int x, y;

	}
}