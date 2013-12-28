package com.butterfly.spotter.android.database;

/** 
*
* @author Nadim
* @since  Dec 10, 2013
*
*/

public interface DbMetadata {

	 public static final String DATABASE_NAME = "spotter";
	 public static final int DATABASE_VERSION = 1;
	 
	 //common id
	 public static final String COLUMN_ID = "id";
	 public static final String COLUMN_CREATED = "created";
	 
	 ///Message table related info
	 public static final String TABLE_MESSAGE = "message";
	 public static final String COLUMN_MESSAGE_PHONE_ID = "phoneId";
	 public static final String COLUMN_MESSAGE_MESSAGE = "message";
	 
	 public static final String CREATE_MESSAGE_TABLE = "CREATE TABLE IF NOT EXISTS " 
			 + TABLE_MESSAGE 
			 + "("
             + COLUMN_ID 
             + " INTEGER PRIMARY KEY," 
             + COLUMN_MESSAGE_PHONE_ID
             + " TEXT,"
             + COLUMN_MESSAGE_MESSAGE
             + " TEXT,"
             + COLUMN_CREATED
             + " DATETIME"
             + ")";
	 
	 public static String FIND_FROM_MESSAGE_TABLE = "SELECT * FROM "
			 + TABLE_MESSAGE
			 + " WHERE "
			 + COLUMN_MESSAGE_PHONE_ID
			 + " = ? "
			 + " ORDER BY "
			 + DbMetadata.COLUMN_CREATED
			 + " ASC";

	 //
}
