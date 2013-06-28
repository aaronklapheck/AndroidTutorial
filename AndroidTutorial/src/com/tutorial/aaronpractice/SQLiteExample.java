package com.tutorial.aaronpractice;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * SQLiteExample uses the user interface sql_light.xml. This is a very simple
 * activity so all events are triggered by user input button presses defined in
 * {@link #onClick}.
 * 
 * @author Aaron Klapheck
 * @version 1.0.0
 */
public class SQLiteExample extends Activity implements OnClickListener {

	EditText etName, etHotness, etRow;
	Button bUpdate, bView, bGetInfo, bEdit, bDelete;
	private final static int bUpdate_id = R.id.bSQLUpdate,
			bView_id = R.id.bSQLView, bGetInfo_id = R.id.bGetInfo,
			bEdit_id = R.id.bEditEntry, bDelete_id = R.id.bDeleteEntry;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sql_light);
		initializeViews();
		initializeListeners();
	}

	/**
	 * Initializes all views. For example:
	 * <p>
	 * </p>
	 * {@code
	 * bNewTab = (Button) findViewById(R.id.bAddTab);
	 * }
	 * <p>
	 * </p>
	 * Notice that the variable {@code bNewTab} is not defined by a class. This
	 * should be done in the class not the method.
	 */
	private void initializeViews() {
		// TODO Auto-generated method stub
		etName = (EditText) findViewById(R.id.etSQLName);
		etHotness = (EditText) findViewById(R.id.etSQLHotness);
		etRow = (EditText) findViewById(R.id.etEnterRowID);
		bUpdate = (Button) findViewById(bUpdate_id);
		bView = (Button) findViewById(bView_id);
		bGetInfo = (Button) findViewById(bGetInfo_id);
		bEdit = (Button) findViewById(bEdit_id);
		bDelete = (Button) findViewById(bDelete_id);
	}

	/**
	 * Initializes all listeners. For example:
	 * <p>
	 * </p>
	 * {@code
	 * bNewButton.setOnClickListener(this);
	 * }
	 * <p>
	 * </p>
	 * For this code to work the following must be done:
	 * <ul>
	 * <li>{@code bNewButton} (a button in this case) must be assigned in
	 * {@link #initializeViews} and declared in the class container</li>
	 * <li>For {@code this} to be used as the onClickListener, the
	 * {@link android.view.View.OnClickListener} must be implemented by this
	 * class.</li>
	 * </ul>
	 */
	private void initializeListeners() {
		// TODO Auto-generated method stub
		bUpdate.setOnClickListener(this);
		bView.setOnClickListener(this);
		bGetInfo.setOnClickListener(this);
		bEdit.setOnClickListener(this);
		bDelete.setOnClickListener(this);
	}

	/**
	 * The onClickListeners have been setup in {@link #InitializeListeners} and five buttons
	 * are set up to listen to onClicks:
	 * <ul>
	 * 		<li>bUpdate - Takes values entered into the name and hotness text views and stores
	 * 			them in the database.</li>
	 * 		<li>bView - Runs the activity {com.tutorial.aaronpractice.sqlview} which shows
	 * 			all entries in the database.</li>
	 * 		<li>bGetInfo - Shows the name and hotness of the row id entered into the etRow.</li>
	 * 		<li>bEdit - Fill out the name, hotness, and row. This take all three pieces of information 
	 * 			and overrides the name and hotness of the entry located in the user specified row.</li>
	 * 		<li>bDelete - Deletes the entry determined by the user in etRow.</li>
	 * </ul>
	 */
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case bUpdate_id:

			String name = etName.getText().toString();
			String hotness = etHotness.getText().toString();
			long row = 0;

			try {
				HotOrNot entry = new HotOrNot(this);
				entry.open();
				row = entry.createEntry(name, hotness);
				entry.close();

				if (row != -1) {
					Toast t = Toast.makeText(this, "Entry was created in row "
							+ row, Toast.LENGTH_SHORT);
					t.show();
				}

			} catch (Exception e) {
				String error = e.toString();

				Dialog d = new Dialog(this);
				d.setTitle("Dang it!");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			}

			break;
		case bView_id:
			Intent in = new Intent("com.tutorial.aaronpractice.SQLVIEW");
			startActivity(in);
			break;
		case bGetInfo_id:
			String sRow = etRow.getText().toString();
			long l = Long.parseLong(sRow);
			try{
			HotOrNot hon = new HotOrNot(this);
			hon.open();
			String[] Return = hon.getNameAndHotness(l);
			hon.close();
			etName.setText(Return[0]);
			etHotness.setText(Return[1]);
			
			} catch (Exception e) {
				String error = e.toString();

				Dialog d = new Dialog(this);
				d.setTitle("Dang it!");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();

			}
			
			break;
		case bEdit_id:
			String[] entryUp = { etName.getText().toString(),
					etHotness.getText().toString(), etRow.getText().toString() };

			try{
			HotOrNot honUp = new HotOrNot(this);
			honUp.open();
			long check = honUp.updateEntry(entryUp);
			honUp.close();

			if (check == -1) {
				Toast t = Toast.makeText(this, "Error: table entry " + entryUp[0]
						+ " not edited",
						Toast.LENGTH_LONG);
				t.show();
			} else {
				Toast t = Toast.makeText(this, "table entry " + entryUp[0]
						+ " edited", Toast.LENGTH_SHORT);
				t.show();
			}
			
			} catch (Exception e) {
				String error = e.toString();

				Dialog d = new Dialog(this);
				d.setTitle("Dang it!");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();

			}

			break;
		case bDelete_id:
			String sDel = etRow.getText().toString();
			long lDel = Long.parseLong(sDel);

			try{
			HotOrNot honDel = new HotOrNot(this);
			honDel.open();
			long check2 = honDel.delEntry(lDel);
			honDel.close();

			if (check2 == 0) {
				Toast t = Toast.makeText(this, "Error: table entry " + sDel
						+ " not deleted", Toast.LENGTH_LONG);
				t.show();
			} else {
				Toast t = Toast.makeText(this, "table entry " + sDel
						+ " deleted", Toast.LENGTH_SHORT);
				t.show();
			}
			
			} catch (Exception e) {
				String error = e.toString();

				Dialog d = new Dialog(this);
				d.setTitle("Dang it!");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();

			}
			break;
		}

	}

}
