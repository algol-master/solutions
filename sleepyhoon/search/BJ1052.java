package sleepyhoon.search;

import java.util.Scanner;

public class BJ1052 {
    static int n,k;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        k = sc.nextInt();

        System.out.println(func());
    }

    private static int func() {
        if(n<=k) return 0;

        for(int i=0;i<k-1;i++){
            int base = 0;
            while(Math.pow(2,base) < n) base++;
            n -= (int) Math.pow(2,base-1);

            if(n==0)
                return 0;
        }

        int base = 0;
        while(Math.pow(2,base) < n) base++;

        return (int) Math.pow(2,base) - n;
    }
}
