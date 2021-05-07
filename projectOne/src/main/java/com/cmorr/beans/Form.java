package com.cmorr.beans;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Form {

	private UUID formId;
	private UUID empId;
	private Type type;
	private Status status;
	private double amount;
	private boolean superApp;
	private boolean dheadApp;
	private boolean bencoApp;
	private LocalDateTime creation;
	private LocalDateTime eventDate;
	private boolean passed;
	private boolean urgent;
	private String comments;
	private List<String> attachments;

	public enum Type {
		UniCourse(0.8), Seminar(0.6), CertPrep(0.75), Certification(1.0), TechTraining(0.9), Other(0.3);

		public final double d;

		private Type(double d) {
			this.d = d;
		}
	}

	public enum Status {
		PENDING, APPROVED, DENIED
	}

	public UUID getFormId() {
		return formId;
	}

	public void setFormId(UUID formId) {
		this.formId = formId;
	}

	public UUID getEmpId() {
		return empId;
	}

	public void setEmpId(UUID empId) {
		this.empId = empId;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public boolean isSuperApp() {
		return superApp;
	}

	public void setSuperApp(boolean superApp) {
		this.superApp = superApp;
	}

	public boolean isDheadApp() {
		return dheadApp;
	}

	public void setDheadApp(boolean dheadApp) {
		this.dheadApp = dheadApp;
	}

	public boolean isBencoApp() {
		return bencoApp;
	}

	public void setBencoApp(boolean bencoApp) {
		this.bencoApp = bencoApp;
	}

	public LocalDateTime getCreation() {
		return creation;
	}

	public void setCreation(LocalDateTime creation) {
		this.creation = creation;
	}

	public LocalDateTime getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDateTime eventDate) {
		this.eventDate = eventDate;
	}

	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}

	public boolean isUrgent() {
		return urgent;
	}

	public void setUrgent(boolean urgent) {
		this.urgent = urgent;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public List<String> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<String> list) {
		this.attachments = list;
	}

	@Override
	public String toString() {
		return "Form [formId=" + formId + ", empId=" + empId + ", type=" + type + ", status=" + status + ", amount="
				+ amount + ", superApp=" + superApp + ", dheadApp=" + dheadApp + ", bencoApp=" + bencoApp
				+ ", creation=" + creation + ", eventDate=" + eventDate + ", passed=" + passed + ", urgent=" + urgent
				+ ", comments=" + comments + ", attachments=" + attachments + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((attachments == null) ? 0 : attachments.hashCode());
		result = prime * result + (bencoApp ? 1231 : 1237);
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((creation == null) ? 0 : creation.hashCode());
		result = prime * result + (dheadApp ? 1231 : 1237);
		result = prime * result + ((empId == null) ? 0 : empId.hashCode());
		result = prime * result + ((eventDate == null) ? 0 : eventDate.hashCode());
		result = prime * result + ((formId == null) ? 0 : formId.hashCode());
		result = prime * result + (passed ? 1231 : 1237);
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + (superApp ? 1231 : 1237);
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + (urgent ? 1231 : 1237);
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
		Form other = (Form) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (attachments == null) {
			if (other.attachments != null)
				return false;
		} else if (!attachments.equals(other.attachments))
			return false;
		if (bencoApp != other.bencoApp)
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (creation == null) {
			if (other.creation != null)
				return false;
		} else if (!creation.equals(other.creation))
			return false;
		if (dheadApp != other.dheadApp)
			return false;
		if (empId == null) {
			if (other.empId != null)
				return false;
		} else if (!empId.equals(other.empId))
			return false;
		if (eventDate == null) {
			if (other.eventDate != null)
				return false;
		} else if (!eventDate.equals(other.eventDate))
			return false;
		if (formId == null) {
			if (other.formId != null)
				return false;
		} else if (!formId.equals(other.formId))
			return false;
		if (passed != other.passed)
			return false;
		if (status != other.status)
			return false;
		if (superApp != other.superApp)
			return false;
		if (type != other.type)
			return false;
		if (urgent != other.urgent)
			return false;
		return true;
	}

}
