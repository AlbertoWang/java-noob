package cn.albertowang.datastructure.linklist;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/1/19 22:04
 * @description 链表中每隔k个元素反转
 **/

public class ReverseKGroup {

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null)
            return null;
        if (k < 2)
            return head;
        // virtual head
        ListNode preHead = new ListNode(-1);
        preHead.next = head;
        // last first
        ListNode lastOfPre = preHead;
        ListNode lastOfThis = lastOfPre;
        while (lastOfThis != null) {
            for (int i = 0; i < k && lastOfThis != null; i++)
                lastOfThis = lastOfThis.next;
            if (lastOfThis == null)
                break;
            // head last
            ListNode headOfThis = lastOfPre.next;
            ListNode headOfLast = lastOfThis.next;
            // split
            lastOfThis.next = null;
            // reverse
            lastOfPre.next = ReverseLinkList.reverse(headOfThis);
            headOfThis.next = headOfLast;
            // update
            lastOfPre = headOfThis;
            lastOfThis = lastOfPre;
        }
        return preHead.next;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.getLinkList();
        ListNode res = ReverseKGroup.reverseKGroup(head, 3);
        ListNode.print(res);
    }
}
