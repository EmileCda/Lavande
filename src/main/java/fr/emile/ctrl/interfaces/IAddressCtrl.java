package fr.emile.ctrl.interfaces;

import java.util.List;

import fr.emile.entity.Address;

public interface IAddressCtrl {
	
	public Address add (Address address) throws Exception ;
	public Address get(int id) throws Exception;
	public Address get(String email) throws Exception;
	public List<Address> list() throws Exception;
	public int  update(Address address) throws Exception; 
	public int delete (Address address) throws Exception ;

}
