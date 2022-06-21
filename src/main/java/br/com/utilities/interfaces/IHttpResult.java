package br.com.utilities.interfaces;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.utilities.trowables.HttpResultThrowable;

public interface IHttpResult {

	public abstract class CreateResponse {

		public static <R> ResponseEntity<R> Entity(HttpStatus status) {
			return new ResponseEntity<R>(status);
		}

		public static <R> ResponseEntity<R> Entity(R r, HttpStatus status) {
			return new ResponseEntity<R>(r, status);
		}

		public static <R> ResponseEntity<R> Entity(Runnable r, HttpStatus status) {
			r.run();
			return new ResponseEntity<R>(status);
		}

		public static <R> ResponseEntity<R> Entity(IProcessor<R> p, HttpStatus status) {
			return new ResponseEntity<R>(p.process(), status);
		}

		@SuppressWarnings("unchecked")
		public static <R> ResponseEntity<R> Entity(IThrowableProcessor<R> p) {
			ResponseEntity<R> result = null;
			try {
				p.process();
			} catch (HttpResultThrowable e) {
				result = new ResponseEntity<R>((R) e.getContent(), e.getStatus());
			}
			return result;
		}
	}

	public abstract class OK {

		public static ResponseEntity<String> response() {
			return CreateResponse.Entity(HttpStatus.OK);
		}

		public static <R> ResponseEntity<R> response(R r) {
			return CreateResponse.Entity(r, HttpStatus.OK);
		}

		public static <R> ResponseEntity<R> response(Runnable r) {
			return CreateResponse.Entity(r, HttpStatus.OK);
		}

		public static <R> ResponseEntity<R> response(IProcessor<R> p) {
			return CreateResponse.Entity(p.process(), HttpStatus.OK);
		}

		public static <R> ResponseEntity<R> response(IThrowableProcessor<R> p) {
			return CreateResponse.Entity(p);
		}

		public static void result() throws HttpResultThrowable {
			Custom.result(HttpStatus.OK);
		}

		public static void result(Object content) throws HttpResultThrowable {
			Custom.result(HttpStatus.OK, content);
		}

		public static void result(Object content, String message) throws HttpResultThrowable {
			Custom.result(HttpStatus.OK, content, message);
		}
	}

	public static class NotFound {

		public static void result() throws HttpResultThrowable {
			Custom.result(HttpStatus.NOT_FOUND, null);
		}

		public static void result(Object content) throws HttpResultThrowable {
			Custom.result(HttpStatus.NOT_FOUND, content);
		}

		public static void result(Object content, String message) throws HttpResultThrowable {
			Custom.result(HttpStatus.NOT_FOUND, content, message);
		}

	}

	public static class NotAcceptable {

		public static void result() throws HttpResultThrowable {
			Custom.result(HttpStatus.NOT_ACCEPTABLE, null);
		}

		public static void result(Object content) throws HttpResultThrowable {
			Custom.result(HttpStatus.NOT_ACCEPTABLE, content);
		}

		public static void result(Object content, String message) throws HttpResultThrowable {
			Custom.result(HttpStatus.NOT_ACCEPTABLE, content, message);
		}
	}

	public static class Created {

		public static void result() throws HttpResultThrowable {
			Custom.result(HttpStatus.CREATED, null);
		}

		public static void result(Object content) throws HttpResultThrowable {
			Custom.result(HttpStatus.CREATED, content);
		}

		public static void result(Object content, String message) throws HttpResultThrowable {
			Custom.result(HttpStatus.CREATED, content, message);
		}
	}

	public static class InternalServerError {

		public static void result() throws HttpResultThrowable {
			Custom.result(HttpStatus.INTERNAL_SERVER_ERROR, null);
		}

		public static void result(Object content) throws HttpResultThrowable {
			Custom.result(HttpStatus.INTERNAL_SERVER_ERROR, content);
		}

		public static void result(Object content, String message) throws HttpResultThrowable {
			Custom.result(HttpStatus.INTERNAL_SERVER_ERROR, content, message);
		}
	}

	public abstract class Custom {

		public static ResponseEntity<String> response(HttpStatus status) {
			return CreateResponse.Entity(status);
		}

		public static <R> ResponseEntity<R> response(R r, HttpStatus status) {
			return CreateResponse.Entity(r, status);
		}

		public static <R> ResponseEntity<R> response(Runnable r, HttpStatus status) {
			return CreateResponse.Entity(r, status);
		}

		public static <R> ResponseEntity<R> response(IProcessor<R> p, HttpStatus status) {
			return CreateResponse.Entity(p.process(), status);
		}

		public static <R> ResponseEntity<R> response(IThrowableProcessor<R> p) {
			return CreateResponse.Entity(p);
		}

		public static void result(HttpStatus status) throws HttpResultThrowable {
			throw new HttpResultThrowable(status, null);
		}

		public static void result(HttpStatus status, Object content) throws HttpResultThrowable {
			throw new HttpResultThrowable(status, content);
		}

		public static void result(HttpStatus status, Object content, String message) throws HttpResultThrowable {
			throw new HttpResultThrowable(status, content, message);
		}
	}

}
