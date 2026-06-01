package com.nt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nt.BootJpaProj01CrudRepositoryApplication;
import com.nt.entity.Artist;
import com.nt.repository.IArtistRepository;

@Service("artistService")
public class ArtistMgmtServiceImpl implements IArtistMgmtService {

    private final BootJpaProj01CrudRepositoryApplication bootJpaProj01CrudRepositoryApplication;

	@Autowired
	private IArtistRepository artistRepo;

    ArtistMgmtServiceImpl(BootJpaProj01CrudRepositoryApplication bootJpaProj01CrudRepositoryApplication) {
        this.bootJpaProj01CrudRepositoryApplication = bootJpaProj01CrudRepositoryApplication;
    }

	@Override
	public String registerArtist(Artist artist) {
		//use repo
		Artist artist1 = artistRepo.save(artist);
		return artist.getAid()==null?"Artist Not Register(Record Not Save)":"Artist Register(Record save)";
	}

	@Override
	public boolean checkArtistAvailability(int id) {
		boolean flag = artistRepo.existsById(id);
		return flag;
	}

	@Override
	public long showArtistCount() {
		long count = artistRepo.count();
		return count;
	}

	//there are 3 methods methods
	/*@Override
	public String registerArtistBatch(List<Artist> list) {
		Iterable<Artist> savedList = artistRepo.saveAll(list);
		List<Integer>idsList = new ArrayList();
		savedList.forEach(artist->{
			idsList.add(artist.getAid());
		});
		return idsList.size()+"no.of artist are registered having the idValues "+idsList.toString();
	}*/

	/*@Override
	public String registerArtistBatch(List<Artist> list) {

		//save the objs
		List<Artist>saveList = (List<Artist>) artistRepo.saveAll(list);

		List<Integer>idList = saveList.stream().map(art->art.getAid()).collect(Collectors.toList());

		return idList.size()+" no of Artist are save with the id vlause :: "+idList;
	}*/

	@Override
	public String registerArtistBatch(List<Artist> list) {

		//save the objs
		Iterable<Artist> saveList = artistRepo.saveAll(list);
		List<Integer>ids = StreamSupport.stream(saveList.spliterator(), false).map(Artist::getAid).collect(Collectors.toList());
		return ids.size()+" no of Artist are register with the id values :: "+ids;
	}

	//show all Artist records
	@Override
	public List<Artist> showAllArtist() {
		List<Artist>list=(List<Artist>) artistRepo.findAll();
		return list;
	}

	//Get records by ids
	@Override
	public Iterable<Artist> getRecordsByIds(Iterable<Integer> idList) {
		Iterable<Artist>list=artistRepo.findAllById(idList);
		return list;
	}

	//get single record by id
	@Override
	public Optional<Artist> showArtistById(int id) {
		Optional<Artist>opt = artistRepo.findById(id);

		return opt;
	}


	@Override
	public Artist fetchArtistById(int id) {
		/*Optional<Artist>opt=artistRepo.findById(id);
		if(opt.isPresent())
		{
			return opt.get();
		}
		else
		{
			throw new IllegalArgumentException("invalid id");
		}*/

		return artistRepo.findById(id).orElseThrow(()->new IllegalArgumentException("invalid id"));

	}

	//register or update Artist
	@Override
	public String registerOrUpdateArtist(Artist artist) {
		//save or update

		boolean exits = artistRepo.existsById(artist.getAid());
		if(exits)
		{
			artistRepo.save(artist);
			return artist.getAname()+ " Artist is updated";
		}
		else
		{
			artist.setAid(null);
			artistRepo.save(artist);
			return artist.getAname()+" Artist is register";
		}


	}

	@Override
	public String hikeFeeByIdAndPercentage(int id, double percentage)
	{
		Optional<Artist> opt =artistRepo.findById(id);
		if(opt.isPresent())
		{
			Artist artist=opt.get();
			artist.setFee(artist.getFee()+(artist.getFee()*percentage/100.0f));
			artistRepo.save(artist);
			return "Artist is found and fee modified";
		}
		else
		{
			return "Artist is not found for Modification";
		}
	}

	@Override
	public String deleteArtistById(int id) {
		Optional<Artist>opt=artistRepo.findById(id);
		if(opt.isPresent())
		{
			artistRepo.deleteById(id);
			return "Artist Record Deleted";
		}
		else
		{
			return "Artist Record not found ";
		}
	}

	@Override
	public String deleteAllArtist() {
		long count = artistRepo.count();

		if(count>0)
		{
			artistRepo.deleteAll();
			return count+" No. of Artist deleted";
		}
		else
		{
			return "There is no Artist Records are found";
		}
	}

	@Override
	public String deleteRecordByIds(Iterable<Integer> ids) {
		Iterable<Artist>artistList = artistRepo.findAllById(ids);
		List<Integer>idsFound = new ArrayList<Integer>();
		artistList.forEach(artist->idsFound.add(artist.getAid()));
		long count1 = idsFound.size();
		System.out.println("Ids given by user "+ids);
		if(count1>0)
		{
			artistRepo.deleteAllById(ids);
			long count = Stream.of(artistList).count();
			return ids+" given by the user "+"  "+idsFound+" ids Found In records"+"  "+count+" total records deleted";
		}
		else
		{
			return "no records are Found";
		}

	}

}
