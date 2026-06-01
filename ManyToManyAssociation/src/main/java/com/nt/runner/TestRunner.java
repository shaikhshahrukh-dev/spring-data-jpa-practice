package com.nt.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nt.service.IHospitalService;

@Component
public class TestRunner implements CommandLineRunner {

	@Autowired
	private IHospitalService hosService;
	@Override
	public void run(String... args) throws Exception {

		try
		{
			//hosService.saveDataUsingParent();
			//hosService.loadDataUsingParent();
			hosService.deleteDataUsingParent();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
