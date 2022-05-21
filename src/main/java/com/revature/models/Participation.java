package com.revature.models;

import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name="participations")
public class Participation {

	private int participationId;
	private User user;
	private Activity activity;
	
	public Participation() {
		super();
	}
	
	public Participation(User user, Activity activity) {
		super();
		this.user = user;
		this.activity = activity;
	}
	
	public Participation(int participationId, User user, Activity activity) {
		super();
		this.participationId = participationId;
		this.user = user;
		this.activity = activity;
	}
	
	public int getParticipationId() {
		return participationId;
	}
	
	public void setParticipationId(int participationId) {
		this.participationId = participationId;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Activity getActivity() {
		return activity;
	}
	
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	
	@Override
	public String toString() {
		return "Participation [participationId=" + participationId + ", user=" + user + ", activity=" + activity + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(activity, participationId, user);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Participation other = (Participation) obj;
		return Objects.equals(activity, other.activity) && participationId == other.participationId
				&& Objects.equals(user, other.user);
	}
}
