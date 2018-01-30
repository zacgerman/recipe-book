package com.german.zac.recipebook

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class RecipeDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    @Throws(SQLiteConstraintException::class)
    fun recipeCreate(recipe: RecipeModel): Long {
        val db = writableDatabase

        val values = ContentValues()
        values.put(RecipeEntryContract.RecipeEntry.COLUMN_TITLE, recipe.title)
        values.put(RecipeEntryContract.RecipeEntry.COLUMN_GROUP, recipe.group)
        values.put(RecipeEntryContract.RecipeEntry.COLUMN_BODY, recipe.body)

        return db.insert(RecipeEntryContract.RecipeEntry.TABLE_NAME, null, values)
    }






    companion object {
        // If you change the database schema, you must increment the database version.
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "FeedReader.db"

        private const val SQL_CREATE_ENTRIES =
                "CREATE TABLE ${RecipeEntryContract.RecipeEntry.TABLE_NAME} (" +
                        "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                        "${RecipeEntryContract.RecipeEntry.COLUMN_TITLE} TEXT," +
                        "${RecipeEntryContract.RecipeEntry.COLUMN_GROUP} TEXT," +
                        "${RecipeEntryContract.RecipeEntry.COLUMN_BODY} TEXT)"

        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${RecipeEntryContract.RecipeEntry.TABLE_NAME}"
    }
}
