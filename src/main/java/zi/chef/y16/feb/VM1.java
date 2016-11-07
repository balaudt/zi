package zi.chef.y16.feb;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * Created by balaudt on 8/22/16.
 */
public class VM1 {
    public static void main(String[] args) throws Exception {
//        BufferedReader reader = new BufferedReader(new FileReader("/Users/balaudt/Temp/hrank/input001.txt"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        boolean isMulti = false;
        while ((line = reader.readLine()) != null) {
            if (isMulti)
                System.out.println(line);
            else {
                if (line.contains("/*")) {
                    isMulti = true;
                    System.out.println(line.substring(line.indexOf("/*")));
                }
                else if (line.contains("//"))
                    System.out.println(line.substring(line.indexOf("//")));
            }
            if (line.contains("*/"))
                isMulti = false;
        }
    }
}
