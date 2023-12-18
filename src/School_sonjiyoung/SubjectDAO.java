package School_sonjiyoung;

import java.util.ArrayList;
import java.util.Random;

public class SubjectDAO {
	private ArrayList<Subject> subList;

	public SubjectDAO(){
		subList = new ArrayList<Subject>();
	}
	
	private int subjectNameCheck(String name, int num) {
		if(subList.size()==0) return -1;
		
		for(int i=0; i<subList.size(); i++) {
			if(subList.get(i).getSubName().equals(name) && subList.get(i).getStuNo()==num) {
				return i;
			}
		}
		return -1;
	}
	
	public String subjectDataSave() {
		String data = "";
		if(subList==null) return data;
		for(Subject s : subList) {
			data += "%d/%s/%d\n".formatted(s.getStuNo(),s.getSubName(),s.getScore());
		}
		data = data.substring(0,data.length()-1);
		return data;
	}
	
	public void subjectDataLoad(String data) {
		if(data.isEmpty()) return;
		subList.clear();
		String[] arr = data.split("\n");
		for(int i =0; i<arr.length; i++) {
			String[] temp = arr[i].split("/");
			
			subList.add(new Subject(temp[0],temp[1],temp[2]));
		}
	}
	
	public void studentRemoveList(int num) {
		for(int i =0; i<subList.size(); i++) {
			if(subList.get(i).getStuNo()==num) {
				subList.remove(i);
				i-=1;
			}
		}
	}
	
	public void subjectAddList(StudentDAO stuDAO) {
		Random rd = new Random();
		int num = stuDAO.studentNumberCheck();
		if(num==0) return;
		
		String name = Util.getValue("과목명 입력 : ");
		if(subjectNameCheck(name,num)!=-1) {
			System.out.println("중복된 과목명 입니다.");
			return;
		}
		int score = rd.nextInt(51)+50;
		subList.add(new Subject(num+"",name,score+""));
		
		System.out.println("[추가 완료]");
	}
	
	public void subjectRemoveList(StudentDAO stuDAO) {
		int num = stuDAO.studentNumberCheck();
		if(num==0) return;
		
		int idx = -1;
		for(int i =0; i<subList.size(); i++) {
			if(subList.get(i).getStuNo()==num) {
				idx = i;
			}
		}
		if(idx==-1) {
			System.out.println("삭제할 과목이 없습니다.");
			return;
		}
		
		String name = Util.getValue("과목명 입력 : ");
		idx = subjectNameCheck(name,num);
		if(idx==-1) {
			System.out.println("해당 학번에 존재하지 않는 과목입니다.");
			return;
		}
		
		subList.remove(idx);
		
		System.out.println("[삭제 완료]");
	}
	
	public double studentTotal(int num) {
		int total = 0;
		int cnt=0;
		
		for(int i =0; i<subList.size(); i++) {
			if(subList.get(i).getStuNo()==num) {
				total += subList.get(i).getScore();
				cnt++;
			}
		}
		
		if(cnt==0) return 0;
		
		return total*1.0/cnt;
	}
	
	public void printStudentList(int num) {
		int cnt =0;
		for(Subject s : subList) {
			if(s.getStuNo()==num) {
				cnt++;
				System.out.print(s+" ");				
			}
		}
		if(cnt==0) System.out.print("과목이 없습니다.");
		System.out.println();
	}
	
	public void printSubjectList(StudentDAO stuDAO) {
		String name = Util.getValue("과목명 입력 : ");
		
		ArrayList<Integer> arr = new ArrayList<>();
		for(int i=0; i<subList.size(); i++) {
			if(subList.get(i).getSubName().equals(name)) {
				arr.add(subList.get(i).getStuNo());
			}
		}
		
		if(arr.size()==0) {
			System.out.println("학생 데이터가 없습니다.");
		}

		System.out.println("=="+name+"==");
		stuDAO.printSubjectList(arr);
		
	}
}
