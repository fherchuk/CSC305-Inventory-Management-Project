package model;

import java.sql.Date;

//this is our model, which is the table in the database, we need this to work with database , there is no other way
public class TodoItems {

	private int id, estimate_hours;
	private String title, item_description, priority, item_state;
	private Date due_date, created_date;
	
	public TodoItems() {}
	
	public TodoItems(int id, int estimate_hours, String title, String item_description, String priority,
			String item_state, Date due_date, Date created_date) {
		this.id = id;
		this.estimate_hours = estimate_hours;
		this.title = title;
		this.item_description = item_description;
		this.priority = priority;
		this.item_state = item_state;
		this.due_date = due_date;
		this.created_date = created_date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEstimate_hours() {
		return estimate_hours;
	}
	public void setEstimate_hours(int estimate_hours) {
		this.estimate_hours = estimate_hours;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getItem_description() {
		return item_description;
	}
	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getItem_state() {
		return item_state;
	}
	public void setItem_state(String item_state) {
		this.item_state = item_state;
	}
	public Date getDue_date() {
		return due_date;
	}
	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	
	
}
