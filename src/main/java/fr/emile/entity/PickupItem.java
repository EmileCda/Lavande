package fr.emile.entity;

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
import javax.persistence.Transient;

import fr.emile.common.IConstant;
import fr.emile.ctrl.CrudCtrl;
import fr.emile.ctrl.StandardCrudCtrl;
import fr.emile.utils.Utils;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="item_type")
@Table(name="pickup_item")
public abstract class PickupItem implements IConstant, Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int quantity;
	@Transient
	private float grossCost;
	@Transient
	private float netCost;
	
	
	@OneToOne
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

	

	
	public void calculate() {
		
		int categoryDiscount = this.getCategoryDiscount();
		int totalDiscount = categoryDiscount + this.getItem().getDiscount();
		float netCoast = this.getQuantity() * this.getItem().getPrice()* (100- totalDiscount)/100; 
		float grossCoast = this.getQuantity() * this.getItem().getPrice(); 
		this.setNetCost(netCoast );
		this.setGrossCost(grossCoast);
	}
	
	
	public int getCategoryDiscount() {
		int discount = 0 ; 

		Category category = this.getItem().getCategory();
		if (category.isDiscountCumulative())
			discount = category.getDiscount();
		return discount ; 
	}
	
	
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


	public float getGrossCost() {
		return Math.max(0, this.grossCost);
	}

	public void setGrossCost(float grossCost) {
		this.grossCost = grossCost;
	}

	public float getNetCost() {
		 
		return Math.max(0, this.netCost);
	}

	public void setNetCost(float netCost) {
		this.netCost = netCost;
	}

	
	

}
