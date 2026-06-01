package com.nt.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "JPA_MTOM_PATIENT")
@Setter
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class Patient {


	@Id
	@SequenceGenerator(name = "gen1",sequenceName = "SID_SEQ",initialValue = 100,allocationSize = 1)
	@GeneratedValue(generator = "gen1",strategy = GenerationType.SEQUENCE)
	private Integer pid;

	@Column(length = 30)
	@NonNull
	private String pname;

	@Column(length = 30)
	@NonNull
	private String paddrs;


	@ManyToMany(targetEntity = Doctor.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "patientInfo")
	Set<Doctor>docterInfo = new HashSet();


	//toString
	@Override
	public String toString() {
		return "Patient [pid=" + pid + ", sname=" + pname + ", saddrs=" + paddrs + "]";
	}


}
