package ar.com.frba.utn.tacs.grupocuatro.domain;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import ar.com.frba.utn.tacs.grupocuatro.exceptions.UserAlreadyVoteException;

@Entity
public class Item_G4 extends BusinessObject{
	
	private static final long serialVersionUID = 5073309699938666985L;

	private String label;
	private Long votes = 0l;
	private List<Long> voters;
	@Index
	private Long listId;

	public Item_G4() {
		super();
		this.voters = new ArrayList<Long>();
	}

	public Item_G4(String label) {
		this();
		this.label = label;
	}

	public void incVotes(Long userId){
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

	public List<Long> getVoters() {
		return voters;
	}

	public void setVoters(List<Long> voters) {
		this.voters = voters;
	}

	@Override
	public String toString() {
		return "Item_G4 [label=" + label + ", id=" + getId() + "]";
	}

	public Long getListId() {
		return listId;
	}

	public void setListId(Long listId) {
		this.listId = listId;
	}
	
}
