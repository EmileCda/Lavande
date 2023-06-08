package param;

import java.util.ArrayList;
import java.util.List;

import fr.lotus.entity.Param;
import fr.lotus.entity.Param;
import fr.lotus.entity.User;
import fr.lotus.model.implement.ClassDao;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.Utils;

public class TParamRead {
	public static void main(String[] args) {
	Utils.trace("*************************** Begin ************************************");
	readMany();
	readOne();
	Utils.trace("*************************** end ************************************");
	
}
//-------------------------------------------------------------------------------------------------	
public static void readMany() {
	Utils.trace("=========================== read many  ===========================");

	List<ClassDao> paramList = new ArrayList<ClassDao>() ;
	Param param = new Param() ;
	ICrudDao paramDao = new CrudDao(param);
	try {
		paramList = paramDao.read();
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		paramDao.close();
	}
	if ((paramList.size() >0  ) && (paramList != null)) {
		for (ClassDao classDao : paramList) {
			param = (Param)classDao ; 
			Utils.trace(param.toString()); 
		}
	}
	else
		Utils.trace(" paramList vide");
}
//-------------------------------------------------------------------------------------------------	
public static void readOne() {
	Utils.trace("=========================== read One  ===========================");
	int paramId = 3;
	Param param = new Param() ;
	ICrudDao paramDao = new CrudDao(param);
	try {
		param = (Param) paramDao.read(paramId);
	} catch (Exception e) {
		e.printStackTrace();
	}
	if (param != null )
		Utils.trace(param.toString());
	else 
		Utils.trace("param null");
	
}
}
