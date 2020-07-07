package leetcode.a21;

import leetcode.common.ListNode;

class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode l1List = l1;
        ListNode l2List = l2;

        ListNode prv = l1List;
        ListNode next = l1List.next;
        if ( prv==null){
            return null;
        }
        if (next==null){
            //直接拿到尾指针链上l2List其实也可以了
            prv.next = l2List;
        }else{
            while (l1List!=null && l2List!=null){

                if (l1List.val<=l2List.val){
                    prv = l1List;
                    next = l1List.next;
                    l1List = l1List.next;

                }else{
                   //说明前一个节点的下一个节点应该是l2List.
                    ListNode temp = prv.next;
                    //复制l2List的当前节点
                    ListNode newNode = new ListNode(l2List.val);
                    newNode.next = temp;
                    prv.next = newNode;

                    //l2要指向下一位
                    l2List = l2List.next;
                }

            }


        }
        if(l1List==null){
            //直接拿到尾指针链上l2List其实也可以了
            prv.next = l2List;
        }
        return l1;
        //遍历完l1List 有两种情况出现，第一是l1List==null,第二是l2List==null,l2List为null，不处理，因为说明合并完成
        //l1List为null要处理，因为没完成

    }

    public static void main(String[] args) {
        int[] array={1,2,4};
        ListNode list1 = ListNode.arrayToList(array);
        int[] array2={1,3,4};
        ListNode list2 = ListNode.arrayToList(array2);
        Solution solution = new Solution();
        ListNode list3 = solution.mergeTwoLists(list1,list2);
        ListNode.printList(list3);
    }
}