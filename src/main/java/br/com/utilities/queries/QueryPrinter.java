package br.com.utilities.queries;

import org.hibernate.Query;
import org.hibernate.SQLQuery;

public class QueryPrinter {

	public static boolean DEBUG = false;

	public static void debugOn() {
		DEBUG = true;
	}

	public static void debugOff() {
		DEBUG = false;
	}

	public static final void printQuery(Query qry) {
		if (QueryPrinter.DEBUG) {
			System.out.println(qry.getQueryString());
		}
	}

	public static final void printQuery(SQLQuery qry) {
		if (QueryPrinter.DEBUG) {
			System.out.println(qry.getQueryString());
		}
	}
}
