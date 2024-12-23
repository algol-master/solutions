package sleepyhoon.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.BitSet;

public class BJ13701 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] bitset = new int[1 << 20 + 1];
        String[] input = br.readLine().split(" ");
        StringBuilder sb = new StringBuilder();

        for(String s : input) {
            int n = Integer.parseInt(s);
            int idx = n / 32;
            int rest = n % 32;
            int bit = 1 << rest;
            if((bitset[idx] & bit) == 0) {
                bitset[idx] |= bit;
                sb.append(n).append(" ");
            }
        }
        System.out.println(sb);
    }
}
