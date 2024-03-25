package samsung;

import java.util.*;
import java.io.*;

public class Solution_bj_14890_경사로_서울_20반_손홍서 {
    static int N, L;
    static int[][] map;
    static boolean[][] visit;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visit = new boolean[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution());
    }

    static int solution() {
        int ans = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 1; j < N; j++) {
                if(map[i][j-1] != map[i][j]) {
                    //오르막
                    if(map[i][ j- 1] - map[i][j] == -1) {
                        if(!canMakeStairs_hor(i, j, true)) {
                            break;
                        }
                    }
                    //내리막
                    else if(map[i][j-1] - map[i][j] == 1) {
                        if(!canMakeStairs_hor(i, j, false)) {
                            break;
                        }
                    } else {
                        break;
                    }
                }

                if(j >= N - 1) {
                    ans++;
                }
            }
        }

        visit = new boolean[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 1; j < N; j++) {
                if(map[j-1][i] != map[j][i]) {
                    //오르막
                    if(map[j-1][i] - map[j][i] == -1) {
                        if(!canMakeStairs_ver(i, j, true)) {
                            break;
                        }
                    }
                    //내리막
                    else if(map[j-1][i] - map[j][i] == 1) {
                        if(!canMakeStairs_ver(i, j, false)) {
                            break;
                        }
                    } else {
                        break;
                    }
                }

                if(j >= N - 1) {
                    ans++;
                }
            }
        }
        return ans;
    }

    static boolean canMakeStairs_hor(int i, int j, boolean isUp) {
        if(isUp) {
            if(j - L < 0) {
                return false;
            }
            for(int z = j - 1; z >= j - L; z--) {
                if(map[i][j - 1] != map[i][z] || visit[i][z]) {
                    return false;
                }
            }

            for(int z = j - 1; z >= j - L; z--) {
                visit[i][z] = true;
            }

            return true;
        } else {
            if(j + L > N) {
                return false;
            }
            for(int z = j; z < j + L; z++) {
                if(map[i][j] != map[i][z] || visit[i][z]) {
                    return false;
                }
            }
            for(int z = j; z < j + L; z++) {
                visit[i][z] = true;
            }
            return true;
        }
    }

    static boolean canMakeStairs_ver(int i, int j, boolean isUp) {
        if(isUp) {
            if(j - L < 0) {
                return false;
            }
            for(int z = j - 1; z >= j - L; z--) {
                if(map[j-1][i] != map[z][i] || visit[z][i]) {
                    return false;
                }
            }

            for(int z = j - 1; z >= j - L; z--) {
                visit[z][i] = true;
            }

            return true;
        } else {
            if(j + L > N) {
                return false;
            }
            for(int z = j; z < j + L; z++) {
                if(map[j][i] != map[z][i] || visit[z][i]) {
                    return false;
                }
            }
            for(int z = j; z < j + L; z++) {
                visit[z][i] = true;
            }
            return true;
        }
    }
}