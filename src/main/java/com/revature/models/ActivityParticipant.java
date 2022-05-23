package com.revature.models;


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
    @JoinColumn(name ="user_id", referencedColumnName = "id")
	private User participant;
	
	@ManyToOne
    @JoinColumn(name ="activity_id", referencedColumnName = "id")
	private Activity activity;
	
	public ActivityParticipant() {
		super();
	}

	public ActivityParticipant(User participant, Activity activity) {
		super();
		this.participant = participant;
		this.activity = activity;
	}

	public ActivityParticipant(int id, User participant, Activity activity) {
		super();
		this.id = id;
		this.participant = participant;
		this.activity = activity;
	}
}
