package zi.leet.easy;

import zi.leet.ds.ListNode;
import zi.leet.ds.Result;

//https://leetcode.com/problems/merge-two-sorted-lists
public class MergeSortedLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        Result result = new Result();
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                updateResult(l1, result);
                l1 = l1.next;
            } else {
                updateResult(l2, result);
                l2 = l2.next;
            }
        }
        while (l1 != null) {
            updateResult(l1, result);
            l1 = l1.next;
        }
        while (l2 != null) {
            updateResult(l2, result);
            l2 = l2.next;
        }
        return result.head;
    }

    private void updateResult(ListNode l, Result result) {
        if (result.next == null) {
            result.next = l;
            result.head = l;
        } else {
            result.next.next = l;
            result.next = l;
        }
    }

}
