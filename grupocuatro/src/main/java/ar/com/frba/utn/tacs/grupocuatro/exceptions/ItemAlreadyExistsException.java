package ar.com.frba.utn.tacs.grupocuatro.exceptions;

public class ItemAlreadyExistsException extends BusinessException {

	private static final long serialVersionUID = -4060304360976139557L;

	public ItemAlreadyExistsException(String message) {
		super(message);
	}

}
