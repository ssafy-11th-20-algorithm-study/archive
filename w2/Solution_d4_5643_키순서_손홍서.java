package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_d4_5643_키순서_손홍서 {
    static int N, M, ans;
    static int[][] dist;
    static final int INF = 999;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            dist = new int[N + 1][N + 1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    dist[i][j] = INF;
                }
            }

            int s, e;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                s = Integer.parseInt(st.nextToken());
                e = Integer.parseInt(st.nextToken());
                dist[s][e] = 1;
            }

            for (int k = 1; k <= N; k++) {
                for (int i = 1; i <= N; i++) {
                    for(int j = 1; j <= N; j++) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }

            ans = N;
            for (int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    if(i != j && dist[i][j] == INF && dist[j][i] == INF) {
                        ans--;
                        break;
                    }
                }
            }

            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}
