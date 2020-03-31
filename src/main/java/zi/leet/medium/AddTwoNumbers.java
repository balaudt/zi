package zi.leet.medium;

import zi.leet.ds.ListNode;
import zi.leet.ds.Result;

//https://leetcode.com/problems/add-two-numbers
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        Result result = new Result();
        while (l1 != null || l2 != null) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            ListNode current = new ListNode(sum % 10);
            carry = sum / 10;
            if (result.next == null) {
                result.next = current;
                result.head = current;
            } else {
                result.next.next = current;
                result.next = current;
            }
        }
        while (carry > 0) {
            ListNode current = new ListNode(carry % 10);
            result.next.next = current;
            result.next = current;
            carry /= 10;
        }
        return result.head;
    }
}
