package io;
   class ListNode {
    int val;
   ListNode next;
   ListNode(int x) { val = x; }
   static ListNode arrayToList(int[] array){
       ListNode head = new ListNode(0);
       ListNode temp = head;
       for (int i = 0; i < array.length; i++) {
           ListNode newNode = new ListNode(0);
           newNode.val= array[i];
           temp.next =newNode;
           temp = newNode;
       }
       return head.next;
   }
 }
public class LeetcodeTwoSum {
    public static void main(String[] args) {
            int[] array = new int[]{9,9,9};
        int[] array2 = new int[]{1};
        ListNode ls = ListNode.arrayToList(array);
        ListNode ls2 = ListNode.arrayToList(array2);

        LeetcodeTwoSum solution = new LeetcodeTwoSum();
        solution.printList(ls);
        solution.printList(ls2);
        solution.addTwoNumbers(ls,ls2);

    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int l1Length = getLength(l1);
        int l2Length = getLength(l2);
        ListNode result = new ListNode(0);
        ListNode longList = l1;
        ListNode shortList = l2;

        int cha = l1Length -l2Length;
        if ( l1Length<l2Length){
            longList = l2;
            shortList =l1;
            cha = l2Length-l1Length;
        }
        ListNode temp = result;
        result = plusNode(longList,shortList,cha);
        printList(result);

        return result.next;
    }


    private void printList(ListNode result) {
        boolean first=true;
        while ( result!=null){
            if ( first) {
                System.out.print(result.val);
            }else{
                System.out.print(" "+result.val);

            }
            result = result.next;
        }
        System.out.println();
    }


    private ListNode plusNode(ListNode longList, ListNode shortList,int cha) {
        ListNode result = new ListNode(0);
        ListNode temp = result;
        int c=0;
        int sum=0;
        while ( cha>=0 && longList!=null && shortList!=null){
            ListNode newNode = new ListNode(0);
            sum = longList.val+shortList.val+c;

            c = sum/10;
            if (sum>=10){
                sum =sum%10;
            }
            newNode.val = newNode.val+sum;

            temp.next = newNode;
            temp = newNode;

            longList = longList.next;
            shortList = shortList.next;
        }
        //低位处理完了，要处理高位
        while ( longList!=null){
            ListNode newNode = new ListNode(0);
            sum = longList.val+c;
            c = sum/10;
            if (sum>=10){
                sum =sum%10;
            }

            newNode.val = newNode.val+sum;

            temp.next = newNode;
            temp = newNode;
            longList = longList.next;
        }

        if (c!=0){
            ListNode newNode = new ListNode(c);
            temp.next = newNode;
            temp = newNode;
        }

        return result.next;
    }

    private int getLength(ListNode l1) {
        int length=0;
        while (l1!=null){
            length++;
            l1=l1.next;
        }
        return length;
    }
}