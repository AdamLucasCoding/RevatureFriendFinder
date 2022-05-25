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
	private Activity activity_id;
	
	@ManyToOne
    @JoinColumn(name ="author_id", referencedColumnName = "id")
	private User author_id;
	
	@Column(name="created_date")
	private LocalDate timeStamp;

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Message(String text, Activity activity, User author, LocalDate timeStamp) {
		super();
		this.text = text;
		this.activity_id = activity;
		this.author_id = author;
		this.timeStamp = timeStamp;
	}

	public Message(int id, String text, Activity activity, User author, LocalDate timeStamp) {
		super();
		this.id = id;
		this.text = text;
		this.activity_id = activity;
		this.author_id = author;
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", text=" + text + ", activity_id=" + activity_id + ", author_id=" + author_id
				+ ", timeStamp=" + timeStamp + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Activity getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(Activity activity_id) {
		this.activity_id = activity_id;
	}

	public User getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(User author_id) {
		this.author_id = author_id;
	}

	public LocalDate getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDate timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
}
