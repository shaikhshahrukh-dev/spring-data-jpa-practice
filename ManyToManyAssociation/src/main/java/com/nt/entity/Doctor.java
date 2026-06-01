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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "JPA_MTOM_DOCTOR")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Doctor {

	@Id
	@SequenceGenerator(name = "gen1", sequenceName = "DNO_SEQ",initialValue = 1,allocationSize = 1)
	@GeneratedValue(generator = "gen1",strategy = GenerationType.SEQUENCE)
	private Integer did;

	@NonNull
	@Column(length = 30)
	private String dname;

	@NonNull
	@Column(length = 30)
	private String daddrs;


	@ManyToMany(targetEntity = Patient.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name = "JPA_MTOM_DOCTOR_PATIENT",//third table
			joinColumns = @JoinColumn(name="doctor_id",referencedColumnName = "did"),//owning side Fk column
			inverseJoinColumns = @JoinColumn(name="patient_id",referencedColumnName = "pid"))//non owing FK column
	private Set<Patient> patientInfo= new HashSet();


	//toString() (alt+shift+s,s)
	@Override
	public String toString() {
		return "Doctor [did=" + did + ", dname=" + dname + ", daddrs=" + daddrs + "]";
	}


}
