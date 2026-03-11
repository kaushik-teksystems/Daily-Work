package day2;

import java.time.LocalDate;
import java.util.Date;

public class PermanentEmployee implements Employee {
	private String empID;
	private String Position;
	Date joiningDate;
	
	public String getPosition() {
		return Position;
	}

	public void setPosition(String position) {
		Position = position;
	}

	public Date getDate() {
		return joiningDate;
	}

	public void setDate(Date date) {
		this.joiningDate = date;
	}

	public LocalDate getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(LocalDate currentDate) {
		this.currentDate = currentDate;
	}

	public String getEmpID() {
		return empID;
	}

	LocalDate currentDate = LocalDate.now();

	@Override
	public Date GetHistory() {
		return joiningDate;
	}

	@Override
	public String GetCurrent() {
		return Position;
	}

	@Override
	public String Hire() {
		return empID;
	}

}
