package param;

import java.util.ArrayList;
import java.util.List;

import fr.lotus.entity.Address;
import fr.lotus.entity.Param;
import fr.lotus.entity.Param;
import fr.lotus.entity.Param;
import fr.lotus.model.implement.ClassDao;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.DataTest;
import fr.lotus.utils.Utils;

public class TParamCreate {
	public static void main(String[] args) {
		int paramId = 1;
		Param param = new Param() ;
		ICrudDao paramDao = new CrudDao(param);
		try {
			param = (Param) paramDao.read(paramId);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			paramDao.close();
		}
		if (param!=null)
			Utils.trace(param.toString());
		else 
			Utils.trace("param null");
		
	}
}
