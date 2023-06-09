package fr.emile.ctrl;

import java.util.ArrayList;
import java.util.List;

import fr.emile.common.IConstant;
import fr.emile.model.CrudDao;
import fr.emile.utils.Utils;


public abstract  class CrudCtrl implements IConstant {

	private Object currentObject;
	
	
	protected abstract Object preWrite(Object myObject) ;
	protected abstract Object postWrite(Object myObject);
	protected abstract Object postRead(Object myObject);

	public CrudCtrl(Object currentObject) {
		this.setCurrentObject(currentObject);

	}

	public Object create(Object myObject) throws Exception {
		
		CrudDao crudDao = new CrudDao(currentObject);
		myObject = preWrite(myObject);
		crudDao.create(myObject);
		myObject = postWrite(myObject);
		return myObject;
	}
	
	public Object  read(int id) throws Exception {
		Object myObject ; 
		CrudDao crudDao = new CrudDao(currentObject);
		myObject= crudDao.read(id);
		myObject = postRead(myObject);
		return myObject;
	}

	public List<Object> list() throws Exception {

		List<Object> myObjectList ;  
		List<Object> myObjectListReturn= null ;  
		CrudDao crudDao = new CrudDao(currentObject);
		myObjectList=crudDao.list();
		
		if (myObjectList.size()>0) {
			myObjectListReturn = new ArrayList<Object>(); 
			for (Object myObject : myObjectList) {
				myObject = postRead(myObject);
				myObjectListReturn.add(myObject);
			}
		}
		return myObjectListReturn;
		
	}

	public int update(Object myObject) throws Exception {

		CrudDao crudDao = new CrudDao(currentObject);
		myObject = preWrite(myObject);
		int nbRecord =crudDao.update(myObject );
		return nbRecord;
	}

	public int delete(Object myObject) throws Exception {
		CrudDao crudDao = new CrudDao(currentObject);
		myObject = preWrite(myObject);
		int nbRecord =crudDao.delete(myObject );
		return nbRecord;
	}

	public Object getCurrentObject() {
		return currentObject;
	}

	public void setCurrentObject(Object currentObject) {
		this.currentObject = currentObject;
	}

}
