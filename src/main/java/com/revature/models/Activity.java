package com.revature.models;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="activities")
public class Activity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="type", nullable=false)
	private String type;
	
	@Column(name="location", nullable=false)
	private String location;
	
	@Column(name="created_date", nullable=false)
	private LocalDate dateTime;
	
	@ManyToOne
    @JoinColumn(name ="created_by", referencedColumnName = "id")
	private User created_by;
	
	@Column(name="occupancy_max", nullable=false)
	private int occupancyMax;

	public Activity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Activity(String name, String type, String location, LocalDate dateTime, User createdBy, int occupancyMax) {
		super();
		this.name = name;
		this.type = type;
		this.location = location;
		this.dateTime = dateTime;
		this.created_by = createdBy;
		this.occupancyMax = occupancyMax;
	}

	public Activity(int id, String name, String type, String location, LocalDate dateTime, User createdBy,
			int occupancyMax) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.location = location;
		this.dateTime = dateTime;
		this.created_by = createdBy;
		this.occupancyMax = occupancyMax;
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", name=" + name + ", type=" + type + ", location=" + location + ", dateTime="
				+ dateTime + ", createdBy=" + created_by + ", occupancyMax=" + occupancyMax + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocalDate getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDate dateTime) {
		this.dateTime = dateTime;
	}

	public User getCreated_by() {
		return created_by;
	}

	public void setCreated_by(User created_by) {
		this.created_by = created_by;
	}

	public int getOccupancyMax() {
		return occupancyMax;
	}

	public void setOccupancyMax(int occupancyMax) {
		this.occupancyMax = occupancyMax;
	}

	@Override
	public int hashCode() {
		return Objects.hash(created_by, dateTime, id, location, name, occupancyMax, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		return Objects.equals(created_by, other.created_by) && Objects.equals(dateTime, other.dateTime)
				&& id == other.id && Objects.equals(location, other.location) && Objects.equals(name, other.name)
				&& occupancyMax == other.occupancyMax && Objects.equals(type, other.type);
	}
	
	
	
}
