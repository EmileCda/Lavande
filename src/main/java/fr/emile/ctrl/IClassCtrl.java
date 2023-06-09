package fr.emile.ctrl;

public interface IClassCtrl {
	

	public abstract Object preWrite(Object object);
	public abstract Object postWrite(Object object);
	public abstract Object preRead(Object object);
	public abstract Object postRead(Object object);


}
