package samsung;

import java.io.*;
import java.util.*;
public class Solution_bj_15683_감시_서울_20반_손홍서 {
    /**
     * 1. cctv 방향 조합으로 결정
     * 2. cctv가 감시할 수 있는 부분 표시하면서 탐색
     * 3. 사각지대 체크
     *     3-1. 답보다 작으면 UPDATE
     */
    static int[][][] dir = { //각 CCTV를 90도로 돌렸을 때 감시 가능한 모든 방향
            {{}},
            {{0}, {1}, {2}, {3}}, // 1번 CCTV는 (우), (하), (좌), (상)으로 감시 가능
            {{0, 2}, {1, 3}}, // 2번 CCTV는 (우, 좌), (하, 상)으로 감시 가능
            {{3, 0}, {0, 1}, {1, 2}, {2, 3}}, //3번 CCTV는 (상, 우), (우, 하), (하, 좌), (좌, 상)으로 감시 가능
            {{2, 3, 0}, {3, 0, 1}, {0, 1, 2}, {1, 2, 3}}, //4번 CCTV는 (좌, 상, 우), (상, 우, 하), (우, 하, 좌), (하, 좌, 상)으로 감시 가능
            {{0, 1, 2 ,3}} // 5번 CCTV는 (우, 하, 좌, 상) 감시 가능
    };
    static int[] dr = {0, 1, 0, -1}; // 우, 하, 좌, 상
    static int[] dc = {1, 0, -1, 0};

    static int N, M;
    static int[][] map;
    static ArrayList<int[]> cctv; //cctv 위치 입력
    static boolean[][] v; // 감시 가능한 위치에 visit을 true로 설정할 것이므로 필요

    static int[][] arr; //조합으로 뽑은 각 cctv 설치 방향 저장할 배열

    static int ans;
    public static void main(String[] args) throws Exception{
        /**
         * 입력
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        cctv = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(0 < map[i][j] && map[i][j] < 6) {
                    cctv.add(new int[] {i, j});
                }
            }
        }

        arr = new int[cctv.size()][2]; //0번은 cctv 번호(1~5) 1번은 설치 방향

        ans = Integer.MAX_VALUE;
        solution(0, 0);
        System.out.println(ans);
    }

    static void solution(int cnt, int s) {
        if(cnt == cctv.size()) {
            v = new boolean[N][M];
            for(int i = 0; i < arr.length; i++) {
                int r = cctv.get(i)[0];
                int c = cctv.get(i)[1];
                int index = arr[i][0];
                int d = arr[i][1];

                v[r][c] = true;
                for(int j = 0; j < dir[index][d].length; j++) {
                    mark(r, c, index, d, j);
                }
            }
            ans = Math.min(ans, getCount());
            return;
        }

        //설치 방향을 정할 cctv의 위치 가져오기
        int r = cctv.get(cnt)[0];
        int c = cctv.get(cnt)[1];
        //해당 cctv 번호 가져오기(1~5)
        int index = map[r][c];

        //가능한 모든 방향에 설치해보기
        for(int i = s; i < dir[index].length; i++) {
            arr[cnt][0] = index;
            arr[cnt][1] = i;
            //다음 cctv로 넘어가기
            solution(cnt + 1, 0);
        }
    }

    static void mark(int r, int c, int index, int d, int i) {
        int nr = r + dr[dir[index][d][i]];
        int nc = c + dc[dir[index][d][i]];
        if(0 <=  nr && nr < N && 0 <= nc && nc < M && map[nr][nc] != 6) {
            v[nr][nc] = true;
            mark(nr, nc, index, d, i);
        }
    }

    static int getCount() {
        int cnt = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 0 && !v[i][j]) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
