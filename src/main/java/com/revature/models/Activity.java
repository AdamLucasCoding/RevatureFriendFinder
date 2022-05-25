package com.revature.models;

import java.time.LocalDate;

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
	
	
	
}
