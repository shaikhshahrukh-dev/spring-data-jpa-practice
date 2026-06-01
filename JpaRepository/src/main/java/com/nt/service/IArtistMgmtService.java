package com.nt.service;

import java.util.List;
import java.util.Optional;

import com.nt.entity.Artist;

public interface IArtistMgmtService {

	//Delete Records by Ids in batch
	public String RemoveArtistByIdsInBatch(Iterable<Integer>ids);

	//search Artist by Given Data
	public List<Artist> searchArtistByGevenData(String name, Double fee);

	public Optional<Artist> fetchArtistById(int id);

	public Artist loadArtistById(int id);

}
