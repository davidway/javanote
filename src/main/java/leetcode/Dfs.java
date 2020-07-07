package leetcode;

public class Dfs {
    static int n=9;
    static int a[] = new int[10];
    static int book[] = new int[10];
    public static void main(String[] args) {
        dfs(1);
    }

    public static void dfs(int step) {
        int i;
        if ( step==n){ //判断边界
            for (  i=1 ; i<=n ; i++){
                System.out.print(a[i]);
            }
            System.out.println();
        }

        for ( i=1 ; i<=n ; i++){//尝试每一种可能
            if (book[i]==0){
                a[step]=i;
                book[i]=1;
                dfs(step+1);//继续下一步
                book[i]=0;
            }
        }
    }
}
