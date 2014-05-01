package ar.com.frba.utn.tacs.grupocuatro.domain;

public class Item_G4 extends BusinessObject{
	
	private static final long serialVersionUID = 5073309699938666985L;

	private String label;
	private Long votes = 0l;

	public Item_G4() {
		super();
	}

	public Item_G4(String label) {
		super();
		this.label = label;
	}

	public void incVotes(){
		this.votes++;
	}

	public Long getVotes() {
		return votes;
	}

	public void setVotes(Long votes) {
		this.votes = votes;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
