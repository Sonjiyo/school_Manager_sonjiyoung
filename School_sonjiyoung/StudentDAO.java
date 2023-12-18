package School_손지영Ver4;

import java.util.ArrayList;

public class StudentDAO {
	private ArrayList<Student> stuList;
	private int maxNum;
	
	public StudentDAO(){
		stuList = new ArrayList<Student>();
	}
	
	private void studentNumberMax() {
		for(Student s : stuList) {
			if(maxNum<s.getStuNo()) {
				maxNum = s.getStuNo();
			}
		}
	}
	
	private boolean studentListIsEmpty() {
		if(stuList.size()==0) {
			System.out.println("학생이 없습니다.");
			return true;
		}
		return false;
	}
	
	private int studentIdCheck(String id) {
		if(stuList.size()==0) return -1;
		
		for(int i=0; i<stuList.size(); i++) {
			if(stuList.get(i).getStuId().equals(id)) {
				return i;
			}
		}
		return -1;
	}
	
	public int studentNumberCheck() {
		studentNumberMax();
		if(stuList.size()==0) {
			System.out.println("학생이 없습니다.");
			return 0;
		}
		int num = Util.getValue("학번 입력", 1001,maxNum);
		
		while(true) {
			for(int i=0; i<stuList.size(); i++) {
				if(stuList.get(i).getStuNo()==num) {
					return num;
				}
			}
			break;
		}
		System.out.println("존재하지 않는 학번입니다.");
		return 0;
	}
	
	public String studentDataSave() {
		String data = "";
		if(stuList.size()==0) return data;
		for(Student s : stuList) {
			data += "%d/%s/%s\n".formatted(s.getStuNo(),s.getStuName(),s.getStuId());
		}
		data = data.substring(0,data.length()-1);
		return data;
	}
	
	public void studentDataLoad(String data) {
		if(data.isEmpty()) return;
		stuList.clear();
		String[] arr = data.split("\n");
		for(int i =0; i<arr.length; i++) {
			String[] temp = arr[i].split("/");
			
			stuList.add(new Student(temp[0],temp[1],temp[2]));
		}
	}
	
	public void studentAddList() {
		String id = Util.getValue("아이디를 입력하세요 : ");
		if(studentIdCheck(id)!=-1) {
			System.out.println("중복된 아이디입니다.");
			return;
		}
		
		String name = Util.getValue("이름을 입력하세요 : ");
		int stuNo = stuList.size()==0 ? 1001 : stuList.get(stuList.size()-1).getStuNo()+1;
		
		stuList.add(new Student(stuNo+"",name,id));
		
		System.out.println("[추가 완료]");
	}
	
	public void studentRemoveList(SubjectDAO subDAO) {
		if(studentListIsEmpty()) return;
		String id = Util.getValue("아이디를 입력하세요 : ");
		int idx = studentIdCheck(id);
		if(idx==-1) {
			System.out.println("잘못된 id입니다.");
			return;
		}
		
		subDAO.studentRemoveList(stuList.get(idx).getStuNo());
		stuList.remove(idx);
		
		System.out.println("[삭제 완료]");
	}
	
	private ArrayList<Student> sortScoreStudentList(SubjectDAO subDAO) {
		ArrayList<Student> copy = new ArrayList<>();
	
		for(int i=0; i<stuList.size(); i++) {
			copy.add(stuList.get(i));
		}
		
		for(int i =0; i<copy.size(); i++) {
			double total1 = subDAO.studentTotal(copy.get(i).getStuNo());
			for(int j=0; j<copy.size(); j++) {
				double total2 = subDAO.studentTotal(copy.get(j).getStuNo());
				if(total1>total2) {
					Student temp = copy.get(i);
					copy.set(i, copy.get(j));
					copy.set(j, temp);
				}
			}
		}
		return copy;
	}
	
	public void printStudentList(SubjectDAO subDAO) {
		ArrayList<Student> copy = sortScoreStudentList(subDAO);
		System.out.println("========================");
		System.out.println("번호\t이름\t아이디");
		System.out.println("------------------------");
		for(Student s : copy) {
			System.out.println(s);
			subDAO.printStudentList(s.getStuNo());
		}
		System.out.println("========================");
	}
	
	Student getStudentNumber(Integer num) {
		for(Student s : stuList) {
			if(s.getStuNo()==num) {
				return s;
			}
		}
		return null;
	}
	
	public void printSubjectList(ArrayList<Integer> arr) {
		ArrayList<Student> copy = new ArrayList<Student>();
		
		for(int i =0; i<arr.size(); i++) {
			copy.add(getStudentNumber(arr.get(i)));
		}
		
		for(int i =0; i<copy.size(); i++) {
			for(int j=0; j<copy.size(); j++) {
				if(copy.get(i).getStuName().compareTo(copy.get(j).getStuName())<0) {
					Student temp = copy.get(i);
					copy.set(i, copy.get(j));
					copy.set(j, temp);
				}
			}
		}
		
		for(Student s : copy) {
			System.out.println(s);
		}
	}
}
