package com.nt.runner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nt.repository.IArtistRepository;


@Component
public class FinderMethodsTestRunner implements CommandLineRunner {

	@Autowired
	public IArtistRepository artistRepo;

	@Override
	public void run(String... args) throws Exception {

		//artistRepo.findByAnameEquals("shahrukh2").forEach(System.out::println);
		
		//artistRepo.findByCategoryEquals("villain").forEach(System.out::println);;

		//artistRepo.readByCategoryIs("hero").forEach(System.out::println);

		//artistRepo.findByAnameStartingWith("a").forEach(System.out::println);;

		//artistRepo.findByAnameEndingWith("l").forEach(System.out::println);

		//artistRepo.findByAnameContaining("o").forEach(System.out::println);

		artistRepo.findByFeeBetween(10000.0, 65760000).forEach(System.out::println);

		artistRepo.findByCategoryIn(List.of("villain","heroin")).forEach(System.out::println);;
	}


}

