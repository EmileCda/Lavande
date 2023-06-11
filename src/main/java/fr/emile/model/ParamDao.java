package fr.emile.model;

import org.hibernate.query.Query;

import fr.emile.common.IConstant;
import fr.emile.entity.Param;
import fr.emile.entity.User;
import fr.emile.utils.Utils;

public class ParamDao extends CrudDao implements IConstant {

	public ParamDao() {
		super(new Param());
		// TODO Auto-generated constructor stub
	}
	
	
	//-------------------------------------------------------------------------------------------------	
	public Param readByFunctionCode(int functionCode) {
		Param param = null;
		try {
			String  stringQuery = "FROM Param p WHERE p.functionKey = :functionKey";
			Query<Param> query = this.getSession().createQuery(stringQuery, Param.class);
			query.setParameter("functionKey", functionCode);
			param = query.uniqueResult(); 
		} catch (Exception e) {
			Utils.trace("catch  read(String email) %s \n", e.toString());

		} finally {
		}
		return param;
	}

}
