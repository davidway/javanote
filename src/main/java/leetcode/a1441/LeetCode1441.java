package leetcode.a1441;

  class ListNode {
     int val;
    ListNode next;
     ListNode(int x) {
         val = x;
        next = null;
     }
     static ListNode arrayToList(int[] array){
         ListNode head = new ListNode(0);
         ListNode temp = head;
         for (int i = 0; i < array.length; i++) {
             ListNode newNode = new ListNode(array[i]);
             temp.next = newNode;
             temp = newNode;
         }
         return head;
     }
     public static void printList(ListNode list){
         boolean first =true;
         while ( list!=null){
             if ( first){
                 System.out.print(list.val);
                 first=false;
             }else{
                 System.out.print(" "+list.val);
             }
             list = list.next;

         }
     }
 }
public class LeetCode1441 {
    public static void main(String[] args) {
            int[] array = {1,4,2,2};
            ListNode hasCycle = ListNode.arrayToList(array);
        LeetCode1441 leetCode1441 = new LeetCode1441();
        System.out.println(leetCode1441.hasCycle(hasCycle)==true);
        int[] array2 = {1,4,2,4};
        ListNode hasCycle2 = ListNode.arrayToList(array2);
        System.out.println(leetCode1441.hasCycle(hasCycle2)==true);
        int[] array3 = {1,1};
        ListNode hasCycle3 = ListNode.arrayToList(array3);
        System.out.println(leetCode1441.hasCycle(hasCycle3)==true);
        int[] array4 = {1,4,4};
        ListNode hasCycle4 = ListNode.arrayToList(array4);
        System.out.println(leetCode1441.hasCycle(hasCycle4)==true);

    }
    public  boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        while ( slow!=fast){
            if ( fast.next==null || fast==null){
                return false;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return false;
    }
}
