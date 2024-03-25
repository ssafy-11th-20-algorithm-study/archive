package bj.dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_bj_9370_미확인도착지_손홍서 {
    static final int INF = Integer.MAX_VALUE;
    static int N, M, E, S, G, H;
    static ArrayList<Link>[] adjList;
    static int[] targets;
    static int[] dist;
    static ArrayList<Integer> ans;
    static class Link implements Comparable<Link>{
        int e;
        int w;
        Link(int e, int w) {
            this.e = e;
            this.w = w;
        }

        public int compareTo(Link l) {
            if(w > l.w) {
                return 1;
            }
            return -1;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            adjList = new ArrayList[N + 1];
            targets = new int[E];
            for(int i = 1; i <= N; i++) {
                adjList[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            G = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());

                adjList[s].add(new Link(e, w));
                adjList[e].add(new Link(s, w));
            }

            ans = new ArrayList<>();
            for(int i = 0; i < E; i++) {
                targets[i] = Integer.parseInt(br.readLine());
                int min = dijkstra(S, targets[i]);
                if(dijkstra(S, G) + dijkstra(G, H) + dijkstra(H, targets[i]) == min) {
                    ans.add(targets[i]);
                } else if(dijkstra(S, H) + dijkstra(H, G) + dijkstra(G, targets[i]) == min){
                    ans.add(targets[i]);
                }
            }

            Collections.sort(ans);
            for(int x : ans) {
                sb.append(x + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    //targets로 Dijkstra
    //S || G를 지나지 않고 가는 길 자르기
    static int dijkstra(int start, int target) {
        dist = new int[N + 1];
        for(int i = 1;  i <= N; i++) {
            dist[i] = INF;
        }
        PriorityQueue<Link> pq = new PriorityQueue<>();
        pq.offer(new Link(start, 0));
        dist[start] = 0;
        while(!pq.isEmpty()) {
            Link curr = pq.poll();
            if(curr.e == target) {
                return dist[target];
            }
            for(int i = 0; i < adjList[curr.e].size(); i++) {
                Link next = adjList[curr.e].get(i);

                if(dist[curr.e] + next.w < dist[next.e]) {
                    dist[next.e] = dist[curr.e] + next.w;
                    pq.offer(new Link(next.e, dist[curr.e] + next.w));
                }
            }
        }
        return Integer.MIN_VALUE;
    }
}