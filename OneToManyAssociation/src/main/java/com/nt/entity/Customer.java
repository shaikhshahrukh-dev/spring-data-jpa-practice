package com.nt.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "JPA_OTM_CUSTOMER")
@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class Customer
{

	@Id
	@SequenceGenerator(name = "gen1",sequenceName = "CNO_SEQ",initialValue = 1000,allocationSize = 1)
	@GeneratedValue(generator = "gen1", strategy = GenerationType.SEQUENCE)
	private Integer cno;
	@NonNull
	@Column(length = 20)
	private String cname;
	@NonNull
	@Column(length = 20)
	private String caddrs;

	//for building one to many association
	@OneToMany(targetEntity = PhoneNumber.class,
			orphanRemoval = true,fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "cust" )
	private Set<PhoneNumber>phoneInfo;

	//toString()
	@Override
	public String toString() {
		return "Customer [cno=" + cno + ", cname=" + cname + ", caddrs=" + caddrs + "]";
	}


}
