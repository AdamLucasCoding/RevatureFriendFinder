package com.revature.models;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name="activities")
public class Activity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="a_id")
	private int activityId;
	
	@Column(name="a_name", nullable=false)
	private String activityName;
	
	@Column(name="a_type", nullable=false)
	private String activityType;
	
	@Column(name="a_location", nullable=false)
	private String location;
	
	@Column(name="a_date_time", nullable=false)
	private Date dateTime;
	
	@Column(name="user_id", nullable=false)
	private User user;
	
	@Column(name="occupancy_max", nullable=false)
	private int occupancyMax;

	public Activity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Activity(String activityName, String activityType, String location, Date dateTime, User user,
			int occupancyMax) {
		super();
		this.activityName = activityName;
		this.activityType = activityType;
		this.location = location;
		this.dateTime = dateTime;
		this.user = user;
		this.occupancyMax = occupancyMax;
	}

	public Activity(int activityId, String activityName, String activityType, String location, Date dateTime,
			User user, int occupancyMax) {
		super();
		this.activityId = activityId;
		this.activityName = activityName;
		this.activityType = activityType;
		this.location = location;
		this.dateTime = dateTime;
		this.user = user;
		this.occupancyMax = occupancyMax;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getOccupancyMax() {
		return occupancyMax;
	}

	public void setOccupancyMax(int occupancyMax) {
		this.occupancyMax = occupancyMax;
	}

	@Override
	public String toString() {
		return "Activitie [activityId=" + activityId + ", activityName=" + activityName + ", activityType="
				+ activityType + ", location=" + location + ", dateTime=" + dateTime + ", user=" + user
				+ ", occupancyMax=" + occupancyMax + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(activityId, activityName, activityType, dateTime, location, occupancyMax, user);
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
		return activityId == other.activityId && Objects.equals(activityName, other.activityName)
				&& Objects.equals(activityType, other.activityType) && Objects.equals(dateTime, other.dateTime)
				&& Objects.equals(location, other.location) && occupancyMax == other.occupancyMax
				&& Objects.equals(user, other.user);
	}	
}
