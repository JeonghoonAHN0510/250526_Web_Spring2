package backjoon;

import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder answer = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        String str1 = br.readLine();
        String str2 = br.readLine();
        int length1 = str1.length();
        int length2 = str2.length();
        int[][] LCS = new int[length1 + 1][length2 + 1];
        for (int i = 1; i <= length1; i++){
            for (int j = 1; j <= length2; j++){
                if (str1.charAt(i - 1) == str2.charAt(j - 1)){
                    LCS[i][j] = LCS[i - 1][j - 1] + 1;
                } else {
                    LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
                } // if end
            } // for end
        } // for end

        answer.append(LCS[length1][length2]);

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
} // class end