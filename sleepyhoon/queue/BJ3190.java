package sleepyhoon.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ3190 {

    static int n;
    // 0 은 아무것도 없음, 1은 뱀이 있는 자리, 2는 사과가 있는 자리
    static int[][] map;
    static Shift[] shifts;
    static int currentTime = 0;
    // 동남서북 (시계방향)
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};

    // 지렁이가 지나간 경로를 저장한다.
    static Queue<Point> q = new ArrayDeque<>();

    static class Snake {
        int[] head;
        int direction;

        Snake() {
            this.head = new int[]{0, 0};
            q.offer(new Point(0,0));
            this.direction = 0; // 머리가 동쪽으로 향함
        }

        public boolean move() {
            currentTime++;
            int nx = this.head[0] + dx[direction];
            int ny = this.head[1] + dy[direction];
            // map에서 벗어나거나 자기자신과 부딪히면 안된다.
            if(nx < 0 || nx >= n || ny < 0 || ny >= n || q.contains(new Point(nx,ny)))
                return false;
            // 이동한 곳에 아무것도 없음
            // 새로운 꼬리 생성 필요
            if(map[nx][ny] == 0) {
                // 기존 꼬리 제거
                q.remove();
            }
            // 사과를 먹으면 치워야함
            if(map[nx][ny] == 1) {
                map[nx][ny] = 0;
            }
            // 기본적인 이동
            this.head[0] = nx;
            this.head[1] = ny;
            // 자취를 추가함
            q.offer(new Point(nx,ny));
            return true;
        }

        public void changeDirection(String operation) {
            // 오른쪽으로 회전
            if(operation.equals("D")) {
                if(direction == 3)
                    direction = 0;
                else
                    direction++;
            }
            else {
                if(direction == 0)
                    direction = 3;
                else
                    direction--;
            }
        }
    }

    static class Shift {
        int time;
        String op;

        Shift(int time, String op) {
            this.time = time;
            this.op = op;
        }
    }

    static class Point {
        int x;
        int y;

        Point(int x,int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Point point = (Point) obj;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // map의 크기를 입력받음
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        // 사과의 개수를 입력받음
        int k = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i=0;i<k;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x-1][y-1] = 1;
        }

        // 뱀 생성
        Snake snake = new Snake();

        // 방향 전환 횟수를 입력받음
        int x = Integer.parseInt(br.readLine());
        shifts = new Shift[x];
        for(int i=0;i<x;i++){
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String op = st.nextToken();
            shifts[i] = new Shift(time,op);
        }

        int shiftIndex = 0;
        while(snake.move()) {
            if(shiftIndex < x && currentTime == shifts[shiftIndex].time) {
                snake.changeDirection(shifts[shiftIndex++].op);
            }
        }
        System.out.println(currentTime);
    }
}
