package com.nt.service;

import java.util.List;
import java.util.Optional;

import com.nt.entity.Artist;

public interface IArtistMgmtService {
	//this method for Register Artist
	public String registerArtist(Artist artist);

	//this method for find Artist by id
	public boolean checkArtistAvailability(int id);

	//this method for counting total Artist
	public long showArtistCount();

	//for batch insert
	public String registerArtistBatch(List<Artist>list);

	//Get all records
	public List<Artist> showAllArtist();

	//get records by ids
	public Iterable<Artist> getRecordsByIds(Iterable<Integer>idList);

	//get records by id
	public Optional<Artist> showArtistById(int id);

	//get single recod by id
	public Artist fetchArtistById(int id);

	//register or update Artist
	public String registerOrUpdateArtist(Artist artist);

	//hike artist fee by ids and percentages
	public String hikeFeeByIdAndPercentage(int id, double percentage);

	//delete Artist by Id
	public String deleteArtistById(int id);

	//delete All Artist Records
	public String deleteAllArtist();

	//delete records
	public String deleteRecordByIds(Iterable<Integer>ids);

}
