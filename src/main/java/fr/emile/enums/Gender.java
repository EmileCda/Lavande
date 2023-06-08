package fr.lotus.enums;



public enum Gender {

	MALE("Homme","M.",0),FEMALE("Femme","Mme",1), OTHER("autre","",2),NULL("","",3) ;
	
	private String value;
	private String title;
	private int id;
	
	private Gender(String value,String title,int id) {
		this.setValue(value);
		this.setTitle(title);
		this.setId(id);
	}
	private Gender() {
		
		this("","",-1);
	}
	
	
	public static Gender fromString(String text) {
		for (Gender oneValue : Gender.values()) {
			if (oneValue.getValue().equalsIgnoreCase(text)) {
				return oneValue;
			}
		}
		return null;
	}

	public static Gender fromId(int id) {
		for (Gender oneValue : Gender.values()) {
			if (oneValue.getId() == id) {
				return oneValue;
			}
		}
		return NULL;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setValue(String value) {
		this.value = value;
	}
	public String getValue() {
		return this.value;
	}

}
