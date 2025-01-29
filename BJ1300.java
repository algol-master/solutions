import java.util.Scanner;

public class BJ1300 {
    static long n, k;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        k = sc.nextInt();

        long left = 1;
        long right = n * n; // lower bound

        while (left < right) {
            long mid = (left + right) / 2;

            if (countSmaller(mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        System.out.println(left);
    }

    private static boolean countSmaller(long mid) {
        long count = 1;

        for (long i = 1; i <= n; i++) {
            count += Math.min((mid / i),n);
        }
        return count <= k;
    }
}
