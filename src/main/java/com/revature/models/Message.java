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

@Entity
@Table(name="messages")
public class Message {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="text", nullable=false)
	private String text;
	
	@ManyToOne
    @JoinColumn(name ="activity_id", referencedColumnName = "id")
	private Activity activity;
	
	@ManyToOne
    @JoinColumn(name ="author_id", referencedColumnName = "id")
	private User author;
	
	@Column(name="created_date")
	private LocalDate timeStamp;

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Message(String text, Activity activity, User author, LocalDate timeStamp) {
		super();
		this.text = text;
		this.activity = activity;
		this.author = author;
		this.timeStamp = timeStamp;
	}

	public Message(int id, String text, Activity activity, User author, LocalDate timeStamp) {
		super();
		this.id = id;
		this.text = text;
		this.activity = activity;
		this.author = author;
		this.timeStamp = timeStamp;
	}
}
