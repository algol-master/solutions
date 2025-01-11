package sleepyhoon.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14499 {
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static class Dice {
        int up;
        int right;
        int down;
        int left;
        int ceil;
        int floor;

        int x;
        int y;
        Dice(int up,int right,int down,int left,int ceil,int floor,int x,int y) {
            this.up = up;
            this.down = down;
            this.left = left;
            this.right = right;
            this.ceil = ceil;
            this.floor = floor;
            this.x = x;
            this.y = y;
        }
    }
    static int n,m,x,y,k;
    static int[][] arr;
    static int[] commands;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        commands = new int[k];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<k;i++){
            commands[i] = Integer.parseInt(st.nextToken());
        }

        Dice dice = new Dice(0,0,0,0,0,0,x,y);
        play(dice,0);
        System.out.println(sb);
    }

    private static Dice movingDice(int command,Dice dice) {
        if(command==0)
            return new Dice(dice.up,dice.ceil,dice.down,dice.floor,dice.left,dice.right,dice.x,dice.y+1);
        if(command==1)
            return new Dice(dice.up,dice.floor,dice.down,dice.ceil,dice.right,dice.left,dice.x,dice.y-1);
        if(command==2)
            return new Dice(dice.ceil,dice.right,dice.floor,dice.left,dice.down,dice.up,dice.x-1,dice.y);
        if(command==3)
            return new Dice(dice.floor,dice.right,dice.ceil,dice.left,dice.up,dice.down,dice.x+1,dice.y);
        return null;
    }

    private static void play(Dice dice, int index) {
        if(index == k) return;

        int command = commands[index] - 1;
        int nx = dice.x + dx[command];
        int ny = dice.y + dy[command];

        if(nx<0||nx>=n||ny<0||ny>=m) {
            play(dice,index+1);
        }
        else {
            Dice newDice = movingDice(command,dice);

            if(arr[nx][ny] == 0) {
                arr[nx][ny] = newDice.floor;
            }
            else {
                newDice.floor = arr[nx][ny];
                arr[nx][ny] = 0;
            }
            sb.append(newDice.ceil).append("\n");

            play(newDice,index+1);
        }

    }
}
