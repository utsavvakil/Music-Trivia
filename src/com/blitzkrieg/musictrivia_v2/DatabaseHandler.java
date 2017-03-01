package com.blitzkrieg.musictrivia_v2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "scoreManager";

	// Score table name
	private static final String TABLE_SCORE = "Score";

	// Score Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_SCORE = "score";
    private static  final String KEY_FLAG= "flag";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_SCORE_TABLE = "CREATE TABLE " + TABLE_SCORE + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
				+ KEY_SCORE + " INTEGER,"
                + KEY_FLAG + " INTEGER "+")";
		db.execSQL(CREATE_SCORE_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORE);
		// Create tables again
		onCreate(db);
	}
	//------------------------------DATA BASE FUNCTIONS---------------------------------------------------

	// Adding new contact
	void addContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, contact.getName()); // Contact Name
		values.put(KEY_SCORE, contact.getScore()); // Contact Phone
        values.put(KEY_FLAG,1);
		// Inserting Row
		db.insert(TABLE_SCORE, null, values);
		db.close(); // Closing database connection
	}
    void addContact1(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName()); // Contact Name
        values.put(KEY_SCORE, contact.getScore()); // Contact Phone
        values.put(KEY_FLAG,0);
        // Inserting Row
        db.insert(TABLE_SCORE, null, values);
        db.close(); // Closing database connection
    }

	// Getting single contact
	Contact getContact(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_SCORE, new String[] { KEY_ID,
				KEY_NAME, KEY_SCORE }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), Integer.parseInt(cursor.getString(2)));
		// return contact
		return contact;
	}
	
	// Getting All Contacts
	public List<Contact> getAllContacts() {
		List<Contact> contactList = new ArrayList<Contact>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_SCORE + " WHERE flag ='1' ORDER BY score DESC  " ;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Contact contact = new Contact();
				contact.setID(Integer.parseInt(cursor.getString(0)));
				contact.setName(cursor.getString(1));
				contact.setScore(Integer.parseInt(cursor.getString(2)));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}

		// return contact list
		return contactList;
	}

	// Updating single contact
	public int updateContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, contact.getName());
		values.put(KEY_SCORE, contact.getScore());
        values.put(KEY_FLAG,0);
		// updating row
		return db.update(TABLE_SCORE, values, KEY_ID + " = ?",
				new String[] { String.valueOf(contact.getID()) });
	}

	// Deleting single contact
	public void deleteContact(int min,Contact sco) {
        System.out.print("DELETE WORKING");
		SQLiteDatabase db = this.getWritableDatabase();

       // db.delete(TABLE_SCORE, KEY_SCORE + "= '" + min + "'", new String[] { String.valueOf(sco.getID())});
       db.rawQuery(" DELETE FROM Score WHERE score ='" + min + "' AND id = '"+ sco.getID() +"'",null );
        System.out.print("okaaaaaaaaaaaaaaaaa"+sco.getID());
		/*db.delete(TABLE_SCORE, KEY_ID + " = ?",
				new String[] { String.valueOf(contact.getID()) });*/
		db.close();
	}


	// Getting contacts Count
	public int getContactsCount() {
		String countQuery = "SELECT  * FROM " + TABLE_SCORE+" "+" WHERE flag = '1' ";
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		//cursor.close();

		// return count
		return cursor.getCount();
	}
    public  int getContactRank(int score_rank){
        String countQuery = "SELECT  * FROM " + TABLE_SCORE+ " " + " WHERE score > "+score_rank;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        //cursor.close();

        // return count
        return cursor.getCount();
    }

}
