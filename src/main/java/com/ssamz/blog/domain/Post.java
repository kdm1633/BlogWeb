package com.ssamz.blog.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int num;
	
	@Column(nullable = true, length = 100)
	private String title;
		
	@Lob
	@Column(nullable = false)
	private String content;
	
	@ManyToOne(fetch = FetchType.EAGER)		// default: EAGER
	@JoinColumn(name = "usernum")
	private User user;
	
	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "post")
	@OrderBy("num desc")
	private List<Comment> commentList;
	
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@Column(columnDefinition = "int default 0")
	private int hit;
}
