package zi.jam.y20.qual;

import org.apache.commons.lang.text.StrBuilder;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

public class DatabaseInteractor {
    public static void main(String[] args) throws IOException {
        Process exec = Runtime.getRuntime().exec("java -cp /Users/bchandrasekaran/src/zi/target/classes zi.jam.y20.qual.Database");
        PrintStream interPs = new PrintStream(exec.getOutputStream());
        Scanner scanner = new Scanner(exec.getInputStream());
        Random random = new Random();
        int b = 20;
        int t = random.nextInt(100) + 100;
        String tb = String.format("%d %d", t, b);
        System.out.println(tb);
        interPs.println(tb);
        interPs.flush();
        if (b == 10) {
            for (int i = 0; i < t; i++) {
                int num = random.nextInt(1024);
                String required = Database.padBinaryStr(num, 10);
                int interactions = 0;
                while (interactions <= 150) {
                    interactions++;
                    String s = scanner.nextLine();
                    if (s.length() < 3) {
                        interPs.println((num & (1 << (10 - Integer.parseInt(s)))) > 0 ? '1' : '0');
                        interPs.flush();
                    } else {
                        if (s.equals(required)) {
                            System.out.println(String.format("got %s want %s", s, required));
                            interPs.println('Y');
                            interPs.flush();
                        } else {
                            interPs.println('N');
                            System.out.println(String.format("got %s want %s", s, required));
                            System.exit(1);
                        }
                        break;
                    }
                }
            }
        } else {
            for (int i = 0; i < t; i++) {
                int num = random.nextInt(1 << 20);
                int interactions = 0;
                while (interactions <= 150) {
                    interactions++;
//                    System.out.println(interactions);
                    if ((interactions % 10) == 1) {
                        int op = random.nextInt(3);
                        switch (op) {
                            case 0:
                                num ^= 0xFFFFF;
                                break;
                            case 1:
                                num = Database.reverseBits(num, 20);
                                break;
                            case 2:
                                num = Database.reverseBits(num, 20) ^ 0xFFFFF;
                        }
                    }
                    String s = scanner.nextLine();
                    if (s.length() < 3) {
                        int pos = Integer.parseInt(s);
                        char x = (num & (1 << (20 - pos))) > 0 ? '1' : '0';
//                        System.out.println(pos + " " + x);
                        interPs.println(x);
                        interPs.flush();
                    } else {
                        System.out.println(new StrBuilder("got ").append(s).append(" want ").append(Database.padBinaryStr(num, 20)));
                        interPs.println('Y');
                        interPs.flush();
                        break;
                    }
                }
            }
        }
    }
}
