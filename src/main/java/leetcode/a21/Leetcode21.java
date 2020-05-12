package leetcode.a21;

import leetcode.common.ListNode;

class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode l1List = l1;
        ListNode l2List = l2;
        ListNode prv = l1List;
        while (l1List!=null && l2List!=null){

            if (l1List.val<=l2List.val){
                ListNode newNode = new ListNode(l2List.val);
                ListNode temp = l1List.next;
                newNode.next = temp;
                l1List.next = newNode;

                l2List = l2List.next;
            }else{
                prv=l1List;
                l1List = l1List.next;
            }

        }
        //遍历完l1List 有两种情况出现，第一是l1List==null,第二是l2List==null,l2List为null，不处理，因为说明合并完成
        //l1List为null要处理，因为没完成
        if(l1List==null){
            //直接拿到尾指针链上l2List其实也可以了
            prv.next = l2List;
        }
        return l1List;
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