package com.nt.runner;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nt.service.ICustomerMgmtService;

@Component
public class CustomerTestRunner implements CommandLineRunner {

	@Autowired
	private ICustomerMgmtService custService;

	@Override
	public void run(String... args) throws Exception {

		try
		{
			/*String msg = custService.saveDataUsingParent();
			System.out.println(msg);*/

			/*String msg = custService.saveDataUsingChilds();
			System.out.println(msg);*/

			//custService.deleteRecordById();

			//custService.deleteOnlyChildOfParent();

			//custService.addNewchildToExitingChild();

			//custService.deleteDataUsingChild();

			custService.showParentToChildsDataUsingJoins().forEach(row->{
				System.out.println(Arrays.toString(row));
			});
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}
