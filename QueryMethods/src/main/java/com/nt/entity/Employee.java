package com.nt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "EMP")
@Data
@AllArgsConstructor
public class Employee
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EMPNO")
	private Integer empNo;
	@Column(name = "ENAME")
	private String empName;
	private String job;
	private Double sal;
	@Column(name = "DEPTNO")
	private Integer deptNo;

	public Employee() {
		System.out.println("Employee::0-params constructor");
	}
}
