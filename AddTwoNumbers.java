package com.bhar.leetCodePractice;

/**
 * Definition for singly-linked list.
 * */
class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

public class AddTwoNumbers {
    public static void main(String arg[]) {

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode nodeBeforeHead = new ListNode(-1);
        ListNode current = nodeBeforeHead;

        int carry = 0;
        ListNode ptr1 = l1;
        ListNode ptr2 = l2;
        int digitSum = 0;
        while (ptr1 != null || ptr2 != null || carry != 0) {
            digitSum = carry;
            if (ptr1 != null && ptr2 !=null) {
                digitSum += ptr1.val + ptr2.val;
                ptr1 = ptr1.next;
                ptr2 = ptr2.next;
            } else if (ptr1 != null) {
                digitSum += ptr1.val;
                ptr1 = ptr1.next;
            } else if (ptr2 != null) {
                digitSum += ptr2.val;
                ptr2 = ptr2.next;
            }
            ListNode newNode = new ListNode(digitSum % 10);
            carry = digitSum / 10;
            current.next = newNode;
            current = current.next;
        }


        return nodeBeforeHead.next;
    }
}


