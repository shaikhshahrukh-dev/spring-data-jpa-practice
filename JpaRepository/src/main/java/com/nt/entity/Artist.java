package com.nt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="ARTIST_INFO")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
public class Artist
{
	@Column(name="AID")
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "gen1", sequenceName = "artist_seq", initialValue = 1000, allocationSize = 1)
	@GeneratedValue(generator = "gen1", strategy = GenerationType.SEQUENCE)
	private Integer aid;

	//@NonNull
	@Column(name="ANAME",length = 40)
	private String aname;

	@NonNull
	@Column(length = 30)
	private String category;

	//@NonNull
	private Double fee;

}
