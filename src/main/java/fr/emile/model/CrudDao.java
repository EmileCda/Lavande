package fr.lotus.model.implement;

import java.util.List;
import org.hibernate.query.Query;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.Utils;

public class CrudDao extends UtilDao implements ICrudDao {

	private Object currentObject;

	public CrudDao(Object currentObject) {
		this.setCurrentObject(currentObject);

	}

//-------------------------------------------------------------------------------------------------
	@Override
	public ClassDao create(ClassDao myDao) throws Exception {
		try {
			beginTransaction();
			myDao.preWrite();
			this.getSession().save(myDao);
			commit();
		} catch (Exception e) {
			Utils.trace("catch create " + e.toString());
			rollBack();
		}
		return myDao;
	}
//-------------------------------------------------------------------------------------------------
	@Override
	public ClassDao read(int id) throws Exception {
		ClassDao myDao = null;
		try {
			myDao = (ClassDao) this.getSession().find(this.getCurrentObject().getClass(), id);
			if (myDao != null)
				myDao.postRead();
		} catch (Exception e) {
			Utils.trace("catch Read " + e.toString());
		}
		return myDao;
	}

//-------------------------------------------------------------------------------------------------
	@Override
	public List<ClassDao> read() throws Exception {
		List<ClassDao> objectList = null;
		try {
			String className = this.getCurrentObject().getClass().getSimpleName();
			String querryString = "from " + className;
			@SuppressWarnings("unchecked")
			Query<ClassDao> myQuery = this.getSession().createQuery(querryString);
			objectList = myQuery.list();
			for (ClassDao oneDao : objectList) {
				if (oneDao != null)
					oneDao.postRead();
			}
		} catch (Exception e) {
			Utils.trace("catch Read " + e.toString());
		}
		return objectList;
	}

//-------------------------------------------------------------------------------------------------
	@Override
	public void update(ClassDao myDao) throws Exception {
		try {
			beginTransaction();

			myDao.preWrite();
			this.getSession().update(myDao);
			commit();

		} catch (Exception e) {

			Utils.trace("catch update " + e.toString());
			rollBack();

		}
	}

//-------------------------------------------------------------------------------------------------
	@Override
	public void delete(ClassDao myDao) throws Exception {
		try {
			beginTransaction();

			this.getSession().remove(myDao);
			commit();

		} catch (Exception e) {

			Utils.trace("catch update " + e.toString());
			rollBack();

		}
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
