package br.com.utilities.queries;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.hibernate.Query;
//import org.hibernate.SQLQuery;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

public abstract class QueryPrinter {

	private static Logger logger = LogManager.getLogger(QueryPrinter.class);
	
	public static boolean DEBUG = false;

	public static void debugOn() {
		DEBUG = true;
	}

	public static void debugOff() {
		DEBUG = false;
	}

	public static final void printQuery(Query<?> qry) {
		if (QueryPrinter.DEBUG) {
			logger.debug(qry.getQueryString());
		}
	}

	public static final void printQuery(NativeQuery<?> qry) {
		if (QueryPrinter.DEBUG) {
			logger.debug(qry.getQueryString());
		}
	}
}
