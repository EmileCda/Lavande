package comment;

import fr.lotus.entity.Comment;
import fr.lotus.entity.Comment;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.Utils;

public class TCommentUpdate {
	public static void main(String[] args) {
		
		Utils.trace("*************************** Begin ************************************\n");
		int rowId = 1;
		Comment comment =  new Comment();
		ICrudDao commentDao = new CrudDao(comment) ;
		try {
			comment = (Comment) commentDao.read(rowId );
			if (comment == null )
				Utils.trace("Comment null\n");
			else {
				Utils.trace("Before: %s\n",comment);

				// -------------------------- update ----------------------
				comment.setGrade(5);
				commentDao.update(comment);
	
				comment = (Comment) commentDao.read(rowId);
				if (comment != null )
					Utils.trace("After %s\n",comment);
				else
					Utils.trace("Comment null\n");
			}

		} catch (

				Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}finally {
			commentDao.close();
		}
		
		
		Utils.trace("*************************** end ************************************\n");
		
	}
}
