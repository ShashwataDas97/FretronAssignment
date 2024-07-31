import java.util.*;
public class Problem3KillAllAndReturnHome {
    public static void solve(int[][] chessboard, int sr, int sc, int dr, int dc, String psf, boolean[][] visited, ArrayList < String > res, char currDir){
        // Base Condition
        // Negative Base Case
        if (sr < 1 || sc < 1 || sr >= chessboard.length || sc >= chessboard[0].length) {
            return;
        }
        // Positive Base Case
        if(sr == dr && sc == dc){
            psf = psf + "Arrive (" + sc + "," + sr + ")";
            res.add(psf);
            return;
        }
        visited[sr][sc] = true;
        if(chessboard[sr][sc] == 1){ // Cell contain soldier
            // Kill
            chessboard[sr][sc] = 0;
            if(currDir == 'D'){
                solve(chessboard, sr, sc + 1, dr, dc, psf + "Kill (" + sc + "," + sr + "). Turn Left\n", visited, res, 'R');
            }else if(currDir == 'R'){
                solve(chessboard, sr - 1, sc, dr, dc, psf + "Kill (" + sc + "," + sr + "). Turn Left\n", visited, res, 'T');
            }else if(currDir == 'T'){
                solve(chessboard, sr, sc - 1, dr, dc, psf + "Kill (" + sc + "," + sr + "). Turn Left\n", visited, res, 'L');
            }else{
                solve(chessboard, sr + 1, sc, dr, dc, psf + "Kill (" + sc + "," + sr + "). Turn Left\n", visited, res, 'D');
            }
            chessboard[sr][sc] = 1;

            // Jump
            if(currDir == 'D'){
                solve(chessboard, sr + 1, sc, dr, dc, psf + "Jump (" + sc + "," + sr + ")\n", visited, res, 'D');
            }else if(currDir == 'R'){
                solve(chessboard, sr, sc + 1, dr, dc, psf + "Jump (" + sc + "," + sr + ")\n", visited, res, 'R');
            }else if(currDir == 'T'){
                solve(chessboard, sr - 1, sc, dr, dc, psf + "Jump (" + sc + "," + sr + ")\n", visited, res, 'T');
            }else{
                solve(chessboard, sr, sc - 1, dr, dc, psf + "Jump (" + sc + "," + sr + ")\n", visited, res, 'L');
            }
        }else{ // Cell does not contain soldier
            if(currDir == 'D'){
                solve(chessboard, sr + 1, sc, dr, dc, psf, visited, res, 'D');
            }else if(currDir == 'R'){
                solve(chessboard, sr, sc + 1, dr, dc, psf, visited, res, 'R');
            }else if(currDir == 'T'){
                solve(chessboard, sr - 1, sc, dr, dc, psf, visited, res, 'T');
            }else{
                solve(chessboard, sr, sc - 1, dr, dc, psf, visited, res, 'L');
            }
        }
        visited[sr][sc] = false;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ArrayList<int[]> soldiers = new ArrayList<>();
        System.out.print("Find my home castle soldiers : ");
        int numberOfSoldiers = sc.nextInt();
        for(int i=0;i<numberOfSoldiers;i++){
            System.out.print("Enter coordinates for soldier " + (i + 1) + ": ");
            int x = sc.nextInt();
            int y = sc.nextInt();
            soldiers.add(new int[]{x, y});
        }
        int[][] chessboard = new int[10][10];
        for(int i=0;i<soldiers.size();i++){
            int[] coordinate = soldiers.get(i);
            int x = coordinate[0];
            int y = coordinate[1];
            chessboard[y][x] = 1;
        }
        System.out.print("Enter the coordinates for your “special” castle: ");
        int startX = sc.nextInt();
        int startY = sc.nextInt();
        boolean[][] visited = new boolean[10][10];
        ArrayList<String> res = new ArrayList<>();
        solve(chessboard, startY + 1, startX, startY, startX, "Start: (" + startX + "," + startY + ")\n", visited, res, 'D');
        for(int i=0;i<res.size();i++){
            System.out.println("Path " + (i + 1) + "\n=======");
            System.out.println(res.get(i));
            System.out.println();
        }
    }
}
