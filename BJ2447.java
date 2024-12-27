import java.util.Scanner;

public class BJ2447 {
    static String[][] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int n = sc.nextInt();
        arr = new String[n][n];

        func(0,0,n);

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if (arr[i][j] == null) {
                    sb.append(" ");
                } else {
                    sb.append(arr[i][j]);
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void func(int x,int y, int n) {
        if(n==3) {
            for(int i=x;i<x+n;i++){
                for(int j=y;j<y+n;j++){
                    if(i==x+1&&j==y+1)
                        arr[i][j] = " ";
                    else
                        arr[i][j] = "*";
                }
            }
        }
        else {
            int newSize = n/3;
            func(x,y,newSize);
            func(x,y+newSize,newSize);
            func(x,y+2*newSize,newSize);
            func(x+newSize,y,newSize);
            func(x+2*newSize,y,newSize);
            func(x+newSize,y+2*newSize,newSize);
            func(x+2*newSize,y+newSize,newSize);
            func(x+2*newSize,y+2*newSize,newSize);
        }
    }
}
