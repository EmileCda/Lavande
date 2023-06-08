package fr.lotus.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.lotus.common.IConstant;
import fr.lotus.model.implement.ClassDao;

@Entity
@Table(name = "comment")
public class Comment  extends ClassDao implements IConstant {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	private String  text;
	private int   grade ; // from 0 to 5 

	@ManyToOne
	@JoinColumn(name = "item_id", nullable = false)
	private Item item;

	@ManyToOne
	@JoinColumn(name = "costumer_id", nullable = false)
	private Costumer costumer;
	
	
	public Comment() {
		this(DEFAULT_ID,DEFAULT_TEXT,DEFAULT_GRADE,null,null);
	}
	
	public Comment( String text, int grade) {
		this(DEFAULT_ID,text, grade, null, null);
	}
	
	public Comment(int id, String text, int grade, Item item, Costumer costumer) {
		this.setId ( id);
		this.setText ( text);
		this.setGrade ( grade);
		this.setItem ( item);
		this.setCostumer ( costumer);
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public int getGrade() {
		return grade;
	}

	public String getStarGrade() {
		String stringReturn="";
		for (int index = 0; index < this.getGrade(); index++) {
			stringReturn +="*";
		}
		return stringReturn;
	}


	public void setGrade(int grade) {
		this.grade = grade;
	}


	public Item getItem() {
		return item;
	}


	public void setItem(Item item) {
		this.item = item;
	}




	@Override
	public void preWrite() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postRead() {
		// TODO Auto-generated method stub
		
	}



	public Costumer getCostumer() {
		return costumer;
	}

	public void setCostumer(Costumer costumer) {
		this.costumer = costumer;
	}
	
	@Override
	public String toString() {
		return String.format("Id[%d], %s %s, pour:%s:[%s], de :%s", 
				getId(), getStarGrade(),getText(), 
				getItem().getName(),getItem().getCategory().getName(), 
				getCostumer().getEmail());
	}
}
