package ar.com.frba.utn.tacs.grupocuatro.exceptions;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 4764716733748179403L;
	
	public BusinessException(String message) {
		super(message);
	}
}
