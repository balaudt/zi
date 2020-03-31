package zi.leet.easy;

//https://leetcode.com/problems/delete-columns-to-make-sorted
public class DeleteColumns {
    public int minDeletionSize(String[] A) {
        int ct = 0;
        for (int i = 0; i < A[0].length(); i++) {
            for (int j = 1; j < A.length; j++) {
                if (A[j].charAt(i) < A[j - 1].charAt(i)) {
                    ct++;
                    break;
                }
            }
        }
        return ct;
    }

    public static void main(String[] args) {
        DeleteColumns deleteColumns = new DeleteColumns();
        System.out.println(deleteColumns.minDeletionSize(new String[]{"cba", "daf", "ghi"}));
        System.out.println(deleteColumns.minDeletionSize(new String[]{"a", "b"}));
        System.out.println(deleteColumns.minDeletionSize(new String[]{"zyx", "wvu", "tsr"}));
    }
}
