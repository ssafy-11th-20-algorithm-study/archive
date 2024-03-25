package samsung;

/**
 * 1초마다 연산 함
 * R연산: 배열 A의 모든 행에 대해서 정렬 수행
 *         행의 개수 >= 열의 개수
 * C연산: 배열 A의 모든 열에 대해서 정렬 수행
 행의 개수 < 열의 개수
 */
import java.util.*;
import java.io.*;
public class Solution_bj_17140_이차원배열과연산_서울_20반_손홍서 {
    /**
     * 배열의 크기 계속 *2
     */
    static int R, C, K;
    static int N, M;
    static int[][] map;
    static int[] counts;
    static int ans;
    static final int MAX_LEN = 100;

    static PriorityQueue<MyNumber> pq;

    static class MyNumber implements Comparable<MyNumber>{
        int num;
        int cnt;

        public MyNumber(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(MyNumber o) {
            if(o.cnt < cnt) {
                return 1;
            } else if(o.cnt == cnt) {
                if(o.num < num) {
                    return 1;
                }
            }
            return -1;
        }

        @Override
        public String toString() {
            return "MyNumber{" +
                    "num=" + num +
                    ", cnt=" + cnt +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()) - 1;
        C = Integer.parseInt(st.nextToken()) - 1;
        K = Integer.parseInt(st.nextToken());

        N = 3; //행 길이
        M = 3; //열 길이

        map = new int[MAX_LEN][MAX_LEN];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = 0;
        solution();
        System.out.println(ans);
    }

    static void solution() {
        while(true) {
            if (map[R][C] == K) {
                return;
            }

            if (ans == 100) {
                ans = -1;
                return;
            }

            boolean isRow = false;
            if (N >= M) {
                isRow = true;
            }

            int next = 0;
            if(isRow) {
                for (int i = 0; i < N; i++) {
                    count(i, isRow);
                    initPq();
                    int index = 0;
                    while (!pq.isEmpty()) {
                        if (index >= 100) {
                            break;
                        }
                        MyNumber o = pq.poll();
                        map[i][index] = o.num;
                        map[i][index + 1] = o.cnt;
                        index += 2;
                    }
                    next = Math.max(next, index);
                    for (int j = index; j < MAX_LEN; j++) {
                        map[i][j] = 0;
                    }
                }
            } else {
                for (int i = 0; i < M; i++) {
                    count(i, isRow);
                    initPq();
                    int index = 0;
                    while (!pq.isEmpty()) {
                        if (index >= 100) {
                            break;
                        }
                        MyNumber o = pq.poll();
                        map[index][i] = o.num;
                        map[index + 1][i] = o.cnt;
                        index += 2;
                    }
                    next = Math.max(next, index);
                    for (int j = index; j < MAX_LEN; j++) {
                        map[j][i] = 0;
                    }
                }
            }

            if (isRow) {
                M= next;
            } else {
                N = next;
            }
            ans++;
        }
    }



    static void initPq() {
        pq = new PriorityQueue<>();
        for(int i = 1; i <= 100; i++) {
            if(counts[i] != 0) {
                pq.offer(new MyNumber(i, counts[i]));
            }
        }
    }

    static void count(int n, boolean isRow) {
        counts = new int[101];

        if(isRow) {
            for(int i = 0; i < N; i++) {
                counts[map[n][i]]++;
            }
        } else {
            for(int i = 0; i < N; i++) {
                counts[map[i][n]]++;
            }
        }
    }

    static void print() {
        for(int i = 0; i < 100; i++) {
            for(int j = 0; j < 100; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("----");

    }
}
