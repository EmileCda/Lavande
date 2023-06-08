package fr.lotus.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import fr.lotus.common.IConstant;
import fr.lotus.model.implement.ClassDao;

@Entity
@Table(name = "sys_param")
public class Param extends ClassDao  implements IConstant, Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "function_key")
	private int functionKey;
	@Column(name = "int_value")
	private int intValue;
	@Column(name = "varchar_value256")
	private String varcharValue256;
	@Column(name = "varchar_value4096")
	private String varcharValue4096;
	@Column(name = "blob_value")
	private byte[] blobValue;
	@Column(name = "datetime_value")
	private Date datetimeValue;

	public Param() {
		this(DEFAULT_ID, DEFAULT_FUNCTION_KEY, DEFAULT_INT,
				null, null, null, null);

	}
	
	public Param(int  functionKey, int intValue, String varcharValue256, String varcharValue4096,
			byte[] blobValue, Date datetimeValue) {
		
		this(DEFAULT_ID,functionKey, intValue, varcharValue256, varcharValue4096,
				blobValue, datetimeValue) ;
	}
	
	public Param(int id, int functionKey, int intValue, String varcharValue256, String varcharValue4096,
			byte[] blobValue, Date datetimeValue) {
		this.setId ( id);
		this.setFunctionKey ( functionKey);
		this.setIntValue ( intValue);
		this.setVarcharValue256 ( varcharValue256);
		this.setVarcharValue4096 ( varcharValue4096);
		this.setBlobValue ( blobValue);
		this.setDatetimeValue ( datetimeValue);
	}

	
	public void preWrite(){
		
	}

	public void postRead(){
		
		
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFunctionKey() {
		return functionKey;
	}

	public void setFunctionKey(int functionKey) {
		this.functionKey = functionKey;
	}

	public int getIntValue() {
		return intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	public String getVarcharValue256() {
		return varcharValue256;
	}

	public void setVarcharValue256(String varcharValue256) {
		this.varcharValue256 = varcharValue256;
	}

	public String getVarcharValue4096() {
		return varcharValue4096;
	}

	public void setVarcharValue4096(String varcharValue4096) {
		this.varcharValue4096 = varcharValue4096;
	}

	public byte[] getBlobValue() {
		return blobValue;
	}

	public void setBlobValue(byte[] blobValue) {
		this.blobValue = blobValue;
	}

	public Date getDatetimeValue() {
		return datetimeValue;
	}

	public void setDatetimeValue(Date datetimeValue) {
		this.datetimeValue = datetimeValue;
	}

	@Override
	public String toString() {
		return String.format(
				"Id[%d] key=%d, int%d, s-string[%s] B-String[%s], blob[%s], %s",
				getId(), getFunctionKey(), getIntValue(), getVarcharValue256(), getVarcharValue4096(),
				Arrays.toString(getBlobValue()), getDatetimeValue());
	}
	

}
