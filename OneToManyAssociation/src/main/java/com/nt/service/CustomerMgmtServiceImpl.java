package com.nt.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.Customer;
import com.nt.entity.PhoneNumber;
import com.nt.repository.ICustomerRepository;
import com.nt.repository.IPhoneNumberRepository;

@Service("cusService")
public class CustomerMgmtServiceImpl implements ICustomerMgmtService {

	@Autowired
	private ICustomerRepository custRepo;

	@Autowired
	private IPhoneNumberRepository phoneRepo;


	@Override
	public String saveDataUsingParent() {

		//create parent object
		Customer cust = new Customer("nasir","chikli");

		//create child object
		PhoneNumber ph1 = new PhoneNumber(9954861999L,"airtel","residece");
		PhoneNumber ph2 = new PhoneNumber(8865488888L,"jio","office");

		//set parent to child
		ph1.setCust(cust);
		ph2.setCust(cust);

		//set child to parent
		Set<PhoneNumber>child=Set.of(ph1,ph2);
		cust.setPhoneInfo(child);


		//save parent to childs
		int idVal = custRepo.save(cust).getCno();


		return "Parent to childs objs are save with the id value : "+idVal;
	}


	@Override
	public String saveDataUsingChilds() {
		//create parent object
				Customer cust = new Customer("shahrukh","nagpur");

				//create child object
				PhoneNumber ph1 = new PhoneNumber(7777777777L,"airtel","residece");
				PhoneNumber ph2 = new PhoneNumber(5555555555L,"jio","office");

				//set parent to child
				ph1.setCust(cust);
				ph2.setCust(cust);

				//set child to parent
				Set<PhoneNumber>child=Set.of(ph1,ph2);
				cust.setPhoneInfo(child);

				phoneRepo.saveAll(child);
		return "childs to parent objs are saved";
	}


	@Override
	public void deleteRecordById() {

		//load parent obj
		Optional<Customer> opt=custRepo.findById(1000);

		if(opt.isPresent())
		{
			//get customer object
			Customer cus = opt.get();

			//delete customer record
			custRepo.delete(cus);
			System.out.println("Customer and his PhoneNumber are deleted");
		}
		else
		{
			System.out.println("Customer are not found");
		}
	}

	@Override
	public void deleteOnlyChildOfParent() {
		//load the parent object
		Optional<Customer>opt =custRepo.findById(1002);

		if(opt.isPresent())
		{
			//get customer object
			Customer cust = opt.get();

			//get childs of a parents
			Set<PhoneNumber>childs= cust.getPhoneInfo();

			childs.forEach(ph->{
				ph.setCust(null);
			});

			//delete all the childs
			phoneRepo.deleteAll(childs);

			System.out.println("All childs are deleted");
		}
		else
		{
			System.out.println("customer are not found");
		}

	}


	@Override
	public void addNewchildToExitingChild() {

		Optional<Customer> opt=custRepo.findById(1002);

		if(opt.isPresent())
		{
			Customer cus =opt.get();
			Set<PhoneNumber>ph = cus.getPhoneInfo();

			PhoneNumber newPh = new PhoneNumber(22222222L,"VI","office");
			newPh.setCust(cus);
			ph.add(newPh);

			cus.setPhoneInfo(ph);

			custRepo.save(cus);
			System.out.println("New child record save to exiting child record");
		}
		else
		{
			System.out.println("record not found");
		}


	}


	@Override
	public void deleteDataUsingChild() {

		Optional<Customer>  opt=custRepo.findById(1002);

		if(opt.isPresent())
		{
			Set<PhoneNumber> ph =opt.get().getPhoneInfo();

			//1st method to delete the records but in this method multiple delete query generated
			ph.forEach(ph1->{
				phoneRepo.delete(ph1);
			});


			System.out.println("parent record deleted using childs records");
		}
		else
		{
			System.out.println("parent record are not found");
		}



	}


	@Override
	public List<Object[]> showParentToChildsDataUsingJoins() {
		//use repo
		return custRepo.fetchParentToChildsDataUsingJoins();
	}

}
