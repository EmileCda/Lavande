package fr.emile.view;


public class MasterBean {

	

	private String promptStatus = "" ; // message status for information and debug 
	
//%%%%%%%%%%%%%%%%%%%%%%%%%% action %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	
	

	public MasterBean() {
		// TODO Auto-generated constructor stub
	}

	public String getPromptStatus() {
		return promptStatus;
	}

	public void setPromptStatus(String promptStatus) {
		this.promptStatus = promptStatus;
	}
	
	public void cleanPromptStatus() {
		this.setPromptStatus(null);
	}
	
	public void clean() {
		this.setPromptStatus("");
	}
	public void resetPromptStatus() {
		this.setPromptStatus("");
	}
	
	public void setPromptStatus(String patern, Object... arguments) {
		this.setPromptStatus( String.format(patern, arguments));
		
	}
}
