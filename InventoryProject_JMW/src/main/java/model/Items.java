package model;

import java.sql.Date;

//this is our model, which is the table in the database, we need this to work with database , there is no other way
public class Items {

	private int id;
	private String itemName,itemColor,secondaryItemColor,itemBrand,size;
	private Date createdDate;
	private String location_name,location_rack,location_shelf;
	
	public Items() {
	}
	
	public Items(int id, String itemName, String itemColor, String secondaryItemColor, String itemBrand, String size,
			Date createdDate) {
		
		this.id = id;
		this.itemName = itemName;
		this.itemColor = itemColor;
		this.secondaryItemColor = secondaryItemColor;
		this.itemBrand = itemBrand;
		this.size = size;
		this.createdDate = createdDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemColor() {
		return itemColor;
	}
	public void setItemColor(String itemColor) {
		this.itemColor = itemColor;
	}
	public String getSecondaryItemColor() {
		return secondaryItemColor;
	}
	public void setSecondaryItemColor(String secondaryItemColor) {
		this.secondaryItemColor = secondaryItemColor;
	}
	public String getItemBrand() {
		return itemBrand;
	}
	public void setItemBrand(String itemBrand) {
		this.itemBrand = itemBrand;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getLocation_name() {
		return location_name;
	}

	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}

	public String getLocation_rack() {
		return location_rack;
	}

	public void setLocation_rack(String location_rack) {
		this.location_rack = location_rack;
	}

	public String getLocation_shelf() {
		return location_shelf;
	}

	public void setLocation_shelf(String location_shelf) {
		this.location_shelf = location_shelf;
	}
	
	
}
