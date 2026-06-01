package com.nt.runner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nt.entity.Artist;
import com.nt.service.IArtistMgmtService;

@Component
public class JpaRepositoryTestRunner implements CommandLineRunner {



	@Autowired
	private IArtistMgmtService artistService;


	@Override
	public void run(String... args) throws Exception {

		Scanner sc = new Scanner(System.in);

		while(true)
		{
			System.out.println("Main Menu");
			System.out.println("Press 1 for delete Artist By Ind In Batch");
			System.out.println("Press 2 for search Artist by Given Data");
			System.out.println("press 3 for fetchArtistById");
			System.out.println("press 4 for loadArtistById()");
			System.out.println("Press 5 for exit app");
			System.out.println("Select the operation");
			int option = sc.nextInt();
			sc.nextLine();
			//this block is for choose the which method should run
			switch(option)
			{
				case 1:
					deleteArtistByIndInBatch();
					break;

				case 2:
					searchArtistbyGivenData();

				case 3:
					fetchArtistById();

				case 4:
					loadArtistById();

				case 5:
					System.out.println("Exiting Application");
					return; //exits run() method
			}
		}
	}

	private void deleteArtistByIndInBatch()
	{
		String msg = artistService.RemoveArtistByIdsInBatch(Arrays.asList(1034,1036));
		System.out.println(msg);
	}

	////search Artist by Given Data
	private void searchArtistbyGivenData()
	{
		try
		{
			List<Artist> list=artistService.searchArtistByGevenData("Shahrukh2", null);
			list.forEach(System.out::println);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}


	}

	private void fetchArtistById()
	{
		try
		{
			Optional opt=artistService.fetchArtistById(1042);

			if(opt.isEmpty())
			{
				System.out.println("Artist Not Found");
			}
			else
			{
				System.out.println("Artist Found");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void loadArtistById()
	{
		try
		{
			Artist artist = artistService.loadArtistById(1042);
			System.out.println(artist.getAid());
			System.out.println(artist.getAname());

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


}

