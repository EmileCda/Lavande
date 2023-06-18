package fr.emile.model;

import java.util.List;
import org.hibernate.query.Query;
import fr.emile.utils.Utils;

public class CrudDao extends UtilDao  {

	private Object currentObject;

	public CrudDao(Object currentObject) {
		this.setCurrentObject(currentObject);

	}

//-------------------------------------------------------------------------------------------------
	public Object create(Object myObject) throws Exception {
		try {
			beginTransaction();
			this.getSession().save(myObject);
			commit();
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());
			rollBack();
		}
		return myObject;
	}
//-------------------------------------------------------------------------------------------------
	public Object read(int id) throws Exception {
		Object myObject = null;
		try {
			myObject = (Object) this.getSession().find(this.getCurrentObject().getClass(), id);
		} catch (Exception e) {
			Utils.trace("catch read %s\n", e.toString());
		}
		return myObject;
	}

//-------------------------------------------------------------------------------------------------
	public List<Object> list() throws Exception {
		List<Object> objectList = null;
		try {
			String className = this.getCurrentObject().getClass().getSimpleName();
			String querryString = "from " + className;
			@SuppressWarnings("unchecked")
			Query<Object> myQuery = this.getSession().createQuery(querryString);
			objectList = myQuery.list();
		} catch (Exception e) {
			Utils.trace("catch list%s\n", e.toString());
		}
		return objectList;
	}

//-------------------------------------------------------------------------------------------------
	public int update(Object myObject) throws Exception {
		int result = 0 ; 
		try {
			beginTransaction();

			this.getSession().update(myObject);
			result +=1;
			commit();

		} catch (Exception e) {

			Utils.trace("catch update %s\n", e.toString());
			rollBack();

		}
		return result ; 
	}

//-------------------------------------------------------------------------------------------------
	public int delete(Object myObject) throws Exception {
		try {
			beginTransaction();

			this.getSession().remove(myObject);
			commit();

		} catch (Exception e) {

			Utils.trace("catch delete%s\n", e.toString());
			rollBack();

		}
		return 0 ; 
	}

//-------------------------------------------------------------------------------------------------

	public Object getCurrentObject() {
		return currentObject;
	}

	public void setCurrentObject(Object currentObject) {
		this.currentObject = currentObject;
	}

	

//-------------------------------------------------------------------------------------------------

}
