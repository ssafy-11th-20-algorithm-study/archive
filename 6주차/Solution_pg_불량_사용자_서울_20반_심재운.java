package a0408;

import java.util.*;

class Solution3 {
    
    static HashSet<String> sub = new HashSet<>();
    static int N, M;
    static String[] bi, ui;
    static int[] b, temp;
    static boolean[] v;
    
    static void perm(int cnt) {
        if (cnt == M) {
            String str = "";
            for (int i = 0; i < M; i ++) {
                temp[i] = b[i];  
            };
            
            Arrays.sort(temp);
            
            for (int i = 0; i < M; i ++) {
                str += temp[i];  
            };
            
            sub.add(str);
            
            //System.out.println(str);
            
            return;
        }
        
        for (int i = 0; i < N; i ++) {
            if (v[i]) continue;
            if (ui[i].length() != bi[cnt].length()) continue;
            boolean flag = true;
            for (int j = 0; j < ui[i].length(); j ++) {
                if (bi[cnt].charAt(j) == '*') continue;
                if (bi[cnt].charAt(j) != ui[i].charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                b[cnt] = i;
                v[i] = true;
                perm(cnt + 1);
                v[i] = false;
            }
        }
    }
    
    public int solution(String[] user_id, String[] banned_id) {
        N = user_id.length;
        M = banned_id.length;
        ui = user_id;
        bi = banned_id;
        b = new int[M];
        v = new boolean[N];
        temp = new int[M];
        
        perm(0);
        
        return sub.size();
    }
}