package zi.chef.y16.feb;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by balaudt on 8/19/16.
 */
public class MapR2 {
    public static void main(String[] args) {
        System.out.println(stringReplace("abc123", "", "xx"));
    }

    static String stringReplace(String src, String match, String replace) {
        if (src == null || match == null || replace == null)
            return src;
        char[] in = src.toCharArray();
        char[] matchArray = match.toCharArray();
        char[] replaceArray = replace.toCharArray();
        if (matchArray.length == 0)
            return src;
        Set<Integer> matchIndices = new HashSet<>();
        int j;
        for (int i = 0; i < in.length; i++) {
            if (in[i] == matchArray[0]) {
                for (j = 0; j < matchArray.length && i < in.length; j++, i++) {
                    if (in[i] != matchArray[j])
                        break;
                }
                if (j == matchArray.length) matchIndices.add(i - matchArray.length);
            }
        }
        char[] out = new char[in.length + matchIndices.size() * (replaceArray.length - matchArray.length)];
        int outInd = 0;
        for (int i = 0; i < in.length; i++) {
            if (matchIndices.contains(i)) {
                for (j = 0; j < replaceArray.length; j++, outInd++)
                    out[outInd] = replaceArray[j];
                i += matchArray.length - 1;
            } else
                out[outInd++] = in[i];
        }
        return new String(out);
    }
}
