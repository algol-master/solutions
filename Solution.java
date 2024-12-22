import java.util.*;
import java.io.*;

class Solution {
    static char[] members = {'A','C','F','J','M','N','R','T'};
    static boolean[] visited;
    static int answer;
    static Map<Character,Character> map = new HashMap<>();

    public static void main(String[] args) {
        int n = 2;
        String[] data = {"N~F=0", "R~T>2"};
        visited = new boolean[members.length];
        for(int i=0;i<n;i++){
            String now = data[i];
            map.put(now.charAt(0),now.charAt(2));
        }
        dfs(new char[members.length],0,data);
        System.out.println(answer);
    }
    private static void dfs(char[] tmp, int depth, String[] data) {
        if(depth == members.length) {
            int pivot = 0;
            // 조건 검사
            for(char from : map.keySet()) {
                char to = map.get(from);
                int fromIndex = findIndex(from);
                int toIndex = findIndex(to);
                if(!check(data[pivot].charAt(3), data[pivot].charAt(4) - '0',fromIndex,toIndex)) return;
                pivot++;
            }
            answer++;
        } else {
            for(int i=0;i<members.length;i++){
                if(visited[i]) continue;
                visited[i] = true;
                tmp[depth] = members[i];
                dfs(tmp,depth+1,data);
                visited[i] = false;
            }
        }
    }
    private static int findIndex(char target) {
        for(int i=0;i<members.length;i++){
            if(members[i] == target)
                return i;
        }
        return -1;
    }
    private static boolean check(char execute, int limit, int from, int to) {
        if(execute == '>')
            return Math.abs(from - to) - 1 > limit;
        else if(execute == '<')
            return Math.abs(from - to) - 1 < limit;
        else
            return Math.abs(from - to) - 1 == limit;
    }
}