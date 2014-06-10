package ar.com.frba.utn.tacs.grupocuatro.exceptions;

public class UserAlreadyExistsException extends BusinessException {

	private static final long serialVersionUID = -153103979027989164L;

	public UserAlreadyExistsException(String message) {
		super(message);
	}

}
