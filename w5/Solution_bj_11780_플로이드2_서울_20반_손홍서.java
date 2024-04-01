package bj.shortestway;


import java.util.*;
import java.io.*;

public class Solution_bj_11780_플로이드2_서울_20반_손홍서 {

    static int N, M;
    static int[][] D;
    static int[][] P;
    static final int INF = 100 * 100_000 + 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        D = new int[N + 1][N + 1];
        P = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(D[i], INF);
            D[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            if (D[s][e] > d) {
                D[s][e] = d;
                P[s][e] = s;
            }
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                if (k == i) {
                    continue;
                }
                for (int j = 1; j <= N; j++) {
                    if (k == j || i == j) {
                        continue;
                    }
                    if (D[i][j] > D[i][k] + D[k][j]) {
                        D[i][j] = D[i][k] + D[k][j];
                        P[i][j] = P[k][j];
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (D[i][j] == INF) {
                    sb.append(0).append(" ");
                } else {
                    sb.append(D[i][j]).append(" ");
                }
            }
            sb.append("\n");
        }

        Stack<Integer> s;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (D[i][j] >= INF || D[i][j] == 0) {
                    sb.append("0");
                } else if (P[i][j] == j) {
                    sb.append(2 + " " + i + " " + j);
                } else {
                    s = new Stack();

                    int now = j;
                    s.push(now);

                    while(i != now) {
                        now = P[i][now];
                        s.push(now);
                    }
                    sb.append(s.size()).append(" ");
                    while (!s.isEmpty()) {
                        sb.append(s.pop()).append(" ");
                    }
                }
                sb.append("\n");
            }
        }

        System.out.println(sb);
        br.close();
    }
}
