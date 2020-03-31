package zi.leet.easy;

import java.util.Arrays;

//https://leetcode.com/problems/reorder-log-files
public class ReorderLogFiles {

    class LogLine implements Comparable<LogLine> {
        boolean isLetterLog;
        int line;
        String id;
        String log;

        LogLine(String lineStr, int line) {
            this.line = line;
            int sepInd = lineStr.indexOf(' ');
            id = lineStr.substring(0, sepInd);
            log = lineStr.substring(sepInd + 1);
            char firstLogChar = log.charAt(0);
            isLetterLog = firstLogChar >= 'a' && firstLogChar <= 'z';
        }

        @Override
        public int compareTo(LogLine o) {
            if (isLetterLog) {
                if (!o.isLetterLog) {
                    return -1;
                } else {
                    int logOut = log.compareTo(o.log);
                    return logOut == 0 ? id.compareTo(o.id) : logOut;
                }
            } else {
                if (o.isLetterLog) {
                    return 1;
                } else {
                    return line - o.line;
                }
            }
        }

        private String getLogLine() {
            return id + " " + log;
        }

        @Override
        public String toString() {
            return "LogLine{" +
                    "isLetterLog=" + isLetterLog +
                    ", line=" + line +
                    ", id='" + id + '\'' +
                    ", log='" + log + '\'' +
                    '}';
        }
    }

    public String[] reorderLogFiles(String[] logs) {
        LogLine logLines[] = new LogLine[logs.length];
        for (int i = 0; i < logs.length; i++) {
            logLines[i] = new LogLine(logs[i], i);
        }
        Arrays.sort(logLines);
        System.out.println(Arrays.toString(logLines));
        String[] out = new String[logs.length];
        for (int i = 0; i < logLines.length; i++) {
            out[i] = logLines[i].getLogLine();
        }
        return out;
    }

    public static void main(String[] args) {
        ReorderLogFiles files = new ReorderLogFiles();
        System.out.println(Arrays.toString(files.reorderLogFiles(new String[]{"a1 9 2 3 1", "g1 act car", "zo4 4 7", "ab1 off key dog", "a8 act zoo"})));
    }
}
