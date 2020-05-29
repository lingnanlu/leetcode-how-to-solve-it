package easy;

public class Intersection_of_Two_Linked_Lists {

    // 一种方式是标记法, 从headA开始, 遍历并标记, 然后再从headB开始, 遇到标记过的, 就是intersection
    // 但这种方式需要0(n)的空间


    // 最大的障碍是, interectionNode之前的长度不一样, 只要长度一样就好说了.
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        int lengthA = length(headA);
        int lengthB = length(headB);

        if(lengthA > lengthB) {

            int walkStep = lengthA - lengthB;

            ListNode aStartNode = headA;
            while(walkStep != 0) {
                aStartNode = aStartNode.next;
                walkStep--;
            }

            //此时startNode和headB的长度一样了
            return getIntersectionNodeFromSameLengthList(aStartNode, headB);
        } else if (lengthA < lengthB) {


            int walkStep = lengthB - lengthA;

            ListNode bStartNode = headB;
            while(walkStep != 0) {
                bStartNode = bStartNode.next;
                walkStep--;
            }

            return getIntersectionNodeFromSameLengthList(headA, bStartNode);
        } else {            //等于
            return getIntersectionNodeFromSameLengthList(headA, headB);
        }
    }

    private ListNode getIntersectionNodeFromSameLengthList(ListNode headA, ListNode headB) {

        ListNode pA = headA;
        ListNode pB = headB;

        while(pA != null) {
            if(pA == pB) {
                return pA;
            } else {
                pA = pA.next;
                pB = pB.next;
            }
        }

        return null;
    }

    private int length(ListNode list) {
        int length = 0;         //保留的是头结点到list(不包括list)之间的结点个数

        while(list != null) {
            list = list.next;
            length++;
        }

        return length;
    }
}
