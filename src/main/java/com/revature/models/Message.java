package com.revature.models;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name="messages")
public class Message {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="m_id")
	private int messageId;
	
	@Column(name="message_text", nullable=false)
	private String messageText;
	
	@Column(name="activity_id", nullable=false)
	private Activity activity;
	
	@Column(name="user_id", nullable=false)
	private User user;
	
	@Column(name="time_stamp")
	private Date timeStamp;

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Message(String messageText, Activity activity, User user, Date timeStamp) {
		super();
		this.messageText = messageText;
		this.activity = activity;
		this.user = user;
		this.timeStamp = timeStamp;
	}

	public Message(int messageId, String messageText, Activity activity, User user, Date timeStamp) {
		super();
		this.messageId = messageId;
		this.messageText = messageText;
		this.activity = activity;
		this.user = user;
		this.timeStamp = timeStamp;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", messageText=" + messageText + ", activity=" + activity + ", user="
				+ user + ", timeStamp=" + timeStamp + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(activity, messageId, messageText, timeStamp, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		return Objects.equals(activity, other.activity) && messageId == other.messageId
				&& Objects.equals(messageText, other.messageText) && Objects.equals(timeStamp, other.timeStamp)
				&& Objects.equals(user, other.user);
	}
}
