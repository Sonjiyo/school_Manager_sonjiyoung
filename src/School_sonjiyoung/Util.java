package School_손지영Ver4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Util {
	private static Scanner sc;
	private static File file;
	private static String filePath = System.getProperty("user.dir") + "\\src\\";
	private static Util util = new Util();
	
	private Util(){
		filePath += this.getClass().getPackageName()+"\\";
		sc = new Scanner(System.in);
	}
	
	public static int getValue(String msg, int start, int end) {
		while(true) {
			try {
				System.out.print(msg+"["+start+"~"+end+"] : ");				
				int sel = sc.nextInt();
				if(sel<start || sel>end) {
					System.out.println("범위 오류");
					continue;
				}
				return sel;
			} catch (Exception e) {
				sc.nextLine();
				System.out.println("정수로 입력해주세요");
			}
		}
	}
	
	public static String getValue(String msg) {
		System.out.print(msg);
		String input = sc.next();
		return input;
	}
	
	public static void dataFileSave(StudentDAO stuDAO, SubjectDAO subDAO) {
		fileSave("studentData.txt",stuDAO.studentDataSave());
		fileSave("subjectData.txt",subDAO.subjectDataSave());
		
		System.out.println("[저장 완료]");
	}

	private static void fileSave(String fileName, String data) {
		file = new File(filePath+fileName);
		
		try(FileWriter fw = new FileWriter(file)){
			fw.write(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void dataFileLoad(StudentDAO stuDAO, SubjectDAO subDAO) {
		String stuData = fileLoad("studentData.txt");
		String subData = fileLoad("subjectData.txt");
		
		stuDAO.studentDataLoad(stuData);
		subDAO.subjectDataLoad(subData);
		
		System.out.println("[데이터 불러옴]");
	}
	
	private static String fileLoad(String fileName) {
		String data = "";
		file = new File(filePath+fileName);
		
		try(FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);){
			String str = "";
			while(true) {
				str = br.readLine();
				if(str==null) break;
				data += str+"\n";
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}
}
