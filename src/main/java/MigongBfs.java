import java.util.Scanner;

public class MigongBfs {
    static class Node{
        int x;
        int y;
        int f;
        int s;
    }

    public static void main(String[] args) {
        Node[] queue = new Node[2501];
        for ( int i=0 ; i<2501 ; i++){
            queue[i] = new Node();
        }
        int a[][] = new int[51][51];
        int book[][] = new int[51][51];
        int[][] next = {
                {0,1},
                {1,0},
                {0,-1},
                {-1,0}
        };
        int head,tail;
        int i,j,n,m,startx,starty,p,q,tx,ty,flag;
        a= new int[][]{
                {0, 0, 1, 0},
                {0, 0, 0, 0},
                {0, 0, 1, 0},
                {0, 1, 0, 0},
                {0, 0, 0, 1},
                {1, 1, 4, 3}
        };
        n = 5;
        m = 4;
        startx = 0;
        starty = 0;
        p= 3;
        q = 2;

        head=1;
        tail = 1;
        queue[tail].x = startx;
        queue[tail].y = starty;
        queue[tail].f = 0;
        queue[tail].s = 0;
        tail++;
        book[startx][starty] = 1;

        flag = 0;
        while ( head<tail){
            for (int k=0 ; k<=3 ; k++){
                tx = queue[head].x + next[k][0];
                ty = queue[head].y + next[k][1];
                if ( tx<0 || tx>n-1 || ty<1 ||ty>m-1){
                    continue;
                }

                if ( a[tx][ty]==0 && book[tx][ty]==0){
                    book[tx][ty] = 1;
                    queue[tail].x = tx;
                    queue[tail].y = ty;
                    queue[tail].f = head;
                    queue[tail].s = queue[head].s+1;
                }

                if ( tx==p && ty==q){
                    flag = 1;
                    break;
                }
            }

            if ( flag==1){
                break;
            }
            head++;
        }
        System.out.println("queue[tail-1].s = " + queue[tail-1].s);
    }
}
