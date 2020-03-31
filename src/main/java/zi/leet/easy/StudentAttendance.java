package zi.leet.easy;

//https://leetcode.com/problems/student-attendance-record-i
public class StudentAttendance {
    public boolean checkRecord(String s) {
        char[] c = s.toCharArray();
        boolean wasAbsent = false;
        int lateCount = 0;
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 'A')
                if (wasAbsent)
                    return false;
                else {
                    wasAbsent = true;
                    lateCount = 0;
                }
            else if (c[i] == 'L')
                if (lateCount == 2)
                    return false;
                else
                    lateCount++;
            else
                lateCount = 0;
        }
        return true;
    }
}
