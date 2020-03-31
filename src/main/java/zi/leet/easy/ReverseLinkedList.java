package zi.leet.easy;

import zi.leet.ds.ListNode;

import java.util.AbstractMap;
import java.util.Map;

//https://leetcode.com/problems/reverse-linked-list
public class ReverseLinkedList {

    private ListNode last;

    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode reverseList = recurseReverseList(head);
        head.next = null;
        return last;
    }

    private ListNode recurseReverseList(ListNode node) {
        if (node == null) return null;
        if (node.next == null) return node;
        ListNode reversedList = recurseReverseList(node.next);
        reversedList.next = node;
        return node;
    }
}
