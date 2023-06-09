package fr.emile.ctrl;

import fr.emile.common.IConstant;

public class StandardCrudCtrl extends CrudCtrl implements IConstant {

	public StandardCrudCtrl(Object currentObject) {
		super(currentObject);
	}

	@Override
	protected Object preWrite(Object myObject) {
		return myObject;
	}

	@Override
	protected Object postWrite(Object myObject) {
		return myObject;
	}

	@Override
	protected Object postRead(Object myObject) {
		return myObject;
	}

}
