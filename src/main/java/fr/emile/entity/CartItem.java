package fr.lotus.entity;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.lotus.common.IConstant;


@Entity
@DiscriminatorValue("cart_item")
@Table(name="cart_item")
//https://en.wikibooks.org/wiki/Java_Persistence/Inheritance#Example_single_table_inheritance_table_in_database
public class CartItem  extends PickupItem implements IConstant, Serializable {
	
	
	
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "costumer_id", nullable = false)
	private Costumer costumer;
	
	
	public CartItem() {
		this(DEFAULT_ID,
				DEFAULT_QUANTITY,null,null
				);
		
	}
	public CartItem(int quantity,Costumer costumer, Item item) {
		this(DEFAULT_ID,quantity,costumer,item);
	}

	public CartItem(int id, int quantity, Costumer costumer, Item item) {
		
		this.setId ( id);
		this.setQuantity ( quantity);
		this.setCostumer ( costumer);
		this.setItem ( item);
	}



	public Costumer getCostumer() {
		return costumer;
	}
	public void setCostumer(Costumer costumer) {
		this.costumer = costumer;
	}
	@Override
	public String toString() {
		
		return String.format("%s => %s",super.toString(), getCostumer().getEmail());
	}

	
	

}
