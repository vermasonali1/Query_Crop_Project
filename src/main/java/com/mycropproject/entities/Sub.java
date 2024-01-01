package com.mycropproject.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Subs")
@NoArgsConstructor
@Getter
@Setter
	
	public class Sub{

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int subId;
		private String name;
		
//		
		@ManyToOne
		private Crop crop;
//		
		
}