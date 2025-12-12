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

        st = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(st.nextToken());   // A사의 단가
        int Y = Integer.parseInt(st.nextToken());   // B사의 단가
        int Z = Integer.parseInt(st.nextToken());   // C사의 단가

        st = new StringTokenizer(br.readLine());
        int U = Integer.parseInt(st.nextToken());   // A사의 공급량
        int V = Integer.parseInt(st.nextToken());   // B사의 공급량
        int W = Integer.parseInt(st.nextToken());   // C사의 공급량

        int sum = X * (U / 100) + Y * (V / 50) + Z * (W / 20);

        answer.append(sum);

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
} // class end