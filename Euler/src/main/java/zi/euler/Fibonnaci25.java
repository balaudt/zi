package zi.euler;

import org.apfloat.Apint;

public class Fibonnaci25 {

	public static void main(String[] args) {
		Apint a=new Apint(1);
		Apint b=new Apint(1);
		int count=2;
		while(true){
			Apint c = b.add(a);
			count++;
			if(c.toString().length()==1000){
				System.out.println(count);
				break;
			}
			a=b;
			b=c;
		}
	}
}
