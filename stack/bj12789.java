import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[num];
        for(int i = 0; i < num; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Deque<Integer> stack = new ArrayDeque<>();
        int in = 1;
        int index = 0;

        while (index < num) {
            if (arr[index] == in) {
                in++;
                index++;
            } else if (!stack.isEmpty() && stack.peek() == in) {
                stack.pop();
                in++;
            } else {
                stack.push(arr[index]);
                index++;
            }
        }

        while (!stack.isEmpty() && stack.peek() == in) {
            stack.pop();
            in++;
        }

        if (in == num + 1) {
            System.out.println("Nice");
        } else {
            System.out.println("Sad");
        }
    }
}
