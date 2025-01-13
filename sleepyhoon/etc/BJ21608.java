package sleepyhoon.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ21608 {
    static Map<Integer, Set<Integer>> map = new HashMap<>();
    static List<Integer> orders = new ArrayList<>();
    static int[][] arr;
    static int n;
    static class Cluster {
        int x;
        int y;
        int emptys;
        int nears;

        Cluster(int x,int y, int emptys, int nears) {
            this.x = x;
            this.y = y;
            this.emptys = emptys;
            this.nears = nears;
        }

        @Override
        public String toString() {
            return x + " " + y + " " + emptys + " " + nears;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        for(int i=0;i<n*n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int main = Integer.parseInt(st.nextToken());
            Set<Integer> friends = new HashSet<>();
            friends.add(Integer.parseInt(st.nextToken()));
            friends.add(Integer.parseInt(st.nextToken()));
            friends.add(Integer.parseInt(st.nextToken()));
            friends.add(Integer.parseInt(st.nextToken()));
            map.put(main,friends);
            orders.add(main);
        }

        for(int i=0;i<orders.size();i++){
            Integer current = orders.get(i);
            Set<Integer> friends = map.get(current);
            sit(current,friends);
        }

        int sum = 0;

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++) {
                int tmp = 0;
                Set<Integer> friends = map.get(arr[i][j]);
                int[] ints = countFriend(i, j, friends);
                if(ints[1] == 0) tmp+=0;
                else if(ints[1] == 1) tmp+=1;
                else if(ints[1] == 2) tmp+=10;
                else if(ints[1] == 3) tmp+=100;
                else if(ints[1] == 4) tmp+=1000;
                sum+=tmp;
            }
        }
        System.out.println(sum);
    }

    private static void sit(int man, Set<Integer> friends) {
        List<Cluster> list = new ArrayList<>();
        // 일단 인접한 빈자리, 친구 모두 세기
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(arr[i][j]==0) {
                    // [빈 자리 개수, 인접합 친구 명수]
                    int[] counts = countFriend(i,j,friends);
                    list.add(new Cluster(i,j,counts[0],counts[1]));
                }
            }
        }

        Collections.sort(list,(o1,o2)->{
            if(o1.nears == o2.nears) {
                if(o1.emptys == o2.emptys) {
                    if(o1.x == o2.x) {
                        return o1.y - o2.y;
                    }
                    return o1.x - o2.x;
                }
                return o2.emptys - o1.emptys;
            }
            return o2.nears - o1.nears;
        });

        if(list.size()==1) {
            Cluster cluster = list.get(0);
            arr[cluster.x][cluster.y] = man;
            return;
        }

        if(list.get(0).nears != list.get(1).nears) {
            Cluster cluster = list.get(0);
            arr[cluster.x][cluster.y] = man;
            return;
        }

        List<Cluster> second = new ArrayList<>();
        int maxNears = list.get(0).nears;
        for (Cluster cluster : list) {
            if(cluster.nears != maxNears) break;
            second.add(cluster);
        }

        Cluster cluster = second.get(0);
        arr[cluster.x][cluster.y] = man;
    }

    private static int[] countFriend(int x,int y,Set<Integer> friends) {
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};
        int[] answer = new int[2];
        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx<0||nx>=n||ny<0||ny>=n) continue;
            if(arr[nx][ny] == 0)
                answer[0]++;
            else if(friends.contains(arr[nx][ny]))
                answer[1]++;
        }
        return answer;
    }
}
