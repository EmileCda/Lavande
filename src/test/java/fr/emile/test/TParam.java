package fr.emile.test;

import java.util.ArrayList;
import java.util.List;

import fr.emile.common.IConstant;
import fr.emile.ctrl.ParamCtrl;
import fr.emile.entity.Param;
import fr.emile.utils.Utils;



public class TParam implements IConstant{

	public static void main(String[] args) {
		Utils.trace("*************************** Begin ************************************\n");
//		createOne();
//		createMany();
//		readOne();
//		readMany();
//		update();
		delete();

		Utils.trace("*************************** end ************************************\n");

	}

//-------------------------------------------------------------------------------------------------
	public static void create() {
		Utils.trace("=========================== Create ===========================\n");
		createOne();
		createMany();

	}

//-------------------------------------------------------------------------------------------------
	public static void read() {
		Utils.trace("=========================== Read ===========================\n");
		readMany();
		readOne();

	}

//-------------------------------------------------------------------------------------------------
	public static void update() {
		Utils.trace("=========================== Update ===========================\n");
		int codeFunction = 3;
		Param param = null ;  
		
		ParamCtrl paramCtrl = new ParamCtrl();
		try {
			param = paramCtrl.getByFunctionCode(codeFunction);
			if (param == null )
				Utils.trace("param null\n");
			else {
				Utils.trace("param  %s\n", param);

				// -------------------------- update ----------------------
				param.setIntValue(10000);
				paramCtrl.update(param);
	
				param = paramCtrl.getByFunctionCode(codeFunction);
				if (param != null )
					Utils.trace("After %s\n", param);
				else
					Utils.trace("Address null\n");
			}

		} catch (

				Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}

//-------------------------------------------------------------------------------------------------
	public static void delete() {
		Utils.trace("=========================== Delete ===========================\n");
		int codeFunction = 3;
		Param param = new Param();
		ParamCtrl paramCtrl = new ParamCtrl();
		try {
			param = paramCtrl.getByFunctionCode(codeFunction);
			if (param == null) 
				Utils.trace("Error : l'param n'existe pas\n");
			else {
				paramCtrl.delete(param );

				param = paramCtrl.getByFunctionCode(codeFunction);

				if (param != null)
					Utils.trace("Error not remove\n");
				else
					Utils.trace("remove ok\n");
			}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}

	}
	//-------------------------------------------------------------------------------------------------	

	public static void createOne() {
//
		Param param = new Param();
		param = DataTest.genParam(FUNCTION_KEY_DB);
		Utils.trace("%s\n", param);


		ParamCtrl paramCtrl = new ParamCtrl();

		try {
			paramCtrl.create(param);
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());
		} finally {

		}

		Utils.trace("%s\n", param);
	}
	//-------------------------------------------------------------------------------------------------	

	public static void createMany() {
		Utils.trace("=========================== read many  ===========================\n");
		int maxIndex = 10;
		Param param = new Param();
		ParamCtrl paramCtrl = new ParamCtrl();

		try {
			for (int index = 0; index < maxIndex; index++) {
				param = DataTest.genParam(index);
				paramCtrl.create(param);
			}
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());
		} finally {

		}
	}
	//-------------------------------------------------------------------------------------------------	
	public static void readMany() {
		Utils.trace("=========================== read many  ===========================\n");

		List<Object> paramList = new ArrayList<Object>() ;
		ParamCtrl paramCtrl = new ParamCtrl();
		try {
			paramList = paramCtrl.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ((paramList.size() >0  ) && (paramList != null)) {
			for (Object object : paramList) {
				Utils.trace("%s\n",(Param) object ); 
			}
		}
		else
			Utils.trace("address null");
	}
//-------------------------------------------------------------------------------------------------	
	public static void readOne() {
		Utils.trace("=========================== read One  ===========================\n");
		Param param = new Param() ;
		ParamCtrl paramCtrl = new ParamCtrl();
		try {
			param = paramCtrl.getByFunctionCode(FUNCTION_KEY_DB);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (param != null )
			Utils.trace("%s\n",param); 
		else 
			Utils.trace("address null\n");
		
	}
}
