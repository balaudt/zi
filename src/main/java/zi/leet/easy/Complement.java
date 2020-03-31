package zi.leet.easy;

//https://leetcode.com/problems/number-complement
public class Complement {
    public int findComplement(int num) {
        char[] binaryRep = Integer.toBinaryString(num).toCharArray();
        char[] complement = new char[binaryRep.length];
        for (int i = 0; i < binaryRep.length; i++) {
            complement[i] = binaryRep[i] == '1' ? '0' : '1';
        }
        return Integer.parseInt(new String(complement), 2);
    }

    public static void main(String[] args) {
        Complement complement = new Complement();
        System.out.println(complement.findComplement(5));
        System.out.println(complement.findComplement(1));
    }
}
