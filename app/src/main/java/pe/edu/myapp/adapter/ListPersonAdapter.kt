package pe.edu.myapp.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import kotlinx.android.synthetic.main.person_layout.view.*
import pe.edu.myapp.R
import pe.edu.myapp.model.Person

class ListPersonAdapter(
    private var activity: Activity,
    private var lstPerson: List<Person>,
    private var edt_id: EditText,
    private var edt_name: EditText,
    private var edt_email: EditText
) : BaseAdapter() {

    private var inflater: LayoutInflater =
        activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
        val rowView: View = inflater.inflate(R.layout.person_layout, null)

        rowView.txt_row_id.text = lstPerson[position].id.toString()
        rowView.txt_name.text = lstPerson[position].name.toString()
        rowView.txt_email.text = lstPerson[position].email.toString()

        //Event
        rowView.setOnClickListener {
            edt_id.setText(rowView.txt_row_id.text.toString())
            edt_name.setText(rowView.txt_name.text.toString())
            edt_email.setText(rowView.txt_email.text.toString())
        }
        return rowView
    }

    override fun getItem(position: Int): Any {
        return lstPerson[position]
    }

    override fun getItemId(position: Int): Long {
        return lstPerson[position].id.toLong()
    }

    override fun getCount(): Int {
        return lstPerson.size
    }

}