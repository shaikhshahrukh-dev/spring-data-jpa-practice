package com.nt.runner;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.nt.entity.Artist;
import com.nt.service.IArtistMgmtService;

@Component
public class CrudRepositoryTestRunner implements CommandLineRunner {



	@Autowired
	private IArtistMgmtService artistService;


	@Override
	public void run(String... args) throws Exception {

		Scanner sc = new Scanner(System.in);

		while(true)
		{
			System.out.println("Main Menu");
			System.out.println("Press 1 for show Artist by sorting ");
			System.out.println("Press 2 for show artist by page no");
			System.out.println("press 3 for show artist page by page");
			System.out.println("Press 4 for exit app");


			System.out.println("Select the operation");
			int option = sc.nextInt();
			sc.nextLine();
			//this block is for choose the which method should run
			switch(option)
			{
				case 1:
					showArtistBySort();
					break;

				case 2:
					showArtistByPageNo();
					break;

				case 3:
					showArtistPageByPage();

				case 4:
					System.out.println("Exiting Application");
					return; //exits run() method

			}
		}
	}

	// show Artist by sorting
	private void showArtistBySort()
	{
		try
		{
			artistService.showArtistBySorting(true, "aname","aid").forEach(System.out::println);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}


	//Show Artist by Page NO
	private void showArtistByPageNo()
	{
		try
		{
			Page<Artist> page=artistService.showArtistByPageNo(0, 3);

			//get content of the page
			List<Artist>list = page.getContent();
			list.forEach(System.out::println);
			System.out.println("-----------------------------------");
			System.out.println("current page no:: "+(page.getNumber()+1));
			System.out.println("page size :: "+page.getSize());
			System.out.println("total no of pages :: "+page.getTotalPages());
			System.out.println("total no. of records on current page :: "+page.getNumberOfElements());
			System.out.println("is this page is first page :: "+page.isFirst());
			System.out.println("is this page is last page :: "+page.isLast());

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void showArtistPageByPage()
	{
		try
		{
			artistService.showArtistPageByPage(3, true, "aname");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

