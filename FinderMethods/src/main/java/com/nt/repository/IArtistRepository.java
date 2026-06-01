package com.nt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.Artist;

public interface IArtistRepository extends JpaRepository<Artist, Integer>{


	public List<Artist>findByAnameEquals(String name);

	public List<Artist>findByCategoryEquals(String category);
	public List<Artist>readByCategoryIs(String category);

	public List<Artist>findByAnameStartingWith(String initChars);


	public List<Artist>findByAnameEndingWith(String lastChars);


	public List<Artist>findByAnameContaining(String chars);

	public List<Artist>findByFeeBetween(double start, double end);

	public List<Artist>findByCategoryIn(List<String> category);
}
