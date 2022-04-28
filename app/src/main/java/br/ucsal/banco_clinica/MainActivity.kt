package br.ucsal.banco_clinica

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.lifecycleScope
import br.ucsal.banco_clinica.ui.main.SectionsPagerAdapter
import br.ucsal.banco_clinica.databinding.ActivityMainBinding
import br.ucsal.banco_clinica.db.ClinicaDatabase
import br.ucsal.banco_clinica.db.EmpresaCliente
import br.ucsal.banco_clinica.db.getDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: ClinicaDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)

        db = getDatabase(this)

        populateDB()
    }

    private fun populateDB() {
        lifecycleScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                db.clinicaDao.insertEmpresa(
                    EmpresaCliente(
                        id = 0,
                        nome = "UCSal",
                        cnpj = "33717125390800"
                    )
                )
            }
        }
    }
}