package zi.chef.y16.feb;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by balaudt on 8/14/16.
 */
public class Solution1 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("/Users/balaudt/Temp/euclid/input001.txt"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Pattern pattern = Pattern.compile("(\\w*):.*\"sn\": \"(\\w*)\".*\"ss\": (-?[0-9]*).*\"si\": \"(\\w*)\".*");
        Pattern linePattern = Pattern.compile("(\\w*): .*");
        Pattern snPattern = Pattern.compile("\"sn\": \"(\\w*)\"");
        Pattern ssPattern = Pattern.compile("\"ss\": (-?[0-9]*)");
        Pattern siPattern = Pattern.compile("\"si\": \"(\\w*)\"");
        String line = null;
        Matcher matcher = null;
        String level, sn, si, ss;
        int warnCt = 0, infoCt = 0;
        Set<String> routerAddress = new HashSet<>();
        int maxSignal = Integer.MIN_VALUE, signalStrength;
        Set<String> deviceAddress = new HashSet<>();
        while ((line = reader.readLine()) != null) {
            matcher = linePattern.matcher(line);
            if (matcher.find()) {
                level = matcher.group(1);
                matcher = snPattern.matcher(line);
                matcher.find();
                sn = matcher.group(1);

                matcher = ssPattern.matcher(line);
                while (matcher.find()) {
                    ss = matcher.group(1);
                    signalStrength = Integer.parseInt(ss);
                    if (signalStrength > maxSignal)
                        maxSignal = signalStrength;
                }

                matcher = siPattern.matcher(line);
                while (matcher.find()) {
                    si = matcher.group(1);
                    deviceAddress.add(si);
                }

                if (level.equals("INFO"))
                    infoCt++;
                else if (level.equals("WARN"))
                    warnCt++;
                routerAddress.add(sn);
            }
        }
        System.out.println(infoCt);
        System.out.println(warnCt);
        System.out.println(routerAddress.size());
        System.out.println(deviceAddress.size());
        System.out.println(maxSignal);
        reader.close();
    }
}
