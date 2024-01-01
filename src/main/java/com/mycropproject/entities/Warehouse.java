package com.mycropproject.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="warehouse")
@NoArgsConstructor
@Getter
@Setter
	
	public class Warehouse{

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int id;
		private String name;
	    private String tehsil;
		
}
		
		


