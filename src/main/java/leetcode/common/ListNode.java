package leetcode.common;

public class ListNode {

   public  int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
        next = null;
    }

    public static ListNode arrayToList(int[] array) {
        ListNode head = new ListNode(0);
        ListNode temp = head;
        for (int i = 0; i < array.length; i++) {
            ListNode newNode = new ListNode(array[i]);
            temp.next = newNode;
            temp = newNode;
        }
        return head.next;
    }

    public static void printList(ListNode list) {
        boolean first = true;
        while (list != null) {
            if (first) {
                System.out.print(list.val);
                first = false;
            } else {
                System.out.print(" " + list.val);
            }
            list = list.next;

        }
    }
}
