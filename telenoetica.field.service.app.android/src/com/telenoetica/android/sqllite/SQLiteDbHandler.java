package com.telenoetica.android.sqllite;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.telenoetica.android.rest.AppValuesHolder;

public class SQLiteDbHandler extends AbstractSQLiteDbHandler {

  public SQLiteDbHandler(final Context context) {
    super(context);
  }

  public boolean validateUser(final String username,final String password) {
    SQLiteDatabase db = getWritableDatabase();
    String selectQuery = "SELECT  * FROM " + getCredentialsTable() +" where username='"+username+"' and password='"+password+"'" ;
    LOGGER.debug("validateUser .. Checking "+username+" in Phone Db.");
    boolean found = false;
    try {
      Cursor cursor = db.rawQuery(selectQuery, null);
      // looping through all rows and adding to list
      if (cursor.moveToFirst()) {
        do {
          found = true;
        }while(cursor.moveToNext());
      }
      cursor.close();
      db.close();
    } catch (Exception e) {
      LOGGER.error("validateUser  Failed....",e);
    }
    return found;
  }

  public void insertUser(final String username,final String password) {
    SQLiteDatabase db = getWritableDatabase();
    LOGGER.debug("insertUser .. "+username+" in Phone Db.");
    try {
      ContentValues values = new ContentValues();
      values.put("username", username);
      values.put("password", password);
      db.insert(getCredentialsTable(), null, values);
    } catch (Exception e) {
      LOGGER.error("insertUser  Failed....",e);
    }
    db.close(); // Closing database connection
  }

  public void checkBaseDataInSystem(){
    SQLiteDatabase db = getWritableDatabase();
    String baseQuery = "SELECT name FROM ";

    List<String> dataList = null;

    String selectQuery = baseQuery + getClientsTable();
    if(AppValuesHolder.getClients().size() == 1){
      dataList = getNamesList(db, selectQuery);
      AppValuesHolder.setClients(dataList);
    }

    selectQuery = baseQuery + getFaultsTable();
    if(AppValuesHolder.getFaults().size() == 1){
      dataList = getNamesList(db, selectQuery);
      AppValuesHolder.setFaults(dataList);
    }

    selectQuery = baseQuery + getMaintenanceTable();
    if(AppValuesHolder.getMaintenanceCategories().size() == 1){
      dataList = getNamesList(db, selectQuery);
      AppValuesHolder.setMaintenanceCategories(dataList);
    }

    selectQuery = baseQuery + getSitesTable();
    if(AppValuesHolder.getSites().size() == 1){
      dataList = getNamesList(db, selectQuery);
      AppValuesHolder.setSites(dataList);
    }

    selectQuery = baseQuery + getSparesTable();
    if(AppValuesHolder.getSpares().size() == 1){
      dataList = getNamesList(db, selectQuery);
      AppValuesHolder.setSpares(dataList);
    }
    db.close();
  }

  private List<String> getNamesList(final SQLiteDatabase db,final String selectQuery){

    List<String> dataList = new ArrayList<String>();
    try {
      Cursor cursor = db.rawQuery(selectQuery, null);
      // looping through all rows and adding to list
      cursor.moveToFirst();
      do{
        dataList.add(cursor.getString(0));
      }while(cursor.moveToNext());

      cursor.close();
    }catch (Exception e) {
      LOGGER.error("Error populating..",selectQuery);
    }
    return dataList;
  }

  public void checkAndInsertBaseData(){
    SQLiteDatabase db = getWritableDatabase();
    long count = DatabaseUtils.queryNumEntries(db,getMaintenanceTable());

    if(count <=0){
      try {
        LOGGER.debug("Inserting Base Data...");
        db.beginTransaction();
        insertAndroidBaseData(db);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
      } catch (Exception e) {
        LOGGER.error("Exception...on insert base data",e);
      }
    }
    checkBaseDataInSystem();
  }

  private void insertAndroidBaseData(final SQLiteDatabase db) {
    String sql = null;
    if(!CollectionUtils.isEmpty(AppValuesHolder.getSpares())){
      sql = getBaseDataInsertSql(getSparesTable());
      insertBaseData(db, sql, AppValuesHolder.getSpares());
    }
    if(!CollectionUtils.isEmpty(AppValuesHolder.getClients())){
      sql = getBaseDataInsertSql(getClientsTable());
      insertBaseData(db, sql, AppValuesHolder.getClients());
    }

    if(!CollectionUtils.isEmpty(AppValuesHolder.getFaults())){
      sql = getBaseDataInsertSql(getFaultsTable());
      insertBaseData(db, sql, AppValuesHolder.getFaults());
    }

    if(!CollectionUtils.isEmpty(AppValuesHolder.getMaintenanceCategories())){
      sql = getBaseDataInsertSql(getMaintenanceTable());
      insertBaseData(db, sql, AppValuesHolder.getMaintenanceCategories());
    }
    if(!CollectionUtils.isEmpty(AppValuesHolder.getSites())){
      sql = getBaseDataInsertSql(getSitesTable());
      insertBaseData(db, sql, AppValuesHolder.getSites());
    }
  }

  private void insertBaseData( final SQLiteDatabase db,final String sql,final List<String> data){
    SQLiteStatement stmt = db.compileStatement(sql);
    for (int i = 1; i < data.size(); i++) {
      stmt.bindString(1, data.get(i));
      stmt.execute();
      stmt.clearBindings();

    }
  }

  private String getBaseDataInsertSql(final String tableName){
    String sql = "INSERT INTO "+ tableName+ " (name) VALUES (?)";
    return sql;
  }

}
