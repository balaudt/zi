package zi.leet.easy;

//https://leetcode.com/problems/baseball-game
public class BaseballGame {
    public int calPoints(String[] ops) {
        int v1 = Integer.MIN_VALUE, v2 = Integer.MIN_VALUE, sum = 0;
        for (String op : ops) {
            switch(op){
                case "+":
                    sum +=  v1+v2;
                    v1=v2;

            }
        }
        return sum;
    }
}
