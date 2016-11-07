package zi.chef.y16.feb;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by balaudt on 8/22/16.
 */
public class VM2 {
    public static void main(String[] args) {
        printDomains(new String[]{"content=\"name=Cricket;action-uri=http://www.rediff.com/cricket?pin=ie9;icon-uri=http://www.rediff.com/favicon.ico\"/>\n"});
    }

    public static final Pattern PATTERN = Pattern.compile("http://([^\\s/]*)");

    static void printDomains(String[] arr) {
        Set<String> domains = new HashSet<>();
        List<String> generalPrefixes = Arrays.asList(new String[]{"www.", "ww2.", "web."});
        for (String str : arr) {
            Matcher matcher = PATTERN.matcher(str);
            while (matcher.find()) {
                String domain = matcher.group(1);
                if (domain.length() < 4)
                    domains.add(domain);
                else if (generalPrefixes.contains(domain.substring(0, 4)))
                    domains.add(domain.substring(4));
            }
        }
        StringBuilder builder = new StringBuilder();
        for (String domain : domains) {
            builder.append(domain).append(';');
        }
        builder.deleteCharAt(builder.length() - 1);
        System.out.println(builder.toString());
    }
}
