package School_손지영Ver4;

//파일 업로드부터

public class Controller {
	private StudentDAO stuDAO;
	private SubjectDAO subDAO;
	
	public Controller(){
		stuDAO = new StudentDAO();
		subDAO = new SubjectDAO();
		Util.dataFileLoad(stuDAO,subDAO);
		run();
	}
	
	private void run() {
		while(true) {
			mainMenu();
			int sel = Util.getValue("메뉴 선택", 0, 8);
			if(sel==0) return;
			
			switch(sel) {
			case 1: 
				stuDAO.studentAddList();
				break;
			case 2: 
				stuDAO.studentRemoveList(subDAO);
				break;
			case 3: 
				subDAO.subjectAddList(stuDAO);
				break;
			case 4: 
				subDAO.subjectRemoveList(stuDAO);
				break;
			case 5: 
				stuDAO.printStudentList(subDAO);
				break;
			case 6:
				subDAO.printSubjectList(stuDAO);
				break;
			case 7: 
				Util.dataFileSave(stuDAO,subDAO);
				break;
			case 8: 
				Util.dataFileLoad(stuDAO,subDAO);
				break;
			}
		}
	}
	
	private void mainMenu() {
		System.out.println("[1] 학생 추가"); //학번(1001) 자동증가 : 학생id 중복 불가
		System.out.println("[2] 학생 삭제"); //학생 id 입력 후 과목도 같이 삭제
		System.out.println("[3] 과목 추가"); //학번 입력 후 점수는 랜덤 50~100 : 과목이름 중복저장 불가능 
		System.out.println("[4] 과목 삭제"); //학번 입력 후 과목 이름 입력 후 해당 과목에서 학생 1명 삭제
		System.out.println("[5] 전체학생목록"); // 점수 오름차순으로 출력
		System.out.println("[6] 과목별학생목록"); //과목 이름 입력 후 해당 과목 학생 이름과 점수 출력(이름 오름차순)
		System.out.println("[7] 파일 저장"); 
		System.out.println("[8] 파일 불러오기");
		System.out.println("[0] 종료");
	}
}
