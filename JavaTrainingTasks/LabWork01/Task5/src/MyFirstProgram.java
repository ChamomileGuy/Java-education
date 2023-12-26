import myfirstpackage.*;

class MyFirstClass {
	public static void main(String[] s) {
		MySecondClass o = new MySecondClass(10, 15);
		int i, j;
		
		for (i = 1; i <= 8; i++) {
			for(j = 1; j <= 8; j++) {
				o.setNum1(i);
				o.setNum2(j);
				System.out.print(o.mul());
				System.out.print(" ");
			}
			System.out.println();
		}
		
	}
}