package a0301;

import java.io.*;
import java.util.*;

public class Main_bj_16235_나무_재태크_서울_20반_심재운 {

    static int N, M, K;
    static int[][] food, curr; // 상도가 주는 양분, 현재 칸의 양분
    static PriorityQueue<int[]> trees = new PriorityQueue<>((x, y) -> x[2] - y[2]); // 모든 나무
    static ArrayDeque<int[]> deadTree = new ArrayDeque<>(); // 죽은 나무
    static final int[] dy = { 0, 0, -1, 1, -1, 1, -1, 1 };
    static final int[] dx = { 1, -1, 0, 0, 1, -1, -1, 1 };

    static void spring() {
        PriorityQueue<int[]> temp = new PriorityQueue<>((x, y) -> x[2] - y[2]);

        while (!trees.isEmpty()) {
            int[] tree = trees.poll();
            int r = tree[0];
            int c = tree[1];
            int f = tree[2];

            if (curr[r][c] >= f) {
                curr[r][c] -= f;
                temp.offer(new int[] { r, c, f + 1 });
            } else {
                deadTree.offer(new int[] { r, c, f });
            }
        }

        trees = temp;
    }

    static void summer() {
        while (!deadTree.isEmpty()) {
            int[] tree = deadTree.poll();
            int r = tree[0];
            int c = tree[1];
            int f = tree[2];

            int g = f / 2;
            curr[r][c] += g;
        }
    }

    static void fall() {
        ArrayList<int[]> temp = new ArrayList<>();
        for (int[] t : trees) {
            if (t[2] % 5 == 0) {
                for (int d = 0; d < 8; d++) {
                    int nr = t[0] + dy[d];
                    int nc = t[1] + dx[d];
                    if (0 <= nr && nr < N && 0 <= nc && nc < N) {
                        temp.add(new int[] { nr, nc, 1 });
                    }
                }
            }
        }

        for (int[] t : temp) {
            trees.offer(new int[] { t[0], t[1], t[2] });
        }
    }

    static void winter() {
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                curr[y][x] += food[y][x];
            }
        }
    }

    static void getAge() {
        for (int[] t : trees) {
            t[2] += 1;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/0301/Main_bj_16235_나무_재태크_서울_20반_심재운.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long before = System.currentTimeMillis();
        
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        curr = new int[N][N];
        food = new int[N][N];

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < N; x++) {
                food[y][x] = Integer.parseInt(st.nextToken());
                curr[y][x] = 5;
            }
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());
            trees.offer(new int[] { y, x, z });
        }

        int count = 0;

        while (count < K) {

            spring();
            summer();
            fall();
            winter();
            //getAge();

            count++;
        }

        long after = System.currentTimeMillis();
        
        System.out.println(trees.size());
        //System.out.println((after - before) / 1000);
    }

}