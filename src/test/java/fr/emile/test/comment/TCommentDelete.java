package comment;

import fr.lotus.entity.Comment;
import fr.lotus.entity.Comment;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.Utils;

public class TCommentDelete {
	public static void main(String[] args) {
		
		Utils.trace("*************************** Begin ************************************\n");
		
		int rowId =  1;
		Comment comment = new Comment();
		ICrudDao commentDao = new CrudDao(comment);
		try {
			comment = (Comment) commentDao.read(rowId);
			if (comment == null) 
				Utils.trace("Error : Comment n'existe pas\n");
			else {
				Utils.trace("last time seen: %s\n",comment);
				commentDao.delete(comment );
				comment = (Comment) commentDao.read(rowId);
				if (comment != null)	
					Utils.trace("Error : not remove\n");
				else
					Utils.trace("remove ok\n");
			}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}finally {
			commentDao.close();
		}
		
		Utils.trace("*************************** end ************************************\n");
		
	}
}
