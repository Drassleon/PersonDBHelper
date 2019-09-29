package pe.edu.myapp.dbHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import pe.edu.myapp.model.Person

class DBHelper(
    context: Context?
) : SQLiteOpenHelper(
    context,
    DATABASE_NAME, null,
    DATABASE_VER
) {
    companion object {
        private const val DATABASE_NAME = "myApp.db"
        private const val DATABASE_VER = 1

        //Table definition
        private const val TABLE_NAME = "Person"
        private const val COL_ID = "Id"
        private const val COL_NAME = "Name"
        private const val COL_EMAIL = "Email"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery =
            ("CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY, $COL_NAME TEXT, $COL_EMAIL TEXT)")

        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    //CRUD
    var allPerson: List<Person>
        get() {
            val lstPersons = ArrayList<Person>()
            val findAllQuery = "SELECT * FROM $TABLE_NAME "
            val db = this.writableDatabase
            val cursor = db.rawQuery(findAllQuery, null)

            if (cursor.moveToFirst()) {
                do {
                    val person = Person()
                    person.id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                    person.name = cursor.getString(cursor.getColumnIndex(COL_NAME))
                    person.email = cursor.getString(cursor.getColumnIndex(COL_EMAIL))

                    lstPersons.add(person)
                } while (cursor.moveToNext())
            }

            cursor.close()
            db.close()
            return lstPersons
        }
        set(value) {}

    fun addPerson(person: Person) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID, person.id)
        values.put(COL_NAME, person.name)
        values.put(COL_EMAIL, person.email)

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun updatePerson(person: Person) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID, person.id)
        values.put(COL_NAME, person.name)
        values.put(COL_EMAIL, person.email)

        db.update(TABLE_NAME, values, "$COL_ID =?", arrayOf(person.id.toString()))
        db.close()
    }

    fun deletePerson(person: Person) {
        val db = this.writableDatabase

        db.delete(TABLE_NAME, "$COL_ID =?", arrayOf(person.id.toString()))
        db.close()
    }
}