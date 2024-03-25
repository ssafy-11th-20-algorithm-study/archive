package samsung;

import java.io.*;
import java.util.*;

public class Solution_모의_2383_점심식사시간_손홍서 {
    static int N, M;
    static Stair[] stairs;

    static boolean[] ch;
    static ArrayList<int[]> people;
    static int ans;
    static class Stair {
        int r, c, len, cnt; // 계단 위치, 계단 길이, 올라가 있는 사람
        Stair(int r, int c, int len, int cnt) {
            this.r = r;
            this.c = c;
            this.len = len;
            this.cnt = cnt;
        }
    }

    static class Point {
        int dir, n, len;
        public Point(int dir, int n, int len) {
            this.dir = dir;
            this.n = n;
            this.len = len;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            N = Integer.parseInt(br.readLine());

            stairs = new Stair[2];
            int temp;
            int stairIndex = 0;
            M = 0;
            people = new ArrayList<>();
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    temp = Integer.parseInt(st.nextToken());
                    if(temp > 1) {
                        stairs[stairIndex++] = new Stair(i, j, temp, 0);
                    } else if(temp == 1) {
                        people.add(new int[] {i, j});
                    }
                }
            }

            M = people.size();
            ch = new boolean[M];

            ans = Integer.MAX_VALUE;
            subsets(0);
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    static void subsets(int cnt) {
        if(cnt == M) {
            ans = Math.min(ans, getTotalTime());
            return;
        }

        ch[cnt] = true;
        subsets(cnt + 1);
        ch[cnt] = false;
        subsets(cnt + 1);
    }

    static int getTotalTime() {
        LinkedList<Point> q = new LinkedList<>();

        for(int i = 0; i < M; i++) {
            //ch가 true면 0번 계단 선택으로 판단
            int curr[] = people.get(i);
            if(ch[i]) {
                int dir = getDist(curr[0], curr[1], stairs[0].r, stairs[0].c);
                q.offer(new Point(dir + 1, 0, stairs[0].len)); //계단 올라가도 한 칸 더 기다려야 되니깐!
            } else {
                int dir = getDist(curr[0], curr[1], stairs[1].r, stairs[1].c);
                q.offer(new Point(dir + 1, 1, stairs[1].len));
            }
        }

        int time = 0;
        int n = M;
        while(!q.isEmpty()) {
            time++;
            int a = 0;
            int b = 0;
            //계단에 있는 애들 처리
            for(int i = 0; i < n; i++) {
                Point p = q.get(i);
                if(p.dir == 0) { //계단 도착
                    if(p.len == stairs[p.n].len) { // 계단 만석
                        continue;
                    }
                    if(0 < p.len  && p.len < stairs[p.n].len) { //p가 계단 내려가는중
                        p.len -= 1;
                    }
                    if(p.len == 0) { //이번턴에 계단 끝 도착 나가기
                        if(p.n == 0) {
                            a++;
                        } else {
                            b++;
                        }
                        q.remove(p);
                        i--;
                        n--;
                    }
                }
            }

            for(int i = 0; i < n; i++) {
                Point p = q.get(i);
                if(p.dir > 0) {//아직 계단 못도착 이동
                    p.dir -= 1;
                }
                //아까 계단 도착한 애들 중 들어갈 수 있으면 들어가기
                else if(p.dir == 0 && p.len == stairs[p.n].len && stairs[p.n].cnt < 3) {
                    stairs[p.n].cnt += 1;
                    p.len -= 1;
                }
            }
            stairs[0].cnt -= a;
            stairs[1].cnt -= b;
        }


        return time;
    }

    static int getDist(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }
}

