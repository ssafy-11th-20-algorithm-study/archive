package samsung;

import java.io.*;
import java.util.*;
public class Solution_bj_16234_인구이동_서울_20반_손홍서 {
    static int N, L, R;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static ArrayList<int[]> list;
    static boolean[][] v;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;

        while(true) {
            boolean flag = false;
            v = new boolean[N][N];
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(v[i][j]) {
                        continue;
                    }
                    if(solution(i, j)) {
                        flag = true;
                    }
                }
            }
            if(flag) {
                day++;
            } else {
                break;
            }
        }

        System.out.println(day);
    }

    static boolean solution(int r, int c) {
        Queue<int[]> q = new ArrayDeque<>();
        list = new ArrayList<>();
        q.offer(new int[]{r, c});
        list.add(new int[]{r, c});
        v[r][c] = true;

        int sum = map[r][c];
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            r = curr[0];
            c = curr[1];
            for(int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if(0 <= nr && nr < N && 0 <= nc && nc < N && !v[nr][nc]) {
                    int diff = Math.abs(map[r][c] - map[nr][nc]);
                    if(L <= diff && diff <= R) {
                        q.offer(new int[] {nr, nc});
                        list.add(new int[] {nr, nc});
                        v[nr][nc] = true;
                        sum += map[nr][nc];
                    }
                }
            }
        }

        if(list.size() == 1) {
            return false;
        }

        int mark = sum / list.size();
        for(int[] curr : list) {
            map[curr[0]][curr[1]] = mark;
        }
        return true;
    }
}

