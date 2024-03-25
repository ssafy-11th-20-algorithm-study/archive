package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_d4_1251_하나로_손홍서 {
    static class Link implements Comparable<Link> {

        int start;
        int target;
        double weight;

        public Link(int start, int target, double weight) {
            this.start = start;
            this.target = target;
            this.weight = weight;
        }

        @Override
        public int compareTo(Link o) {
            if(weight > o.weight) {
                return 1;
            }
            return -1;
        }
    }
    static int N;
    static double E;
    static PriorityQueue<Link> pq;
    static int[][] input;
    static int[] parent;
    static double ans;
    public static void main(String[] args) throws Exception{
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            N = Integer.parseInt(br.readLine());

            input = new int[N][2];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                input[i][0] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                input[i][1] = Integer.parseInt(st.nextToken());
            }

            E = Double.parseDouble(br.readLine());

            pq = new PriorityQueue<>();
            for(int i = 0; i < N - 1; i++) {
                for(int j = 1; j < N; j++) {
                    double w = E * Math.pow(getDist(input[i][0], input[i][1], input[j][0], input[j][1]), 2);
                    pq.offer(new Link(i, j, w));
                }
            }

            parent = new int[N];
            for(int i = 0; i < N; i++) {
                parent[i] = i;
            }

            ans = 0;
            solution();
            sb.append(Math.round(ans)).append("\n");

        }
        System.out.println(sb);
    }

    static void solution() {
        int cnt = 0;
        Link curr;
        while(cnt < N - 1) {
            curr = pq.poll();
            int pa = find(curr.start);
            int pb = find(curr.target);
            if(pa != pb) {
                union(pa, pb);
                ans += curr.weight;
                cnt++;
            }
        }
    }
    static int find(int a) {
        if(parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {
        if(a <= b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    static double getDist(int r1, int c1, int r2, int c2) {
        return Math.sqrt(Math.pow(r1 - r2, 2) + Math.pow(c1 - c2, 2));
    }
}
