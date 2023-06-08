
package fr.lotus.enums;

public enum Profile {

	COSTUMER("Costumer", 0), MANAGER("Admin", 1), STORE_KEEPER("magasinier", 2), WS_BASIC("Client WS basic", 3),
	WS_ADMIN("Client WS admin", 4), OTHER("autre", 5);

	private String name;
	private int id;

	private Profile(String name, int id) {
		this.setName(name);
		this.setId(id);
	}

	public static Profile fromString(String name) {
		for (Profile oneValue : Profile.values()) {
			if (oneValue.getName().equalsIgnoreCase(name)) {
				return oneValue;
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
