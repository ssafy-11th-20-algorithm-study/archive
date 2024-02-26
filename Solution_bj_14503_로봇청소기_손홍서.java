package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/14503
public class Solution_bj_14503_로봇청소기_손홍서 {
    static final int[] dr = {-1, 0, 1, 0}; // 상좌하우
    static final int[] dc = {0, -1, 0, 1};
    static int N, M;
    static int[][] map;
    static int totalCnt, ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        if(d % 2 == 1) {
            d = (d + 2) % 4;
        }

        totalCnt = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) {
                    totalCnt++;
                }
            }
        }

        ans = 0;
        dfs(r, c, d);
        System.out.println(ans);
    }

    static void dfs(int r, int c, int d) {
        if(map[r][c] == 0) {
            ans++;
            map[r][c] = -1;
        }
        if(totalCnt == ans) {
            return;
        }
        d = (d + 1) % 4;
        int nr = r + dr[d];
        int nc = c + dc[d];
        if(map[nr][nc] == 0) {
            dfs(nr, nc, d);
        }else {
            int cnt = 0;
            while(cnt++ < 3) {
                d = (d + 1) % 4;
                nr = r + dr[d];
                nc = c + dc[d];
                if(map[nr][nc] == 0) {
                    dfs(nr, nc, d);
                    return;
                }
            }
            d = (d + 4) % 4;
            int back = (d + 2) % 4;
            nr = r + dr[back];
            nc = c + dc[back];
            if(map[nr][nc] == 1) {
                return;
            }
            dfs(nr, nc, d);
        }
    }
}
