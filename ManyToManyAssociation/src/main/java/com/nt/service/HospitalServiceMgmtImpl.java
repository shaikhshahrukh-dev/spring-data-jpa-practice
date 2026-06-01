package com.nt.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.Doctor;
import com.nt.entity.Patient;
import com.nt.repository.IDoctorRepository;
import com.nt.repository.IPatientRepository;

@Service("hosService")
public class HospitalServiceMgmtImpl implements IHospitalService {

	@Autowired
	private IDoctorRepository docRepo;

	@Autowired
	private IPatientRepository patRepo;

	@Override
	public void saveDataUsingParent() {

		//prepare Doctor obj
		Doctor dr1 = new Doctor("Ilyas", "ngp");
		Doctor dr2 = new Doctor("Firoz","chikli");
		Doctor dr3 = new Doctor("Baba","mumbai");

		//prepare Patient obj
		Patient pa1 = new Patient("shahrukh","ngp");
		Patient pa2 = new Patient("sajjad", "ngp");
		Patient pa3 = new Patient("kanju","moda");
		Patient pa4 = new Patient("mumtaj","mumbai");
		Patient pa5 = new Patient("arif","newyork");
		Patient pa6 = new Patient("faizan","ngp");
		Patient pa7 = new Patient("muzammil","karnatak");
		Patient pa8 = new Patient("santosh","bihar");

		//assign patient to doctor
		dr1.getPatientInfo().add(pa1);
		dr1.getPatientInfo().add(pa4);
		dr1.getPatientInfo().add(pa7);
		dr2.getPatientInfo().add(pa8);
		dr2.getPatientInfo().add(pa7);
		dr3.getPatientInfo().add(pa3);
		dr3.getPatientInfo().add(pa2);
		dr3.getPatientInfo().add(pa4);
		dr3.getPatientInfo().add(pa5);
		dr3.getPatientInfo().add(pa1);
		dr2.getPatientInfo().add(pa6);


		//assign doctor to patient
		pa1.getDocterInfo().add(dr3);
		pa1.getDocterInfo().add(dr1);
		pa2.getDocterInfo().add(dr3);
		pa3.getDocterInfo().add(dr3);
		pa4.getDocterInfo().add(dr1);
		pa4.getDocterInfo().add(dr3);
		pa5.getDocterInfo().add(dr3);
		pa6.getDocterInfo().add(dr2);
		pa7.getDocterInfo().add(dr1);
		pa7.getDocterInfo().add(dr2);


		//save data using parent
		docRepo.save(dr1);
		docRepo.save(dr2);
		docRepo.save(dr3);

		System.out.println("Doctor and the associated patient are saved");
	}

	@Override
	public void loadDataUsingParent() {
		Iterable<Doctor> doctors=docRepo.findAll();
		doctors.forEach(doc->{
			System.out.println("Doctor::"+doc);
			Set<Patient>patients = doc.getPatientInfo();
			patients.forEach(pa->{
				System.out.println("Patients::"+pa);
			});
		});

	}

	@Override
	public void deleteDataUsingParent() {
		Optional<Doctor> opt= docRepo.findById(18);

		if(opt.isPresent())
		{
			Doctor doc=opt.get();
			Set<Patient> patients=doc.getPatientInfo();
			doc.setPatientInfo(null);
			patients.forEach(pa->{
				pa.setDocterInfo(null);
			});
			docRepo.save(doc);
			System.out.println("Doctor is removed from certain Patients");
		}
		else
		{
			System.out.println("Doctor not found");
		}


	}

}
