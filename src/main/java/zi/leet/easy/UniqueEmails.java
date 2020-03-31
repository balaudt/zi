package zi.leet.easy;

import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/unique-email-addresses
public class UniqueEmails {
    public int numUniqueEmails(String[] emails) {
        Set<String> uniqueEmails = new HashSet<>();
        for (String email : emails) {
            String[] localDomain = email.split("@");
            String local = localDomain[0];
            int plusInd = local.indexOf('+');
            if (plusInd != -1)
                local = local.substring(0, plusInd);
            local = local.replaceAll("\\.", "");
            uniqueEmails.add(local + "@" + localDomain[1]);
        }
        return uniqueEmails.size();
    }

    public static void main(String[] args) {
        UniqueEmails uniqueEmails = new UniqueEmails();
        System.out.println(uniqueEmails.numUniqueEmails(new String[]{"test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com", "testemail+david@lee.tcode.com"}));
    }
}
