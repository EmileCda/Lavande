package param;

import fr.lotus.entity.Param;
import fr.lotus.entity.Param;
import fr.lotus.entity.User;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.Utils;

public class TParamUpdate {
	public static void main(String[] args) {
		
		Utils.trace("*************************** Begin ************************************");

		int rowId = 2;
		Param param =  new Param();
		ICrudDao paramDao = new CrudDao(param) ;
		try {
			param = (Param) paramDao.read(rowId );
			if (param == null )
				Utils.trace("Param null ");
			else {
				Utils.trace("Before" + param.toString());

				// -------------------------- update ----------------------
				param.setIntValue(0);
				paramDao.update(param);
	
				param = (Param) paramDao.read(rowId);
				if (param != null )
					Utils.trace("After" + param.toString());
				else
					Utils.trace("Param null ");
			}

		} catch (

				Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}finally {
			paramDao.close();
		}
		
		
		
		
		
		
		Utils.trace("*************************** end ************************************");
		
	}
}
