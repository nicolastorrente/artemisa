package ar.com.frba.utn.tacs.grupocuatro.domain;

import java.util.ArrayList;
import java.util.List;

import ar.com.frba.utn.tacs.grupocuatro.exceptions.UserAlreadyVoteException;

public class Item_G4 extends BusinessObject{
	
	private static final long serialVersionUID = 5073309699938666985L;

	private String label;
	private Long votes = 0l;
	private List<String> voters;

	public Item_G4() {
		super();
		this.voters = new ArrayList<String>();
	}

	public Item_G4(String label) {
		this();
		this.label = label;
	}

	public void incVotes(String userId){
		this.votes++;
		if(this.voters.contains(userId))
			throw new UserAlreadyVoteException("El usuario ya voto por este item");
		this.voters.add(userId);
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

	public List<String> getVoters() {
		return voters;
	}

	public void setVoters(List<String> voters) {
		this.voters = voters;
	}
	
}
