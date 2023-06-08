package fr.ecommerce.test;

import java.util.ArrayList;
import java.util.List;


import fr.ecommerce.model.dao.implement.ParamsDao;
import fr.ecommerce.model.dao.interfaces.IParamsDao;
import fr.ecommerce.utils.DataTest;
import fr.ecommerce.utils.Params;
import fr.ecommerce.utils.Utils;

public class TParams {

	public static void main(String[] args) {
		Utils.trace("*************************** Begin ************************************\n");
		createOne();
//		createMany();
		readOne();
//		readMany();
//		update();
//		delete();

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
		int paramsId = 3;
		Params params = null ;  
		
		IParamsDao paramsDao = new ParamsDao();
		try {
			params = paramsDao.getParamsById(paramsId);
			if (params == null )
				Utils.trace("Address null\n");
			else {
				Utils.trace("Before  %s\n", params);

				// -------------------------- update ----------------------
				params.setFunctionCode(12345);
				paramsDao.updateParams(params);
	
				params = paramsDao.getParamsById(paramsId);
				if (params != null )
					Utils.trace("After %s\n", params);
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
		int addressId = 3;
		Params params = new Params();
		IParamsDao paramsDao = new ParamsDao();
		try {
			params = paramsDao.getParamsById(addressId);
			if (params == null) 
				Utils.trace("Error : l'params n'existe pas\n");
			else {
				paramsDao.deleteParams(params );

				params = paramsDao.getParamsById(addressId);

				if (params != null)
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
		Params params = new Params();
		params = DataTest.genParams();
		Utils.trace("%s\n", params);


		IParamsDao paramsDao = new ParamsDao();

		try {
			paramsDao.addParams(params);
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());
		} finally {

		}

		Utils.trace("%s\n", params);
	}
	//-------------------------------------------------------------------------------------------------	

	public static void createMany() {
		Utils.trace("=========================== read many  ===========================\n");
		int maxIndex = 10;

		Params params = new Params();
		Utils.trace("%s\n", params);

//address.setCostumer(costumer);
//costumer.addAddress(address);
//Utils.trace(costumer.getAddressList().get(0).toString());

		IParamsDao paramsDao = new ParamsDao();

		try {
			for (int index = 0; index < maxIndex; index++) {
				params = DataTest.genParams();
				paramsDao.addParams(params);
			}
		} catch (Exception e) {
			Utils.trace("catch create %s\n", e.toString());
		} finally {

		}
	}
	//-------------------------------------------------------------------------------------------------	
	public static void readMany() {
		Utils.trace("=========================== read many  ===========================\n");

		List<Params> paramsList = new ArrayList<Params>() ;
		IParamsDao paramsDao = new ParamsDao();
		try {
			paramsList = paramsDao.getParamss();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ((paramsList.size() >0  ) && (paramsList != null)) {
			for (Params params : paramsList) {
				Utils.trace("%s\n",params); 
			}
		}
		else
			Utils.trace("address null");
	}
//-------------------------------------------------------------------------------------------------	
	public static void readOne() {
		Utils.trace("=========================== read One  ===========================\n");
		int addressId = 6;
		Params params = new Params() ;
		IParamsDao paramsDao = new ParamsDao();
		try {
			params = paramsDao.getParamsById(addressId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (params != null )
			Utils.trace("%s\n",params); 
		else 
			Utils.trace("address null\n");
		
	}
}
