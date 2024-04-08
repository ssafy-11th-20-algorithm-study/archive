import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    static int ans;
    static String[] a,b;
    static boolean[] v;
    static int bi,ui;
    static String[] regexArr;
    static int bit=0;
    static Set<Integer> set = new HashSet<>();
    
    public int solution(String[] user_id, String[] banned_id) {
        bi = banned_id.length;
        ui = user_id.length;
        regexArr = new String[bi];
        for(int i=0; i<bi; i++){
            String tmp = "";
            String str = banned_id[i];
            for(int j=0; j<str.length(); j++){
                if(str.charAt(j)=='*')
                    tmp += ".";
                else
                    tmp += str.charAt(j);
            }
            regexArr[i]=tmp;
        }
        
        a = new String[ui];
        for(int i=0; i<ui; i++){
            a[i]=user_id[i];
        }
        b = new String[bi];
        v = new boolean[ui];
        // comb(0,0);
        perm(0);
        
        return ans;
    }
    
    static void perm(int cnt){
        if(cnt == bi){
            if(isList() && !set.contains(bit)){
                ans++;
                set.add(bit);
                return;
            }
                
            return;
        }
        for(int i=0; i<ui; i++){
            if(v[i]) continue;
            b[cnt] = a[i];
            bit |= (1<<i);
            v[i] = true;
            perm(cnt+1);
            v[i] = false;
            bit &= ~(1<<i);
        }
        
    }
    
    static boolean isList(){
        for(int i=0; i<bi; i++){
            Matcher matcher = Pattern.compile(regexArr[i]).matcher(b[i]);
            if(!matcher.matches())
                return false;
        }
        return true;
    }
}
