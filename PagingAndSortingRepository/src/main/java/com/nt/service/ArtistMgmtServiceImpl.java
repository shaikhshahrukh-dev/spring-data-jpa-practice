package com.nt.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nt.entity.Artist;
import com.nt.repository.IArtistRepository;

@Service("artistService")
public class ArtistMgmtServiceImpl implements IArtistMgmtService {



	@Autowired
	private IArtistRepository artistRepo;

	//for sorting artist records
	@Override
	public Iterable<Artist> showArtistBySorting(boolean ascOrder, String... props) {

		//prepare the sort object
		Sort sort=Sort.by(ascOrder?Direction.ASC:Direction.DESC,props);

		//invoke the method
		return artistRepo.findAll(sort);
	}

	@Override
	public Page<Artist> showArtistByPageNo(int pageNo, int pageSize) {

		//prepare pageable object having inputs
		Pageable pageable = PageRequest.of(pageNo, pageSize);

		//call the b.method
		Page<Artist> page = artistRepo.findAll(pageable);

		return page;
	}

	@Override
	public void showArtistPageByPage(int pageSize, boolean ascOrder, String... props) {

		//get record count
		long count = artistRepo.count();

		//get no of pages
		long pageCount = count/pageSize;

		if(count%pageSize!=0)
			pageCount=++pageCount;

		//prepare sort object
		Sort sort=Sort.by(ascOrder?Direction.ASC:Direction.DESC, props);

		for(int i=0;i<pageCount;i++)
		{
			//prepare pageAble obj for every page
			Pageable pageable = PageRequest.of(i, pageSize, sort);

			//get each page record
			Page<Artist> page = artistRepo.findAll(pageable);

			//display each page record
			page.forEach(System.out::println);

			System.out.println("--------------------"+((page.getNumber()+1))+"/"+(page.getTotalPages())+"----------------------");
		}//for

	}//method

}//class
