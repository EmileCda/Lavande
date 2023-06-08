package fr.lotus.model.interfaces;

import java.util.List;

import fr.lotus.model.implement.ClassDao;

public interface ICrudDao {
	
	public ClassDao create (ClassDao myDao)  throws Exception;

	public ClassDao read(int id) throws Exception;
	public List<ClassDao> read() throws Exception;

	public void update(ClassDao myDao) throws Exception; 
	public void delete (ClassDao myDao)  throws Exception;
	
	public void close () ;

}
