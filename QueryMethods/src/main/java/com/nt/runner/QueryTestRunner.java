package com.nt.runner;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nt.repository.IEmployeeRepository;

@Component
public class QueryTestRunner implements CommandLineRunner {

	@Autowired
	public IEmployeeRepository empRepo;

	@Override
	public void run(String... args) throws Exception {

		//empRepo.showAllEmployees().forEach(System.out::println);;
		//empRepo.showEmpsBySalaryRange(950.0, 5000.0).forEach(System.out::println);;

		//empRepo.loadEmpsBySalaryRange(950.0, 5000.0).forEach(System.out::println);
		//empRepo.showEmpsByDesgs("CLERK", "SALESMAN", null).forEach(System.out::println);

		/*empRepo.showEmpsDataByDesg("CLERK", null, null).forEach(row->{
			for(Object val : row)
				System.out.print(val+"   ");

			System.out.println();

		});*/

		//empRepo.showEmpNamesByNameChars("S%").forEach(System.out::println);;

		//System.out.println(empRepo.fetchEmpByName("Shahrukh"));

		/*Object data = empRepo.fetchEmpDataByName("Shahrukh");
		Object row[]= (Object[])data;
		System.out.println("Single record with multiple values "+Arrays.toString(row));*/


		//System.out.println("Single record single value "+empRepo.fetchEmpSalaryByName("Shahrukh"));
		//System.out.println(" showEmpsUniqueCount "+empRepo.showEmpsUniqueCount());;

		Object data = empRepo.showArrgregateDate();
		Object row[] = (Object[])data;
		System.out.println(Arrays.toString(row));
	}

}
