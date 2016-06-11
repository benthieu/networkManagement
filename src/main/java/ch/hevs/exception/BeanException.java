package ch.hevs.exception;

public class BeanException extends RuntimeException {

	public BeanException() {
		super();
	}

	public BeanException(String arg0) {
		super(arg0);
	}

	public BeanException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public BeanException(Throwable arg0) {
		super(arg0);
	}

}
