package com.nt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.entity.Employee;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer>{

	//@Query("from Employee")
	//@Query("from Employee emp")
	//@Query("from com.nt.entity.Employee as emp")
	@Query("select emp from com.nt.entity.Employee as emp")
	public List<Employee> showAllEmployees();

	@Query("from Employee where sal>=?1 and sal<=?2")
	public List<Employee>showEmpsBySalaryRange(double start, double end);

	@Query("from Employee where sal>=:start and sal<=:end")
	public List<Employee>loadEmpsBySalaryRange(double start, double end);

	@Query("from Employee where job in(:desg1,:desg2,:desg3)") //entity query
	public List<Employee>showEmpsByDesgs(String desg1, String desg2, String desg3);

	@Query("select empNo, empName,job from Employee where job in(:desg1,:desg2,:desg3)")
	public List<Object[]>showEmpsDataByDesg(String desg1, String desg2, String desg3);

	@Query("select empName from Employee where empName like :initChars")
	public List<String>showEmpNamesByNameChars(String initChars);

	@Query("from Employee where empName=:name")
	public Employee fetchEmpByName(String name);

	@Query("select empNo, empName, sal from Employee where empName=:name")
	public Object fetchEmpDataByName(String name);

	@Query("select sal from Employee where empName=:name")
	public Double fetchEmpSalaryByName(String name);

	@Query("select count(distinct empName) from Employee")
	public Integer showEmpsUniqueCount();

	@Query("select count(*), max(sal), min(sal), avg(sal), sum(sal) from Employee")
	public Object showArrgregateDate();

}
