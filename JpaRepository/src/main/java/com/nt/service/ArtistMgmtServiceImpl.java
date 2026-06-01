package com.nt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.nt.entity.Artist;
import com.nt.repository.IArtistRepository;

@Service("artistService")
public class ArtistMgmtServiceImpl implements IArtistMgmtService {

	@Autowired
	private IArtistRepository artistRepo;

	@Override
	public String RemoveArtistByIdsInBatch(Iterable<Integer> ids) {

		//load the object by ids
		List<Artist>list=artistRepo.findAllById(ids);
		Iterable<Artist>art2=artistRepo.findAllById(ids);
		List<String>idAndNames = new ArrayList<String>();

		for(Artist art3 : art2)
		{
			idAndNames.add("Id :: "+art3.getAid()+" "+"Name :: "+art3.getAname());
		}

		//delete the records by ids
		artistRepo.deleteAllByIdInBatch(ids);

		return list.size()+" Records are deleted and ids and name are "+idAndNames;
	}

	@Override
	public List<Artist> searchArtistByGevenData(String name, Double fee) {
		Artist artist = new Artist();
		artist.setAname(name);
		artist.setFee(fee);

		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();

		//prepare example object
		Example<Artist> example = Example.of(artist);

		//get the records based on conditions
		List<Artist> records=artistRepo.findAll(example);
		return records;
	}

	@Override
	public Optional<Artist> fetchArtistById(int id) {

		return artistRepo.findById(id);
	}

	@Override
	public Artist loadArtistById(int id) {
		return artistRepo.getReferenceById(id);
	}







}//class
