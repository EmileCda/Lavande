package fr.emile.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import fr.emile.common.IConstant;
import fr.emile.utils.Utils;

@Entity
@Table(name = "costumer_order")
public class Order implements IConstant, Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;

	@Column(name = "order_number")
	private String orderNumber;

	@Column(name = "create_date")
	private Date createDate;
	@Column(name = "delivery_date")
	private Date deliveryDate;

	@Column(name = "total_discount")
	private float totalDiscount; // in value (not in%)
	@Column(name = "shipping_costs")
	private float shippingCosts;
	@Column(name = "grand_total")
	private float grandTotal;

	@OneToOne
	@JoinColumn(name = "delivery_address_id", nullable = false)
	private Address deliveryAddress;

	@OneToOne
	@JoinColumn(name = "billing_address_id", nullable = false)
	private Address billingAddress;

	@OneToOne
	@JoinColumn(name = "bank_card_used_id", nullable = false)
	private BankCard bankCardUsed;

	@ManyToOne
	@JoinColumn(name = "costumer_id", nullable = false)
	private Costumer costumer;

//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "Order", fetch = FetchType.LAZY)
	@Transient
	private List<OrderLine> orderLineList;

	public Order() {
		this(DEFAULT_ID, DEFAULT_ORDER_NUMBER, DEFAULT_DATE, DEFAULT_DATE, DEFAULT_FLOAT_VALUE, DEFAULT_FLOAT_VALUE,
				DEFAULT_FLOAT_VALUE, null, null, null, null);
	}

	public Order(Costumer costumer) {
		this(DEFAULT_ID, DEFAULT_ORDER_NUMBER, DEFAULT_DATE, DEFAULT_DATE, DEFAULT_FLOAT_VALUE, DEFAULT_FLOAT_VALUE,
				DEFAULT_FLOAT_VALUE, null, null, null, costumer);

	}

	public Order(String orderNumber, Date deliveryDate, float totalDiscount, float shippingCosts, float grandTotal) {

		this(DEFAULT_ID, orderNumber, DATE_NOW, deliveryDate, totalDiscount, shippingCosts, grandTotal, null, null,
				null, null);

	}

	public Order(int id, String orderNumber, Date createDate, Date deliveryDate, float totalDiscount,
			float shippingCosts, float grandTotal, Address deliveryAddress, Address billingAddress,
			BankCard bankCardUsed, Costumer costumer) {
		this.setId(id);
		this.setOrderNumber(orderNumber);
		this.setCreateDate(createDate);
		this.setDeliveryDate(deliveryDate);
		this.setTotalDiscount(totalDiscount);
		this.setShippingCosts(shippingCosts);
		this.setGrandTotal(grandTotal);
		this.setDeliveryAddress(deliveryAddress);
		this.setBillingAddress(billingAddress);
		this.setBankCardUsed(bankCardUsed);
		this.setCostumer(costumer);

		if (this.getOrderLineList() == null)
			this.setOrderLineList(new ArrayList<OrderLine>());
		if (this.getCostumer() != null)	this.generateOrderNumber();

	}

	public void addOrderLine(OrderLine orderLine) {
		initOrderLineList();
		this.getOrderLineList().add(orderLine);

	}

	public void initOrderLineList() {
		if (this.getOrderLineList() == null) {
			this.setOrderLineList(new ArrayList<OrderLine>());
		}

	}

	public String generateOrderNumber() {
		
		
		int departement = 0;
		if (this.getCostumer().getAddressList().size() > 0) {
			String zipCode = this.getCostumer().getAddressList().get(0).getZipCode();
			String departementStr = zipCode.substring(0, 2);
			departement = Integer.parseInt(departementStr);

		}
		String orderNumber =  String.format("JSM-%s-%05d-FR-%02d", Utils.date2String(DATE_NOW, "yyyyMMdd"), this.getCostumer().getId(),
				departement);
		
		this.setOrderNumber(orderNumber);
		return orderNumber; 

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public float getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(float totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public float getShippingCosts() {
		return shippingCosts;
	}

	public void setShippingCosts(float shippingCosts) {
		this.shippingCosts = shippingCosts;
	}

	public float getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public BankCard getBankCardUsed() {
		return bankCardUsed;
	}

	public void setBankCardUsed(BankCard bankCardUsed) {
		this.bankCardUsed = bankCardUsed;
	}

	public Costumer getCostumer() {
		return costumer;
	}

	public void setCostumer(Costumer costumer) {
		this.costumer = costumer;
	}

	public List<OrderLine> getOrderLineList() {
		return orderLineList;
	}

	public void setOrderLineList(List<OrderLine> orderLineList) {
		this.orderLineList = orderLineList;
	}

	@Override
	public String toString() {
		String deliveryAddress = "no-delivery-address";
		String billingAddress = "no-billing-address";
		if (getDeliveryAddress() != null)
			deliveryAddress = getDeliveryAddress().toString();
		if (getBillingAddress() != null)
			billingAddress = getBillingAddress().toString();

		return String.format(
				"Id[%d], %s, crea:%s, livraison le:%s, " + "réduction: -%.2f€,  frais de port(): %.2f€, Total: %.2f€ "
						+ "livraison: %s, facture: %s",
				getId(), getOrderNumber(), Utils.date2String(getCreateDate()), Utils.date2String(getDeliveryDate()),
				getTotalDiscount(), getShippingCosts(), getGrandTotal(), deliveryAddress, billingAddress);
	}

}
