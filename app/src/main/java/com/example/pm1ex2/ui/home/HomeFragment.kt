package com.example.pm1ex2.ui.home

import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pm1ex2.ItemList
import com.example.pm1ex2.MainActivity
import com.example.pm1ex2.R
import com.example.pm1ex2.RecyclerAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var img = R.drawable.ic_dashboard_black_24dp;
        var r = view.findViewById<RecyclerView>(R.id.resItems);
        var manager = LinearLayoutManager(view.context);
        r.layoutManager = manager;
//        itemLists += (ItemList("Power Rangers Dino Fury", "Cuando un ejército de poderosos seres alienígenas se desata en la Tierra, amenazando la vida tal como la conocemos, un nuevo equipo de Power Rangers, impulsado por el poder prehistórico de los dinosaurios, es reclutado para hacer frente a la amenaza.", img));
//        itemLists += (ItemList("Power Rangers Beast Morphers", "Power Rangers Beast Morphers son las temporadas vigésima sexta y vigésima séptima de Power Rangers , que sirven como una adaptación de la serie de Super Sentai Tokumei Sentai Go-Busters.", img));
//        itemLists += (ItemList("Power Rangers Super Ninja Steel", "Power Rangers Super Ninja Steel es la vigésimo quinta temporada de Power Rangers y la segunda temporada de Power Rangers Ninja Steel.", img));
//        itemLists += (ItemList("Power Rangers Ninja Steel", "Power Rangers Ninja Steel es la vigésimo cuarta temporada de Power Rangers , que sirve como una adaptación de la serie Super Sentai Shuriken Sentai Ninninger.", img));
//        itemLists += (ItemList("Power Rangers Dino Super Charge", "Power Rangers Dino Super Charge es la vigésimo tercera temporada de Power Rangers y la segunda temporada de Power Rangers Dino Charge , continuando su adaptación de la serie Super Sentai Zyuden Sentai Kyoryuger.", img));
//        itemLists += (ItemList("Power Rangers Dino Charge", "Power Rangers Dino Charge es la vigésimo segunda temporada de Power Rangers , que sirve como una adaptación de la primera mitad de la serie Super Sentai Zyuden Sentai Kyoryuger.", img));
//        itemLists += (ItemList("Power Rangers Super Megaforce", "Power Rangers Super Megaforce es la vigésimo primera temporada de Power Rangers y la secuela de Power Rangers Megaforce .", img));
//        MainActivity.AdapterMonitor.AddData("Power Rangers Megaforce", "Power Rangers Megaforce es la vigésima temporada de Power Rangers, basada en la serie Super Sentai Tensou Sentai Goseiger . ", img);

        r.adapter = MainActivity.AdapterMonitor;
        view.findViewById<FloatingActionButton>(R.id.addItem).setOnClickListener{
            AgregarItem(MainActivity.Context)
        }
    }

    fun AgregarItem(context: Context){
        val alert = AlertDialog.Builder(context)
        alert.setTitle("Agregar generacion PorweRanger")
        alert.setMessage("Ingrese los datos.")
        val layout = LinearLayout(context)
        layout.orientation = LinearLayout.VERTICAL
        val title = EditText(context)
        title.setHint("Titulo de la saga")
        title.inputType = InputType.TYPE_CLASS_TEXT
        layout.addView(title)

        val desc = EditText(context)
        desc.setHint("Descripcion")
        desc.inputType = InputType.TYPE_CLASS_TEXT
        layout.addView(desc)

        alert.setView(layout)
        alert.setPositiveButton("Proceed") { _, _ ->
            val title = title.text.toString()
            val description = desc.text.toString()
            val img = getImage()
            MainActivity.AdapterMonitor.AddData(title,description,img)
            Snackbar.make(this.requireView(),"Agregado",Snackbar.LENGTH_SHORT).show();
        }
        alert.setNegativeButton("Cancel") { dialog, _  ->
            dialog.dismiss()
        }
        alert.setCancelable(false)
        alert.show()
    }

    fun getImage() : Int{
        var img = R.drawable.dino_charge
        var r = (0..7).random()
        when(r){
            0-> img = R.drawable.dino_fury
            1-> img = R.drawable.dino_scharge
            2-> img = R.drawable.mforce
            3-> img = R.drawable.morphes
            4-> img = R.drawable.ninja_steel
            5-> img = R.drawable.ninja_steel_1
            6-> img = R.drawable.super_mforce
            7 -> img = R.drawable.dino_charge
        }
        return img;
    }
}