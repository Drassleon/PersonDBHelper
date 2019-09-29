package pe.edu.myapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import pe.edu.myapp.adapter.ListPersonAdapter
import pe.edu.myapp.dbHelper.DBHelper
import pe.edu.myapp.model.Person

class MainActivity : AppCompatActivity() {

    private lateinit var db: DBHelper
    private var lstPerson: List<Person> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DBHelper(this)

        refreshData()

        //Event
        btn_add.setOnClickListener {
            val person = Person(
                Integer.parseInt(edt_id.text.toString()),
                edt_name.text.toString(),
                edt_email.text.toString()
            )
            db.addPerson(person)
            refreshData()
        }
        btn_update.setOnClickListener {
            val person = Person(
                Integer.parseInt(edt_id.text.toString()),
                edt_name.text.toString(),
                edt_email.text.toString()
            )
            db.updatePerson(person)
            refreshData()
        }
        btn_delete.setOnClickListener {
            val person = Person(
                Integer.parseInt(edt_id.text.toString()),
                edt_name.text.toString(),
                edt_email.text.toString()
            )
            db.deletePerson(person)
            refreshData()
        }
    }

    private fun refreshData() {
        lstPerson = db.allPerson
        val adapter = ListPersonAdapter(this@MainActivity, lstPerson, edt_id, edt_name, edt_email)
        list_person.adapter = adapter

    }
}
