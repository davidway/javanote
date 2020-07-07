import java.util.Scanner;

public class MigongDfs {
    static int m,n,p,q,min=99999999;
    static int[][] a = new int[51][51];
    static int[][] book = new int[51][51];

    public static void main(String[] args) {
        int i,j,startx,starty;
        n = 5; //5行 4列
        m = 4;

        a= new int[][]{
                {0, 0, 1, 0},
                {0, 0, 0, 0},
                {0, 0, 1, 0},
                {0, 1, 0, 0},
                {0, 0, 0, 1},
                {1, 1, 4, 3}
        };
        startx=0;
        starty=0;
        p = 3;
        q = 2;
        book[startx][starty] = 1;
        dfs(startx,starty,0);
        System.out.println(min);
    }

    private static void dfs(int startx, int starty, int step) {
        int next[][] = new int[][]{
                {0,1},
                {1,0},
                {0,-1},
                {-1,0}
        };
        int tx,ty,k;
        if (p == startx && starty ==q ){
            if ( step<min){
                min = step;
            }
            return ;
        }

        for ( k=0 ; k<=3 ; k++){
            tx = startx+next[k][0];
            ty = starty+next[k][1];

            if ( tx<0 || tx>n-1 || ty<0 || ty>m-1){
                continue;
            }

            if ( a[tx][ty]==0 &&book[tx][ty]==0){
                book[tx][ty]=1;
                dfs(tx,ty,step+1);
                book[tx][ty] = 0;
            }
        }
    }
}
