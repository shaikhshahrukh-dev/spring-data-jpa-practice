package com.nt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.entity.Customer;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
	//@Query("select c.cno,c.cname,c.caddrs,p.regNo,p.mobileNo,p.provider,p.NumberType from Customer c inner join c.phoneInfo p")
	//@Query("select c.cno,c.cname,c.caddrs,p.regNo,p.mobileNo,p.provider,p.NumberType from Customer c right join c.phoneInfo p")
	//@Query("select c.cno,c.cname,c.caddrs,p.regNo,p.mobileNo,p.provider,p.NumberType from Customer c left join c.phoneInfo p")
	//@Query("select c.cno,c.cname,c.caddrs,p.regNo,p.mobileNo,p.provider,p.NumberType from Customer c full join c.phoneInfo p")
	@Query(value = "SELECT c.cno,c.cname,c.caddrs,p.reg_no,p.mobile_no,p.provider,p.number_type FROM JPA_OTM_CUSTOMER c LEFT JOIN JPA_OTM_PHONES p ON c.cno = p.customer_id "
		   + "UNION SELECT c.cno,c.cname,c.caddrs,p.reg_no,p.mobile_no,p.provider,p.number_type FROM JPA_OTM_CUSTOMER c RIGHT JOIN JPA_OTM_PHONES p ON c.cno = p.customer_id",
					nativeQuery = true)
	public List<Object[]> fetchParentToChildsDataUsingJoins();


}
