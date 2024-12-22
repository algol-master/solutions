package exhaustive_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.in;
import static java.lang.System.out;
public class BJ15649 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n, r;
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        boolean[] visited = new boolean[n];
        for(int i = 1; i <= n; i++) {
            arr[i - 1] = i;
            visited[i - 1] = false;
        }
        int[] output = new int[n];


        permutation(arr, output, visited,n ,r, 0);



    }

    public static void permutation(int[] arr, int[] output, boolean[] visited, int n, int r, int depth) {
        if(r == depth) {
            for(int i = 0; i < r; i++) {
                out.print(output[i] + " ");
            }
            out.println();
        } else {

            for(int i = 0; i < n; i++) {
                if(visited[i] != true) {
                    visited[i] = true;
                    output[depth] = arr[i];
                    permutation(arr, output, visited, n, r, depth + 1);
                    visited[i] = false;;
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
