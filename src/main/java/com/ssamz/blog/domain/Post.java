package com.ssamz.blog.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
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
	private int id;
	
	@Column(nullable = true, length = 100)
	private String title;
	
	@Lob
	@Column(nullable = false)
	private String content;
	
	@ManyToOne(fetch = FetchType.EAGER)		// default: EAGER
	@JoinColumn(name = "userid")
	private User user;
	
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@Column(columnDefinition = "int default 0")
	private int hit;
}
