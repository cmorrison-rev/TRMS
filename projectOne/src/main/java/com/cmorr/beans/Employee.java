package com.cmorr.beans;

import java.util.UUID;

public class Employee {
	private UUID empId;
	private String fName;
	private String lName;
	private String superId;
	private String dept;
	private Position position;
	private double totalBudget;
	private double pendingBudget;
	private double approvedBudget;

	public static enum Position {
		EMPLOYEE, DIRECT_SUPERVISOR, DEPT_HEAD, BENCO
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public double getTotalBudget() {
		return totalBudget;
	}

	public void setTotalBudget(double totalBudget) {
		this.totalBudget = totalBudget;
	}

	public double getPendingBudget() {
		return pendingBudget;
	}

	public void setPendingBudget(double pendingBudget) {
		this.pendingBudget = pendingBudget;
	}

	public double getApprovedBudget() {
		return approvedBudget;
	}

	public void setApprovedBudget(double approvedBudget) {
		this.approvedBudget = approvedBudget;
	}

	public UUID getEmpId() {
		return empId;
	}

	public void setEmpId(UUID empId) {
		this.empId = empId;
	}

	public String getSuperId() {
		return superId;
	}

	public void setSuperId(String superId) {
		this.superId = superId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(approvedBudget);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((dept == null) ? 0 : dept.hashCode());
		result = prime * result + ((empId == null) ? 0 : empId.hashCode());
		result = prime * result + ((fName == null) ? 0 : fName.hashCode());
		result = prime * result + ((lName == null) ? 0 : lName.hashCode());
		temp = Double.doubleToLongBits(pendingBudget);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((superId == null) ? 0 : superId.hashCode());
		temp = Double.doubleToLongBits(totalBudget);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (Double.doubleToLongBits(approvedBudget) != Double.doubleToLongBits(other.approvedBudget))
			return false;
		if (dept == null) {
			if (other.dept != null)
				return false;
		} else if (!dept.equals(other.dept))
			return false;
		if (empId == null) {
			if (other.empId != null)
				return false;
		} else if (!empId.equals(other.empId))
			return false;
		if (fName == null) {
			if (other.fName != null)
				return false;
		} else if (!fName.equals(other.fName))
			return false;
		if (lName == null) {
			if (other.lName != null)
				return false;
		} else if (!lName.equals(other.lName))
			return false;
		if (Double.doubleToLongBits(pendingBudget) != Double.doubleToLongBits(other.pendingBudget))
			return false;
		if (position != other.position)
			return false;
		if (superId == null) {
			if (other.superId != null)
				return false;
		} else if (!superId.equals(other.superId))
			return false;
		if (Double.doubleToLongBits(totalBudget) != Double.doubleToLongBits(other.totalBudget))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [fName=" + fName + ", lName=" + lName + ", empId=" + empId + ", superId=" + superId + ", dept="
				+ dept + ", position=" + position + ", totalBudget=" + totalBudget + ", pendingBudget=" + pendingBudget
				+ ", approvedBudget=" + approvedBudget + "]";
	}

}
