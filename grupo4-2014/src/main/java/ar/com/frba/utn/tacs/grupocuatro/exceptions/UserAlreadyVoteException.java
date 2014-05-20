package ar.com.frba.utn.tacs.grupocuatro.exceptions;

public class UserAlreadyVoteException extends BusinessException {

	private static final long serialVersionUID = -7177730249313809209L;

	public UserAlreadyVoteException(String message) {
		super(message);
	}

}
