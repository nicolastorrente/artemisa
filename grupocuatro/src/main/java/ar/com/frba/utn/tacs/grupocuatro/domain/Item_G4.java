package ar.com.frba.utn.tacs.grupocuatro.domain;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import ar.com.frba.utn.tacs.grupocuatro.exceptions.UserAlreadyVoteException;

@Entity
@Cache
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((listId == null) ? 0 : listId.hashCode());
		result = prime * result + ((votes == null) ? 0 : votes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item_G4 other = (Item_G4) obj;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (listId == null) {
			if (other.listId != null)
				return false;
		} else if (!listId.equals(other.listId))
			return false;
		if (votes == null) {
			if (other.votes != null)
				return false;
		} else if (!votes.equals(other.votes))
			return false;
		return true;
	}
	
}
