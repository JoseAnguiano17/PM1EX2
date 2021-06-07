package com.example.pm1ex2.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pm1ex2.MainActivity
import com.example.pm1ex2.R
import com.google.android.material.snackbar.Snackbar
import kotlin.time.ExperimentalTime
import kotlin.time.TimeSource

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var Root : View
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        Root = root
        var spinner = root.findViewById<Spinner>(R.id.dropSelect);
        var list_of_items = ArrayList<String>();
        MainActivity.AdapterMonitor.getItems().forEach {item ->
            list_of_items.add(item.titulo);
        }
        val aa = ArrayAdapter(root.context, android.R.layout.simple_spinner_item, list_of_items.toArray())
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner!!.setAdapter(aa)

        root.findViewById<Button>(R.id.btnSend).setOnClickListener{
            // Create the text message with a string
            var text = ""
            if(root.findViewById<CheckBox>(R.id.ckAll).isChecked){
                var i = 1;
                MainActivity.AdapterMonitor.getItems().forEach {item ->
                    text += i.toString() + "- "+ item.titulo + "\n\r"
                    text += "\t"+item.descripcion + "\n\r"
                    i++;
                }
            } else {
                MainActivity.AdapterMonitor.getItems().forEach {item ->
                    if(item.titulo.toString() == spinner.selectedItem.toString()){
                        text += "1 - "+ item.titulo + "\n\r"
                        text += "\t"+item.descripcion
                    }
                }
            }
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, text)
                type = "text/plain"
            }
            Toast.makeText(this.context,"Seleccione una aplicación", Toast.LENGTH_LONG).show();
            startActivity(sendIntent);
        };
        root.findViewById<CheckBox>(R.id.ckAll).setOnCheckedChangeListener{_,_->
            if(root.findViewById<CheckBox>(R.id.ckAll).isChecked){
                spinner.setEnabled(false)
            } else {
                spinner.setEnabled(true)
            }
        }
        return root
    }
}