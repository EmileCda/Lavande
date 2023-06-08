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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import fr.lotus.common.IConstant;
import fr.lotus.model.implement.ClassDao;

@Entity
@Table(name = "item")
public class Item extends ClassDao implements IConstant, Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private float price;
	private int discount; // in %
	private int inventory;
	@Column(name = "is_sallable")
	private boolean isSalable;
	private String picture;
	private String video;

	@ManyToOne
	@JoinColumn(name = "category_id" ,nullable = false)
//	@Transient
	private Category category;
	
	
	@OneToMany(cascade = CascadeType.DETACH, mappedBy = "item", fetch = FetchType.LAZY)
//	@Transient
	private List<Comment> commentList;

	@OneToMany(cascade = CascadeType.DETACH, mappedBy = "item", fetch = FetchType.LAZY)
//	@Transient
	private List<CartItem > cartItemList;

	@OneToMany(cascade = CascadeType.DETACH, mappedBy = "item", fetch = FetchType.LAZY)
//	@Transient
	private List<OrderLine> orderLineList;

	public Item() {

		this(DEFAULT_ID, DEFAULT_NAME, DEFAULT_DESCRIPTION, DEFAULT_PRICE, DEFAULT_DISCOUNT, DEFAULT_INVENTORY, true,
				 DEFAULT_PICTURE, DEFAULT_VIDEO,null);
	}

	public Item( String name, String description, float price, int discount, int inventory, boolean isSalable,
			 String picture, String video) {
		this(DEFAULT_ID, name, description, price, discount, inventory, isSalable,
				 picture, video,null);
	}

	public Item(int id, String name, String description, float price, int discount, int inventory, boolean isSalable,
				String picture, String video,Category category ) {
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
		this.setPrice(price);
		this.setDiscount(discount);
		this.setInventory(inventory);
		this.setSalable(isSalable);
		this.setPicture(picture);
		this.setVideo(video);
		this.setCategory(category);
		initCommentList();
		initCartItemList();

	}
	public void addComment(Comment comment) {
		initCommentList();
		this.getCommentList().add(comment);
		
	}

	public void addCartItem(CartItem cartItem) {
		initCartItemList();
		this.getCartItemList().add(cartItem);
		
	}
	public void addOrderLine(OrderLine orderLine) {
		initOrderLineList();
		this.getOrderLineList().add(orderLine);
		
	}

	
	public void initOrderLineList() {
		if (this.getOrderLineList()== null) {
			this.setOrderLineList(new ArrayList<OrderLine>())  ;
		}
		
	}
	
	public void initCommentList() {
		if (this.getCommentList()== null) {
			this.setCommentList(new ArrayList<Comment>())  ;
		}
		
	}
	
	
	public void initCartItemList() {
		if (this.getCartItemList()== null) {
			this.setCartItemList(new ArrayList<CartItem>())  ;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public boolean isSalable() {
		return isSalable;
	}

	public void setSalable(boolean isSalable) {
		this.isSalable = isSalable;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(ArrayList<Comment> commentList) {
		this.commentList = commentList;
	}

	@Override
	public void preWrite() {
		// TODO Auto-generated method stub

	}

	@Override
	public void postRead() {
		// TODO Auto-generated method stub

	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public List<CartItem> getCartItemList() {
		return cartItemList;
	}

	public void setCartItemList(List<CartItem> cartItemList) {
		this.cartItemList = cartItemList;
	}

	@Override
	public String toString() {
		return String.format("Id[%d] %s : %s %.2f€, -%d %%, stock:%d, %svendable, img:%s, vid :%s",
				getId(), getName(), getDescription(), getPrice(), getDiscount(), getInventory(), 
				isSalable()?"":"non-",
				getPicture(), getVideo());

	}

	public List<OrderLine> getOrderLineList() {
		return orderLineList;
	}

	public void setOrderLineList(List<OrderLine> orderLineList) {
		this.orderLineList = orderLineList;
	}



}
