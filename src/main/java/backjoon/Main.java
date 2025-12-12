package backjoon;

import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder answer = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int d = Integer.parseInt(br.readLine());

        int gcdValue = getGCD(d, 360);

        int minCount = 360 / gcdValue;

        answer.append(minCount);

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
    public static int getGCD(int a, int b){
        if (b == 0) return a;
        return getGCD(b, a % b);
    } // func end
} // class end