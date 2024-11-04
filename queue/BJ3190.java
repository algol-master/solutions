import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;
import static java.lang.System.out;

/**
 * <pre>
 * 문제 : 백준 3190
 * 링크 : https://www.acmicpc.net/problem/3190
 * 난이도 : 골드 4
 * 알고리즘 자체 난이도보다, 구현이 더 복잡했던 문제. snake의 상태를 deque를 이용해서 관리해야 겠다는 사실
 * 바로 알 수 있지만, 여러 조건들에 부합하게끔 구현하는게 좀 많다.
 * 처음에는 map을 따로 구성하지 않고, size와 현재 좌표 및 deque가 arrayDeque 기반으로 이루어져 있으니 containe를 사용해
 * 겹치는지 여부를 확인하려 했으나, contains 자체가 사실 배열의 특징이지, deque의 특징은 아니기 때문에 좀 더 근본에
 * 가까운 풀이 방법을 새로 짜 봤다.
 *
 * 기본적인 아이디어는 각 map의 칸에 대해, 그 상태를 0,1,2 로 나누어 구분하고, 매 시간이 루프를 지나며 증가할 때 마다
 * head의 다음이 만나는 칸의 상태를 체크하여 그 이후의 동작을 수행하도록 하였다.
 *
 * 가령 0을 만나면, 덱에서 앞에 더하고, 뒤에 빼고, map도 새로운 머리를 1로, 꼬리였던 부분을 0으로 바꿔주고, 1을 만나면 종료,
 * 2를 만나면 꼬리에 대한 업데이트는 하지 않고 머리만 업데이트를 하는 방식으로 하였다.
 *
 * 방향에 관한 것은, 그냥 map을 이용햇는데, 데이터셋의 최대 크기가 그렇게 크지 않기에 해시맵을 사용하는데 있어서 부담이 되지
 * 않았고, time을 key 로 하게끔 구현했다. 사실 이도 시간에 맞게끔 주어졌기에 queue를 사용해서 queue.peek를 사용해도 된다고 본다.
 *
 * 방향 자체는 오른쪽/왼쪽/위/아래를 미리 정의해 놓은 다음, 인덱스를 통해 방향을 관리했다. 0~3에 대해 방향을 바꿀 때마다 순환 되도록
 * 설정하였고 , 현재 시간에 대해 map에 존재하게 되면, 현재의 방향에 대한 플레그를 업데이트하도록 하였다.
 *
 * 알고리즘 자체는 크게 어려운게 없었고, 행/열 구분에서 처음에 사과를 넣을 때 x,y좌표를 거꾸로 넣어서 조금 시간이 소모되긴
 * 했다.
 * </pre>
 */
public class BJ3190 {


    private static int size;
    private static int appleN;

    private static int directionN;

    private static Map<Integer, String> round = new HashMap<>();
    private static int time = 0;

    private static int[][] map;

    private static int[] dx = {1,0,-1,0};
    private static int[] dy = {0,1,0,-1};
    private static int direction = 0;

    private static Deque<int[]> snake = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;



        size = Integer.parseInt(br.readLine());
        map = new int[size + 2][size + 2];
        initMap();

        appleN = Integer.parseInt(br.readLine());
        for(int i = 0; i < appleN; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            map[y][x] = 2;
        }

        directionN = Integer.parseInt(br.readLine());
        for(int i = 0; i < directionN; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            String d = st.nextToken();
            round.put(t, d);
        }

        snake.addFirst(new int[]{1, 1});
        map[1][1] = 1;
        while(true) {

            time++;
            int[] head = snake.peekFirst();
            int newHeadX = head[0] + dx[direction];
            int newHeadY = head[1] + dy[direction];


            if(round.containsKey(time)) {
                if(Objects.equals(round.get(time), "D")) {
                    turnRight();
                } else {
                    turnLeft();
                }
            }

            int val = map[newHeadX][newHeadY];
            if(val == 0) {
                snake.addFirst(new int[]{newHeadX, newHeadY});
                map[newHeadX][newHeadY] = 1;
                int[] tail = snake.removeLast();
                map[tail[0]][tail[1]] = 0;
                continue;
            }
            if(val == 1) {
                out.println(time);
                break;
            } else if(val == 2) {
                snake.addFirst(new int[]{newHeadX, newHeadY});
                map[newHeadX][newHeadY] = 1;
                continue;
            }

        }

    }

    private static void initMap() {
        for(int i = 0; i < size + 2; i++) {
            map[0][i] = 1;
            map[size + 1][i] = 1;
            map[i][0] = 1;
            map[i][size + 1] = 1;
        }
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                map[i][j] = 0;
            }
        }
    }

    private static void turnLeft() {
        direction = (direction + 3) % 4;
    }

    private static void turnRight() {
        direction = (direction + 1) % 4;
    }




}
