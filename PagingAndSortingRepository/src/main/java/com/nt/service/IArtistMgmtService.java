package com.nt.service;

import org.springframework.data.domain.Page;

import com.nt.entity.Artist;

public interface IArtistMgmtService {

	//for Artist sorting
	public Iterable<Artist> showArtistBySorting(boolean ascOrder,String...props);

	//Show Artist by page no
	public Page<Artist> showArtistByPageNo(int pageNo, int pageSize);

	//show Artist page by page
	public void showArtistPageByPage(int pageSize, boolean ascOrder, String...props);
}
