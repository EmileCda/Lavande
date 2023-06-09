package fr.emile.ctrl;

import fr.emile.common.IConstant;
import fr.emile.entity.Param;
import fr.emile.model.ParamDao;
import fr.emile.utils.Utils;

public class ParamCtrl extends CrudCtrl implements IConstant {

	public ParamCtrl() {
		super(new Param());
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object preWrite(Object myObject) {
		Param param = (Param)myObject;  
		if (param != null) {
			param.setDatetimeValueSql(Utils.toSqlDate(param.getDatetimeValueJava()));
		}
		return param;
	}

	@Override
	protected Object postWrite(Object myObject) {
		// TODO Auto-generated method stub
		return myObject;
	}

	@Override
	protected Object postRead(Object myObject) {
		Param param = (Param)myObject;  
		if (param != null) {
			param.setDatetimeValueJava(Utils.toJavaDate(param.getDatetimeValueSql()));
		}
		return param;
	}
	
	public Param  getByFunctionCode(int id) throws Exception {
		Param param ; 
		ParamDao paramDao = new ParamDao();
		param= paramDao.readByFunctionCode(id);
		param = (Param) postRead(param);
		return param;
	}

}
