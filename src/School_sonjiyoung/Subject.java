package School_손지영Ver4;

import java.util.ArrayList;

public class Subject {
	private int stuNo;
	private String subName;
	private int score;
	
	public Subject(String stuNo, String subName, String score) {
		this.stuNo = Integer.parseInt(stuNo);
		this.subName = subName;
		this.score = Integer.parseInt(score);
	}
	
	public int getStuNo() {
		return stuNo;
	}

	public String getSubName() {
		return subName;
	}

	public int getScore() {
		return score;
	}

	@Override
	public String toString() {
		return subName+" ["+score+"점] ";
	}

}
