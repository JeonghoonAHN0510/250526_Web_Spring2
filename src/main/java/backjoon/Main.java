package backjoon;

import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder answer = new StringBuilder();
    static List<Integer> result = new ArrayList<>();
    static boolean[][] visited;
    static int[][] apartments;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int N, count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());    // 지도 크기 N
        visited = new boolean[N][N];
        apartments = new int[N][N];
        count = 1;

        for (int i = 0; i < N; i++){
            String str = br.readLine();
            for (int j = 0; j < N; j++){
                apartments[i][j] = str.charAt(j) - '0';
            } // for end
        } // for end

        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (apartments[i][j] == 1 && !visited[i][j]){
                    dfs(i, j);
                    result.add(count);
                    count = 1;
                } // if end
            } // for end
        } // for end

        Collections.sort(result);

        answer.append(result.size()).append("\n");

        for (int i : result){
            answer.append(i).append("\n");
        } // for end

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
    public static void dfs(int x, int y){
        visited[x][y] = true;
        for(int i = 0; i < 4; i++){
            int a = dx[i] + x;
            int b = dy[i] + y;

            if( a >= 0 && b >= 0 && a < N && b < N && !visited[a][b] && apartments[a][b] == 1){
                count++;
                dfs(a, b);
            } // if end
        } // for end
    } // func end
} // class end