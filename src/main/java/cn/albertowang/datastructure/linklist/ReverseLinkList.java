package cn.albertowang.datastructure.linklist;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/1/19 21:46
 * @description 反转链表
 **/

public class ReverseLinkList {

    public static ListNode reverse(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode curr = head;
        ListNode pre = null;
        ListNode next;
        while (curr != null) {
            next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.getLinkList();
        ListNode res = ReverseLinkList.reverse(head);
        ListNode.print(res);
    }

}
