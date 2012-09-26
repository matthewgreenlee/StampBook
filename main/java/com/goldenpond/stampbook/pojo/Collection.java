package com.goldenpond.stampbook.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "COLLECTION")
@XmlRootElement
public class Collection {

	private Long id;

	private Long userId;

	private Long itemId;

	private boolean postmarked;

	private Integer grade;

	@Id
	@Column(name = "COLLECTION_ID")
	@GeneratedValue(generator="native")
	@GenericGenerator(name="native", strategy = "native")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "USER_ID")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "ITEM_ID")
	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	@Column(name = "POSTMARKED")
	public boolean isPostmarked() {
		return postmarked;
	}

	public void setPostmarked(boolean postmarked) {
		this.postmarked = postmarked;
	}

	@Column(name = "GRADE")
	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}
}
