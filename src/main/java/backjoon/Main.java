package backjoon;

import java.awt.*;
import java.io.*;
import java.util.*;

class PointXYZ{
    int x;
    int y;
    int z;

    public PointXYZ(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    } // func end
} // class end

public class Main {
    static StringBuilder answer = new StringBuilder();
    static int[][][] tomato;
    static boolean[][][] visited;
    static Queue<PointXYZ> queue = new LinkedList<>();
    static int[] nx = {0, 0, 1, -1, 0, 0};
    static int[] ny = {1, -1, 0, 0, 0, 0};
    static int[] nz = {0, 0, 0, 0, 1, -1};
    static int N, M, H, zero, one;
    static PointXYZ lastTomato = new PointXYZ(0, 0, 0);
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());       // 가로 길이 N
        M = Integer.parseInt(st.nextToken());       // 세로 길이 M
        H = Integer.parseInt(st.nextToken());       // 상자의 수 H
        tomato = new int[H][M][N];
        visited = new boolean[H][M][N];
        zero = 0;
        one = 0;

        for (int h = 0; h < H; h++){
            for (int i = 0; i < M; i++){
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++){
                    int a = Integer.parseInt(st.nextToken());
                    tomato[h][i][j] = a;
                    if (a == 1){
                        queue.add(new PointXYZ(i, j, h));
                        visited[h][i][j] = true;
                        one++;
                    } else if (a == 0){
                        zero++;
                    } else if (a == -1){
                        one++;
                    } // if end
                } // for end
            } // for end
        } // for end

        if (one == N * M * H){
            answer.append("0");
        } else {
            while (!queue.isEmpty()){
                PointXYZ point = queue.poll();
                for (int i = 0; i < 6; i++){
                    int nextX = point.x + nx[i];
                    int nextY = point.y + ny[i];
                    int nextZ = point.z + nz[i];
                    if (nextX < 0 || nextX >= M || nextY < 0 || nextY >= N || nextZ < 0 || nextZ >= H) continue;
                    if (visited[nextZ][nextX][nextY]) continue;
                    if (tomato[nextZ][nextX][nextY] == -1) continue;
                    tomato[nextZ][nextX][nextY] = tomato[point.z][point.x][point.y] + 1;
                    queue.add(new PointXYZ(nextX, nextY, nextZ));
                    visited[nextZ][nextX][nextY] = true;
                    lastTomato = new PointXYZ(nextX, nextY, nextZ);
                    zero--;
                } // for end
            } // while end
            if (zero == 0){
                answer.append(tomato[lastTomato.z][lastTomato.x][lastTomato.y] - 1);
            } else {
                answer.append("-1");
            } // if end
        } // if end

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
} // class end