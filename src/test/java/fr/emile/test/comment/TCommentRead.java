package comment;

import java.util.ArrayList;
import java.util.List;

import fr.lotus.entity.Comment;
import fr.lotus.entity.Comment;
import fr.lotus.model.implement.ClassDao;
import fr.lotus.model.implement.CrudDao;
import fr.lotus.model.interfaces.ICrudDao;
import fr.lotus.utils.Utils;

public class TCommentRead {
	public static void main(String[] args) {
		Utils.trace("*************************** Begin ************************************\n");
		readMany();
//		readOne();
		Utils.trace("*************************** end ************************************\n");
		
	}
	//-------------------------------------------------------------------------------------------------	
	public static void readMany() {
		Utils.trace("=========================== read many  ===========================\n");

		List<ClassDao> commentList = new ArrayList<ClassDao>() ;
		Comment comment = new Comment() ;
		ICrudDao commentDao = new CrudDao(comment);
		try {
			commentList = commentDao.read();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			commentDao.close();
		}
		if ((commentList.size() >0  ) && (commentList != null)) {
			for (ClassDao classDao : commentList) {
				comment = (Comment)classDao ; 
				Utils.trace("%s\n",comment);
			}
		}
		else
			Utils.trace(" commentList vide");
	}
	//-------------------------------------------------------------------------------------------------	
	public static void readOne() {
		Utils.trace("=========================== read One  ===========================\n");
		int commentId = 1;
		Comment comment = new Comment() ;
		ICrudDao commentDao = new CrudDao(comment);
		try {
			comment = (Comment) commentDao.read(commentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (comment != null )
			Utils.trace("%s\n",comment);
		else 
			Utils.trace("comment null\n");
		
	}
}
