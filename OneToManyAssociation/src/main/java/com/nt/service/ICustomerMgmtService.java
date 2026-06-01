package com.nt.service;

import java.util.List;

public interface ICustomerMgmtService {

	public String saveDataUsingParent();

	public String saveDataUsingChilds();

	public void deleteRecordById();

	public void deleteOnlyChildOfParent();

	public void addNewchildToExitingChild();

	public void deleteDataUsingChild();

	public List<Object[]> showParentToChildsDataUsingJoins();


}
