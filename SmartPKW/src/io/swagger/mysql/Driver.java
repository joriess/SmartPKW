package io.swagger.mysql;

import io.swagger.model.*;
import java.sql.*;
public class Driver {
	private static DataAccess dataAccess = DataAccess.getInstance();
	
	public static void main (String[] args) {
		dataAccess.getRideById(1);

	}

}
