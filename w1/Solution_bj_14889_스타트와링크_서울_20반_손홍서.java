package samsung;

import java.util.*;
import java.io.*;

public class Solution_bj_14889_스타트와링크_서울_20반_손홍서 {

    static int N;
    static int[][] map;
    static int minDiff;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        minDiff = Integer.MAX_VALUE;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        combi(0, 0, N/2, new boolean[N]);
        System.out.println(minDiff);
    }

    public static void combi(int s, int L, int M, boolean[] ch) {
        if(L == M) {
            minDiff = Math.min(minDiff, getDiff(ch));
            return;
        }

        for(int i = s; i < N; i++) {
            ch[i] = true;
            combi(i + 1, L + 1, M, ch);
            ch[i] = false;
        }
    }

    public static int getDiff(boolean[] ch) {
        int linkSum = 0;
        int startSum = 0;
        for(int i = 0; i < N - 1; i++) {
            for(int j = i + 1; j < N; j++) {
                if(ch[i] && ch[j]) {
                    linkSum += (map[i][j] + map[j][i]);
                } else if(!ch[i] && !ch[j]) {
                    startSum += (map[i][j] + map[j][i]);
                }
            }
        }
        return Math.abs(linkSum - startSum);
    }
}
