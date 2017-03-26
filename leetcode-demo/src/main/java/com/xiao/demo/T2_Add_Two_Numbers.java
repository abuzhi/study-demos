package com.xiao.demo;

/**
 * Created by xiaoliang on 2017/3/22.
 * <p>
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * <p>
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
public class T2_Add_Two_Numbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int dist = 0;
        ListNode rusult = new ListNode(dist);
        ListNode head = rusult;

        while (l1 != null || l2 != null) {

            int val1 = l1 == null? 0: l1.val;
            int val2 = l2 == null? 0: l2.val;

            int tmp = (rusult.val + val1 + val2) % 10;
            dist = (rusult.val + val1 + val2) / 10;

            rusult.val = tmp;

            l1 = l1 == null? null: l1.next;
            l2 = l2 == null? null: l2.next;
            if((l1==null)&&(l2==null)){break;}
            rusult.next = new ListNode(dist);
            rusult = rusult.next;
        }
        if(dist>0){
            rusult.next = new ListNode(1);
        }

        return head;
    }


    public static void main(String[] args) {
        T2_Add_Two_Numbers t2 = new T2_Add_Two_Numbers();
        ListNode n1 = new ListNode(1);
//        n1.next = new ListNode(8);
//        n1.next.next = new ListNode(3);

        ListNode n2 = new ListNode(9);
        n2.next = new ListNode(9);
//        n2.next.next = new ListNode(2);

        ListNode result = t2.addTwoNumbers(n1, n2);
        System.out.print("ok");
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}


