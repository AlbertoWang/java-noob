package cn.albertowang.datastructure.linklist;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/1/20 20:51
 * @description 奇数升序偶数降序链表，合并成一个升序链表
 **/

public class SortOddAndEven {

    public static ListNode sortOddAndEven(ListNode head) {
        ListNode even = new ListNode(-2);
        ListNode odd = new ListNode(-1);
        ListNode currEven = even;
        ListNode currOdd = odd;
        while (head != null) {
            if ((head.val & 1) == 0) { // 偶数
                currEven.next = head;
                currEven = currEven.next;
            } else {
                currOdd.next = head;
                currOdd = currOdd.next;
            }
            ListNode temp = head;
            head = head.next;
            temp.next = null;
        }
        // sorted even
        even = ReverseLinkList.reverse(even.next);
        // sorted odd
        odd = odd.next;
        return merge(even, odd);
    }

    public static ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        ListNode res = l1.val < l2.val ? l1 : l2;
        ListNode curr = res;
        l1 = res == l1 ? l1.next : l1;
        l2 = res == l2 ? l2.next : l2;
        while (l1 != null && l2 != null) {
            curr.next = l1.val < l2.val ? l1 : l2;
            l1 = curr.next == l1 ? l1.next : l1;
            l2 = curr.next == l2 ? l2.next : l2;
            curr = curr.next;
        }
        curr.next = l1 == null ? l2 : l1;
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 8, 6, 4, 2};
        ListNode head = ListNode.getLinkList(arr);
        ListNode.print(head);

        ListNode.print(SortOddAndEven.sortOddAndEven(head));
    }
}
