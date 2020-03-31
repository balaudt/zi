package zi.leet.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/subdomain-visit-count
public class SubdomainVisitCount {
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> counter = new HashMap<>();
        for (String cpdomain : cpdomains) {
            String[] t = cpdomain.split(" ");
            int count = Integer.parseInt(t[0]);
            String[] subdomains = t[1].split("\\.");
            int i = subdomains.length - 1;
            String subdomain = "";
            while (i >= 0) {
                subdomain = subdomain.equals("") ? subdomains[i] : subdomains[i] + "." + subdomain;
                if (counter.containsKey(subdomain)) {
                    counter.put(subdomain, counter.get(subdomain) + count);
                } else {
                    counter.put(subdomain, count);
                }
                i--;
            }
        }
        List<String> out = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : counter.entrySet()) {
            out.add(entry.getValue() + " " + entry.getKey());
        }
        return out;
    }

    public static void main(String[] args) {
        SubdomainVisitCount visitCount = new SubdomainVisitCount();
        System.out.println(visitCount.subdomainVisits(new String[]{"9001 discuss.leetcode.com"}));
        System.out.println(visitCount.subdomainVisits(new String[]{"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"}));
    }
}
