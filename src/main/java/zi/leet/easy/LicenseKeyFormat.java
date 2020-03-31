package zi.leet.easy;

//https://leetcode.com/problems/license-key-formatting
public class LicenseKeyFormat {
    public String licenseKeyFormatting(String S, int K) {
        StringBuffer result = new StringBuffer();
        char[] s = S.toCharArray();
        int ct = 0;
        for (int i = s.length - 1; i >= 0; i--) {
            if (s[i] == '-') continue;
            result.append(Character.toUpperCase(s[i]));
            ct++;
            if (ct == 4) {
                ct = 0;
                result.append('-');
            }
        }
        if (result.length() == 0) return "";
        if (result.charAt(result.length() - 1) == '-')
            result.deleteCharAt(result.length() - 1);
        return result.reverse().toString();
    }
}
