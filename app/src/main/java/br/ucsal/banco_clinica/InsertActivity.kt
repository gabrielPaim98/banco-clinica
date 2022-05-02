package br.ucsal.banco_clinica

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import br.ucsal.banco_clinica.databinding.ActivityInsertBinding
import com.google.android.material.snackbar.Snackbar


class InsertActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityInsertBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val items = listOf("Endereço", "Empresa", "Funcionario", "Exame", "Medico", "Atestado")
        val adapter = ArrayAdapter(this, R.layout.dropdown_list_item, items)
        (binding.menu.editText as? AutoCompleteTextView)?.setAdapter(adapter)
        (binding.menu.editText as? AutoCompleteTextView)?.setOnItemClickListener { adapterView, view, i, l ->
            Log.d("insert", "i: $i, l: $l")
            binding.insertPlaceholder.removeAllViews()

            val layoutId = when (i) {
                0 -> {
                    //TODO: Endereço
                    R.layout.fragment_first
                }
                1 -> {
                    //TODO: Empresa
                    R.layout.fragment_second
                }
                2 -> {
                    //TODO: Funcionario
                    R.layout.fragment_first
                }
                3 -> {
                    //TODO: Exame
                    R.layout.fragment_first
                }
                4 -> {
                    //TODO: Medico
                    R.layout.fragment_first
                }
                5 -> {
                    //TODO: Atestado
                    R.layout.fragment_first
                }
                else -> 0
            }

            val vi =
                applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val v: View = vi.inflate(layoutId, null)
            binding.insertPlaceholder.addView(
                v,
                0,
                ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            )
        }


        binding.insertFab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_insert)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}