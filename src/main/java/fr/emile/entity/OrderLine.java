package fr.lotus.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import fr.lotus.common.IConstant;


@Entity
@DiscriminatorValue("order_line")
@Table(name="order_line")
//https://en.wikibooks.org/wiki/Java_Persistence/Inheritance#Example_single_table_inheritance_table_in_database
public class OrderLine extends PickupItem implements IConstant, Serializable {
	
	
	private static final long serialVersionUID = -23153027186502371L;
	
	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;
	
	
	public OrderLine() {
		this(DEFAULT_ID,
				DEFAULT_QUANTITY,null,null);
		
	}
	public OrderLine(int id, int quantity, Item item, Order order) {
		this.setId ( id);
		this.setQuantity ( quantity);
		this.setItem ( item);
		this.setOrder (order);
	}
	
	
	
	
	
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	@Override
	public String toString() {
		float unitPrice = getItem().getPrice();
		float totalPrice =unitPrice * getQuantity();
		float totalDiscount =totalPrice * getItem().getDiscount()/100;
		float toPay=totalPrice -totalDiscount;
		
		return String.format("%s : %d x %s   %.2f€  réduction :-%.2f€  to pay %.2f€", 
				getOrder().getOrderNumber(), 
				getQuantity(),getItem().getName(), 
				totalPrice,totalDiscount,toPay);
	}
	
	

}
