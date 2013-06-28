package com.tutorial.aaronpractice;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Simple database access helper class. Defines the basic CRUD operations
 * for the SQLiteExample, and gives the ability to list all notes as well as
 * retrieve or modify a specific note.
 */
public class HotOrNot {
	
	public static final String KEY_ROWID = "_id", KEY_NAME = "persons_name", 
			KEY_HOTNESS = "persons_hotness";
	
	private static final String DATABASE_NAME = "HotOrNot_db", DATABASE_TABLE = "people_table";
	private static final int DATABASE_VERSION = 1;
	
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    
    
    /**
     * Database creation sql statement
     */
    private static final String DATABASE_CREATE =
        "create table " + DATABASE_TABLE + " (" + 
        		KEY_ROWID + " integer primary key autoincrement, " +
        		KEY_NAME + " text not null, " + 
        		KEY_HOTNESS + " text not null);";
	
	private final Context mCtx;
	
	private static class DatabaseHelper extends SQLiteOpenHelper{

		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
			
		}
		
	}
	
	/**
     * Constructor - takes the context to allow the database to be
     * opened/created
     * 
     * @param ctx the Context within which to work
     */
    public HotOrNot(Context ctx) {
        this.mCtx = ctx;
    }
    
    
    /**
     * Open the notes database. If it cannot be opened, try to create a new
     * instance of the database. If it cannot be created, throw an exception to
     * signal the failure
     * 
     * @return this (self reference, allowing this to be chained in an
     *         initialization call)
     * @throws SQLException if the database could be neither opened or created
     */
    public HotOrNot open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }
    
    public void close() {
        mDbHelper.close();
    }
    
    
    /**
     * Create a new entry using the name and hotness level. If the entry is
     * successfully created return the new rowId for that entry, otherwise return
     * a -1 to indicate failure.
     * 
     * @param name the name of the person
     * @param hotness a ranking from 0 to 10 of the persons hotness
     * @return rowId or -1 if failed
     */
    public long createEntry(String name, String hotness) throws SQLException  {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_HOTNESS, hotness);

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }
    
    
    /**
     * Return a Cursor over the list of all notes in the database
     * 
     * @return Cursor over all notes
     */
    public String fetchAllData() throws SQLException {
    	
    	String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_HOTNESS};
        Cursor c = mDb.query(DATABASE_TABLE, columns, null, null, null, null, null);
        String result = "";
        
        int iRow = c.getColumnIndex(KEY_ROWID);
        int iName = c.getColumnIndex(KEY_NAME);
        int iHotness = c.getColumnIndex(KEY_HOTNESS);
        
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
        	result = result + c.getString(iRow) + " " + c.getString(iName) + " " + 
        				c.getString(iHotness) + "\n";
        }
        
        return result;        
    }

    /**
     * Return a Cursor positioned at the note that matches the given rowId
     * 
     * @param rowId id of note to retrieve
     * @return Cursor positioned to matching note, if found
     * @throws SQLException if note could not be found/retrieved
     */
    public Cursor fetchData(long rowId) throws SQLException {

        Cursor mCursor =

            mDb.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                    KEY_NAME, KEY_HOTNESS}, KEY_ROWID + "=" + rowId, null,
                    null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }
    
    public String[] getNameAndHotness(long l) throws SQLException  {
    	String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_HOTNESS};
    	Cursor mCursor = mDb.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + l, null, null, null, null);
    	
    	if (mCursor != null) {
            mCursor.moveToFirst();
            String[] nameAndHotness = {mCursor.getString(1), mCursor.getString(2)};
            return nameAndHotness;
        }        
    	return null;
    }


	public long updateEntry(String[] entryUp) throws SQLException {
		// TODO Auto-generated method stub
		/*
		String name = entryUp[0];
		String hotness = entryUp[1];
		long l = Long.parseLong(entryUp[2]);
		*/
		
		ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, entryUp[0]);
        initialValues.put(KEY_HOTNESS, entryUp[1]);

        return mDb.update(DATABASE_TABLE, initialValues, KEY_ROWID + "=" + Long.parseLong(entryUp[2]), null);
		
	}


	public long delEntry(long l) throws SQLException  {
		// TODO Auto-generated method stub
		return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + l, null);
	}

}
