package id_318247822;

import java.util.Scanner;

public class ConsoleUI implements Messageable {

	private Scanner scan = new Scanner(System.in);

	@Override
	public void showMessage(String msg) {
		System.out.println(msg);

	}

	@Override
	public String getString(String msg) {
		System.out.println(msg);
		return scan.next();
	}

	@Override
	public int getInt(String msg) {
		System.out.println(msg);
		return scan.nextInt();
	}

	@Override
	public boolean getBoolean(String msg) {
		System.out.println(msg);
		return scan.nextBoolean();
	}

	@Override
	public void scan() {
		scan.next();

	}

}
