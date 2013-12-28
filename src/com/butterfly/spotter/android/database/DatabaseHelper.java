package com.butterfly.spotter.android.database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.butterfly.spotter.android.model.Message;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/** 
*
* @author Nadim
* @since  Dec 9, 2013
*
*/

public class DatabaseHelper extends SQLiteOpenHelper {
	
/*	private static DatabaseHelper sInstance = null;

	public static DatabaseHelper getInstance(Context context) {
		///check it. is it singleton pattern properly???
	    if (sInstance == null) {
	      sInstance = new DatabaseHelper(context.getApplicationContext());
	    }
	    return sInstance;
	  }
	     
*/	  /**
	   * Constructor should be private to prevent direct instantiation.
	   * make call to static factory method "getInstance()" instead.
	   */
	  public DatabaseHelper(Context context) {
	    super(context, DbMetadata.DATABASE_NAME, null,
	    		DbMetadata.DATABASE_VERSION);
	  }

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("DROP TABLE IF EXISTS " + DbMetadata.CREATE_MESSAGE_TABLE);
		db.execSQL(DbMetadata.CREATE_MESSAGE_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + DbMetadata.CREATE_MESSAGE_TABLE);
	    onCreate(db);
	}
	
	public void addMessage(Message message) {
		 ContentValues values = new ContentValues();
	     values.put(DbMetadata.COLUMN_MESSAGE_PHONE_ID, message.getPhoneId());
	     values.put(DbMetadata.COLUMN_MESSAGE_MESSAGE, message.getMessage());
	     values.put(DbMetadata.COLUMN_CREATED, getDateTime());
	     SQLiteDatabase db = null; 
	     try {
		     db = this.getWritableDatabase();
		     db.insert(DbMetadata.TABLE_MESSAGE, null, values);
	     } finally {
		     db.close();
	     }
	}
	
	public List<Message> getMessageList() {
		SQLiteDatabase db = this.getReadableDatabase();
		List<Message> messageList = new ArrayList<Message>();
		Cursor cursor = db.rawQuery(DbMetadata.FIND_FROM_MESSAGE_TABLE,
				new String[] {"0191130668"});

		if (cursor.moveToFirst()) {
            do {
                Message message = new Message();
                message.setPhoneId(cursor.getString(cursor.getColumnIndex(DbMetadata.COLUMN_MESSAGE_PHONE_ID)));
                message.setMessage(cursor.getString(cursor.getColumnIndex(DbMetadata.COLUMN_MESSAGE_MESSAGE)));
                message.setCreated(cursor.getString(cursor.getColumnIndex(DbMetadata.COLUMN_CREATED)));
 
                messageList.add(message);
            } while (cursor.moveToNext());
        }
		return messageList;
	}
	
	private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
