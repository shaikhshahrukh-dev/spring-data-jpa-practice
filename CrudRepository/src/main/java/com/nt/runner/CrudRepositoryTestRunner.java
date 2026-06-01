package com.nt.runner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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
			System.out.println("press 1 for Register Artist");
			System.out.println("press 2 for Check Artist Availability");
			System.out.println("press 3 for Artist Count");
			System.out.println("press 4 for Builk Inser records");
			System.out.println("press 5 for show All records");
			System.out.println("press 6 for Get Records by ids");
			System.out.println("press 7 for Get single Record by id");
			System.out.println("press 8 for get single record by id 2nd method");
			System.out.println("press 9 to registerOrUpdateArtist");
			System.out.println("press 10 for hike Fee By Id And Percentage");
			System.out.println("press 11 to delete Artist Record");
			System.out.println("Press 12 to delete all records");
			System.out.println("press 13 for delete records by Ids");
			System.out.println("press 14 Exit the Application");


			System.out.println("Select the operation");
			int option = sc.nextInt();
			sc.nextLine();
			//this block is for choose the which method should run
			switch(option)
			{
				case 1:
					registerArtist(sc);
					break;

				case 2:
					checkArtistAvailability(sc);
					break;

				case 3:
					artistCount();
					break;

				case 4:
					isertBulkArtist();
					break;

				case 5:
					showAllArtist();
					break;

				case 6:
					getAllRecordsByIds();
					break;

				case 7:
					getArtistById();
					break;

				case 8:
					fatchRecordById();
					break;

				case 9:
					registerOrUpdateArtist();
					break;

				case 10:
					hikeFeeByIdAndPercentage();
					break;

				case 11:
					deleteArtistById();
					break;

				case 12:
					deleteAllRecords();
					break;

				case 13:
					deleteRecordByIds();
					break;

				case 14:
					System.out.println("Exiting Application");
					return; //exits run() method

			}
		}
	}

			//method for register Artist
	private void registerArtist(Scanner sc)
	{
		System.out.println("Artist Name");
		String ArtistName=sc.next();

		System.out.println("Category");
		String category = sc.next();

		System.out.println("fee");
		Double fee = sc.nextDouble();

		Artist artist = new Artist(ArtistName, category, fee);

		// invoke the b.method
		try
		{
			String msg=artistService.registerArtist(artist);
			System.out.println(msg);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
	//method for Check Artist Availability
	private void checkArtistAvailability(Scanner sc)
	{
		System.out.println("Enter Artist Id");
		int ID = sc.nextInt();

		try
		{
			String b =artistService.checkArtistAvailability(ID)?"Artist Available":"Artist Not Available";
			System.out.println(b);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
	//method for Artist Count
	private void artistCount()
	{
		System.out.println("Total Artist = "+artistService.showArtistCount());
	}

	private void isertBulkArtist()
	{
		Artist artist1 = new Artist("Shahrukh2","Hero",560000.0);
		Artist artist2 = new Artist("Akshy khanna","Hero",560000.0);
		Artist artist3 = new Artist("Aliya Bhatt","Heroin",560000.0);
		Artist artist4 = new Artist("Anil kapur","Hero",2340000.0);
		Artist artist5 = new Artist("Aswariya Roy","Heroin",620000.0);
		Artist artist6 = new Artist("Radhika Apte","Heroin",65760000.0);
		Artist artist7 = new Artist("Kajol","Heroin",54360000.0);
		Artist artist8 = new Artist("Raja","Hero",346000.0);
		Artist artist9 = new Artist("Ranvir Kapur","Hero",57650000.0);

		List<Artist>list = List.of(artist1,artist2,artist3,artist4,artist5,artist6,artist7,artist8,artist9);
		artistService.registerArtistBatch(list);

		try
		{
			String msg=artistService.registerArtistBatch(list);
			System.out.println(msg);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void showAllArtist()
	{
		try
		{
			Iterable<Artist>list=artistService.showAllArtist();
			list.forEach(art->{
				System.out.println(art);
			});

			System.out.println("------------------------------------");

			list.forEach(art->System.out.println(art));

			System.out.println("------------------------------------");

			list.forEach(System.out::println);

			System.out.println("------------------------------------");

			for(Artist art:list)
			{
				System.out.println(art);
			}

			System.out.println("------------------------------------");

			Stream.of(list).forEach(System.out::println);

			System.out.println("------------------------------------");

			Stream.of(list).forEachOrdered(System.out::println);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void getAllRecordsByIds()
	{

		try
		{
			List<Integer>idsList = new ArrayList();
			idsList.add(1000); idsList.add(15); idsList.add(1041); idsList.add(1046); idsList.add(52);
			artistService.getRecordsByIds(idsList).forEach(System.out::println);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	//get single record by id
	private void getArtistById()
	{
		try
		{
			Optional<Artist>opt=artistService.showArtistById(1007);

			if(opt.isPresent())
			{
				Artist artist = opt.get();
				System.out.println("Artist Info :: "+artist);
			}
			else
				System.out.println("Artist not found");

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	private void fatchRecordById()
	{

		try
		{
			Artist artist = artistService.fetchArtistById(1003);
			System.out.println(artist);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void registerOrUpdateArtist()
	{
		try
		{
			//Artist artist = new Artist(105,"MySelf","Super_Villan",68786.0);
			Artist artist = new Artist(1011, "raja", "Super_villain", 89000.0);
			String msg=artistService.registerOrUpdateArtist(artist);
			System.out.println(msg);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
	//hike Fee By Id And Percentage
	private void hikeFeeByIdAndPercentage()
	{
		String msg = artistService.hikeFeeByIdAndPercentage(1012, 20);
		System.out.println(msg);
	}


	//delete Artist record by id
	private void deleteArtistById()
	{
		try
		{
			String msg =artistService.deleteArtistById(1002);
			System.out.println(msg);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	//delete all Artist records
	private void deleteAllRecords()
	{
		try
		{
			String msg=artistService.deleteAllArtist();
			System.out.println(msg);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	//Delete records by Ids
	private void deleteRecordByIds()
	{
		Iterable<Integer>idsList = Arrays.asList(101,102,205,1029,1028,1025,1031);
		String msg= artistService.deleteRecordByIds(idsList);
		System.out.println(msg);
	}

}

