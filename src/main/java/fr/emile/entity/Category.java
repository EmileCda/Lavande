package fr.lotus.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import fr.lotus.common.IConstant;
import fr.lotus.model.implement.ClassDao;
import fr.lotus.utils.Utils;

@Entity
@Table(name = "category")
public class Category extends ClassDao implements IConstant, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	private int discount;
	@Column(name = "is_discount_Cumulative")
	private boolean isDiscountCumulative;
	private String picture;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category", fetch = FetchType.LAZY)
//	 @Transient
	private List<Item> itemList;

	public Category() {
		this(DEFAULT_ID, DEFAULT_NAME, DEFAULT_DISCOUNT, false, DEFAULT_PIC);
	}

	public Category(String name, int discount, boolean isDiscountCumulative, String picture) {
		this(DEFAULT_ID, name, discount, isDiscountCumulative, picture);
	}

	public Category(int id, String name, int discount, boolean isDiscountCumulative, String picture) {
		this.setId(id);
		this.setName(name);
		this.setDiscount(discount);
		this.setDiscountCumulative(isDiscountCumulative);
		this.setPicture(picture);
		initItemList();

	}

	@Override
	public void preWrite() {
		// TODO Auto-generated method stub

	}

	@Override
	public void postRead() {
		// TODO Auto-generated method stub

	}

	public void addItem(Item item) {
		initItemList();
		this.getItemList().add(item);
	}

	public void initItemList() {
		if (this.getItemList() == null) {
			this.setItemList(new ArrayList<Item>());
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public boolean isDiscountCumulative() {
		return isDiscountCumulative;
	}

	public void setDiscountCumulative(boolean isDiscountCumulative) {
		this.isDiscountCumulative = isDiscountCumulative;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public List<Item> getItemList() {
		return this.itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	@Override
	public String toString() {

		String stringReturn = "";
		stringReturn += String.format("Id[%d], %s -%d%% %scumulable pic:%s\n", getId(), getName(), getDiscount(),
				isDiscountCumulative() ? "" : "non-", getPicture());
		if (this.getItemList() != null) {
			for (Item item : this.getItemList()) {
				stringReturn += "\t" + item.toString() + "\n";
			}
		}
		return stringReturn;
	}

}
