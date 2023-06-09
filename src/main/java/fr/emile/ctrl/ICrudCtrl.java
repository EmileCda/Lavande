package fr.emile.ctrl;

public interface ICrudCtrl {

	private Object preWrite(Object myObject) {return myObject ; };
	private Object postWrite(Object myObject){return myObject ; };
	private Object postRead(Object myObject){return myObject ; };
	
}
