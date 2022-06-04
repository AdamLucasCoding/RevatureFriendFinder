package com.revature.models;


import java.util.Objects;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name="activity_participants")
public class ActivityParticipant {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne
    @JoinColumn(name ="user_id", referencedColumnName = "u_id")
	private User u_id;
	
	@ManyToOne
    @JoinColumn(name ="activity_id", referencedColumnName = "a_id")
	private Activity activity_id;
	
	public ActivityParticipant() {
		super();
	}

	public ActivityParticipant(User participant, Activity activity) {
		super();
		this.u_id = participant;
		this.activity_id = activity;
	}

	public ActivityParticipant(int id, User participant, Activity activity) {
		super();
		this.id = id;
		this.u_id = participant;
		this.activity_id = activity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getParticipant() {
		return u_id;
	}

	public void setParticipant(User participant) {
		this.u_id = participant;
	}

	public Activity getActivity() {
		return activity_id;
	}

	public void setActivity(Activity activity) {
		this.activity_id = activity;
	}

	@Override
	public String toString() {
		return "ActivityParticipant [id=" + id + ", participant=" + u_id + ", activity=" + activity_id + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(activity_id, id, u_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActivityParticipant other = (ActivityParticipant) obj;
		return Objects.equals(activity_id, other.activity_id) && id == other.id
				&& Objects.equals(u_id, other.u_id);
	}
	
	
	
}
