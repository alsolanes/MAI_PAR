import config.ConfigLoader;
import item.Office;

public class MyMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Planning and approximate reasoning");
		System.out.println("\tAuthors: \tPablo Martinez & Aleix Solanes");
		System.out.println("\tAcademic Year: \t2015/16");
		ConfigLoader loader = new ConfigLoader();
		loader.load("config_example.txt");
	}

}
