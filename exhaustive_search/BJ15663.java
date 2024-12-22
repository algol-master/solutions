package exhaustive_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.in;
import static java.lang.System.out;

public class BJ15663 {

    static int n;
    static int m;
    static int[] arr;
    static int[] output;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        output = new int[m];
        visited = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            visited[i] = false;
        }

        for(int i = 0; i < m; i++) {
            output[i] = 0;
        }

        Arrays.sort(arr);

        permutation(0);


    }

    public static void permutation(int depth) {
        if(m == depth) {
            for(int i = 0; i < m; i++) {
                out.print(output[i] + " ");
            }
            out.println();

        } else {
            int ex = 0;
            for(int i = 0; i < n; i++) {
                if(visited[i]) {
                    continue;
                }
                if(ex != arr[i]) {
                    visited[i] = true;
                    output[depth] = arr[i];
                    ex = arr[i];
                    permutation(depth + 1);
                    visited[i] = false;
                }
            }

        }
    }

    public static void swap(int[] arr, int a, int b) {
        int tmp =  arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

}
