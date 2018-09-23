package com.burgtech.trackyourchild.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "responseCode")
public class ResponseCode 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name  = "ID")
	private Long id;
	
	@Column(unique = true)
	private String code;
	private String message;
	private String type;
}
