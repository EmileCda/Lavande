package fr.lotus.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import fr.lotus.common.IConstant;
import fr.lotus.model.implement.ClassDao;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="item_type")
@Table(name="pickup_item")
public abstract class PickupItem  extends ClassDao implements IConstant, Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int quantity;
	
	
	@ManyToOne
	@JoinColumn(name = "item_id", nullable = false)
	private Item item;

	
	
	
	
	
	public PickupItem() {
		this(DEFAULT_ID,
				DEFAULT_QUANTITY,null
				);
		
	}

	public PickupItem(int id, int quantity,  Item item) {
		this.setId ( id);
		this.setQuantity ( quantity);
		this.setItem ( item);
	}

	

	@Override
	public  void preWrite() {} ;

	@Override
	public void postRead(){} ;
	
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return String.format("Id[%d] %d x %s ", 
				getId(),
				getQuantity(), 
				getItem().getName());
	}

	
	

}
