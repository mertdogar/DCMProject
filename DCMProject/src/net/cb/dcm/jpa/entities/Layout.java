package net.cb.dcm.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "layouts")
public class Layout {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
}
