package samsung;

import java.io.*;
import java.util.*;
/*
 * 개수만큼 사다리 놓기
 * 검사하기
 */
public class Solution_bj_15684_사다리조작_서울_20반_손홍서 {

    static final int[] dr = {0, 0, 1};
    static final int[] dc = {1, -1, 0};
    static int N, M, H;
    static int[][] map;
    static boolean flag;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][N];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            map[r][c] = 1;
        }

        int ans = -1;
        for(int i = 0; i <= 3; i++) {
            flag = false;
            solution(0, 0, i);
            if(flag) {
                ans = i;
                break;
            }
        }
        System.out.println(ans);
    }

    static void solution(int cnt, int si, int size) {
        if(flag) {
            return;
        }
        if(cnt == size) {
            if(check()) {
                flag = true;
            }
            return;
        }
        //사디리 뽑기
        for(int i = si; i < H; i++) {
            for(int j = 0; j < N - 1; j++) {
                if(map[i][j] == 1) {
                    j++;
                    continue;
                }
                int l = j - 1;
                if(l >= 0 && map[i][l] == 1) {
                    continue;
                }
                int r = j + 1;
                if(r < N && map[i][r] == 1) {
                    j += 2;
                    continue;
                }

                map[i][j] = 1;
                solution(cnt + 1, si, size);
                map[i][j] = 0;
            }
        }
    }

    static boolean check() {
        for (int i = 0; i < N; i++) {
            int r = 0;
            int c = i;
            while(r < H) {
                if(map[r][c] == 1) {
                    c++;
                    r++;
                } else if(c - 1 >= 0 && map[r][c - 1] == 1) {
                    c--;
                    r++;
                } else {
                    r++;
                }
            }
            if(c != i) {
                return false;
            }
        }
        return true;
    }
}
