package param;

import fr.lotus.entity.Param;
import fr.lotus.entity.User;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.Utils;

public class TParamDelete {
	public static void main(String[] args) {
		
		Utils.trace("*************************** Begin ************************************");
		
		int rowId = 1;
		Param param = new Param();
		ICrudDao paramDao = new CrudDao(param);
		try {
			param = (Param) paramDao.read(rowId);
			if (param == null) 
				Utils.trace("Error : l'adresse n'existe pas");
			else {
				paramDao.delete(param );
				param = (Param) paramDao.read(rowId);
				if (param != null)
					Utils.trace("Error not remove");
				else
					Utils.trace("remove ok");
			}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}finally {
			paramDao.close();
		}
		
		Utils.trace("*************************** end ************************************");
		
	}
}
