package br.ucsal.banco_clinica

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import br.ucsal.banco_clinica.databinding.ActivityInsertBinding
import br.ucsal.banco_clinica.db.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class InsertActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityInsertBinding

    private var selectedType = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.inputDataExame.transformIntoDatePicker(this, "MM/dd/yyyy")

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val items = listOf(
            "Endereço",
            "Empresa",
            "Funcionario",
            "Exame",
            "Tipo Exame",
            "Medico",
            "Atestado",
            "Tipo Atestado"
        )
        val adapter = ArrayAdapter(this, R.layout.dropdown_list_item, items)
        (binding.menu.editText as? AutoCompleteTextView)?.setAdapter(adapter)
        (binding.menu.editText as? AutoCompleteTextView)?.setOnItemClickListener { _, _, i, _ ->

            selectedType = i

            binding.insertEndereco.visibility = View.GONE
            binding.insertEmpresa.visibility = View.GONE
            binding.insertFuncionario.visibility = View.GONE
            binding.insertExame.visibility = View.GONE
            binding.insertTipoExame.visibility = View.GONE
            binding.insertMedico.visibility = View.GONE
            binding.insertAtestado.visibility = View.GONE
            binding.insertTipoAtestado.visibility = View.GONE

            when (i) {
                0 -> {
                    // Endereço
                    binding.insertEndereco.visibility = View.VISIBLE
                }
                1 -> {
                    // Empresa
                    binding.insertEmpresa.visibility = View.VISIBLE
                }
                2 -> {
                    // Funcionario
                    binding.insertFuncionario.visibility = View.VISIBLE
                }
                3 -> {
                    // Exame
                    binding.insertExame.visibility = View.VISIBLE
                }
                4 -> {
                    // Tipo Exame
                    binding.insertTipoExame.visibility = View.VISIBLE
                }
                5 -> {
                    // Medico
                    binding.insertMedico.visibility = View.VISIBLE
                }
                6 -> {
                    // Atestado
                    binding.insertAtestado.visibility = View.VISIBLE
                }
                7 -> {
                    // Tipo Atestado
                    binding.insertTipoAtestado.visibility = View.VISIBLE
                }
                else -> {
                }
            }
        }


        binding.insertFab.setOnClickListener {
            val dao = getDatabase(this).clinicaDao

            when (selectedType) {
                0 -> {
                    // Endereço
                    val rua = binding.inputRuaEndereco.text?.toString() ?: ""
                    val estado = binding.inputEstadoEndereco.text?.toString() ?: ""
                    val cidade = binding.inputCidadeEndereco.text?.toString() ?: ""
                    val cep = binding.inputCepEndereco.text?.toString() ?: ""
                    val numero = binding.inputNumeroEndereco.text?.toString() ?: ""
                    val bairro = binding.inputBairroEndereco.text?.toString() ?: ""
                    val empresaID = binding.inputEmpresaEndereco.text?.toString() ?: ""

                    lifecycleScope.launch {
                        CoroutineScope(Dispatchers.IO).launch {
                            dao.insertEndereco(
                                Endereco(
                                    rua = rua,
                                    estado = estado,
                                    cidade = cidade,
                                    cep = cep,
                                    numero = numero,
                                    bairro = bairro,
                                    empresaId = empresaID.toLong()
                                )
                            )
                        }
                    }
                }
                1 -> {
                    // Empresa
                    val nome = binding.inputNomeEmpresa.text?.toString() ?: ""
                    val cnpj = binding.inputCnpjEmpresa.text?.toString() ?: ""

                    lifecycleScope.launch {
                        CoroutineScope(Dispatchers.IO).launch {
                            dao.insertEmpresa(
                                EmpresaCliente(
                                    nome = nome,
                                    cnpj = cnpj
                                )
                            )
                        }
                    }
                }
                2 -> {
                    // Funcionario
                    val nome = binding.inputNomeFuncionario.text?.toString() ?: ""
                    val cpf = binding.inputCpfFuncionario.text?.toString() ?: ""
                    val empresaId = binding.inputEmpresaFuncionario.text?.toString() ?: ""

                    lifecycleScope.launch {
                        CoroutineScope(Dispatchers.IO).launch {
                            dao.insertFuncionario(
                                Funcionario(
                                    nome = nome,
                                    cpf = cpf,
                                    empresaId = empresaId.toLong()
                                )
                            )
                        }
                    }
                }
                3 -> {
                    // Exame
                    val data = binding.inputDataExame.text?.toString() ?: ""
                    val relatorio = binding.inputRelatorioExame.text?.toString() ?: ""
                    val funcionarioId = binding.inputFuncionarioExame.text?.toString() ?: ""
                    val atestadoId = binding.inputAtestadoExame.text?.toString() ?: ""
                    val medicoId = binding.inputMedicoExame.text?.toString() ?: ""
                    val tipoExameId = binding.inputTipoExameExame.text?.toString() ?: ""

                    val format = SimpleDateFormat("dd/MM/yyyy")

                    lifecycleScope.launch {
                        CoroutineScope(Dispatchers.IO).launch {
                            dao.insertExame(
                                Exame(
                                    data = format.parse(data),
                                    relatorio = relatorio,
                                    funcionarioId = funcionarioId.toLong(),
                                    atestadoId = atestadoId.toLong(),
                                    medicoId = medicoId.toLong(),
                                    tipoExameId = tipoExameId.toLong()
                                )
                            )
                        }
                    }
                }
                4 -> {
                    // Tipo Exame
                    val nome = binding.inputNomeTipoExame.text?.toString() ?: ""

                    lifecycleScope.launch {
                        CoroutineScope(Dispatchers.IO).launch {
                            dao.insertTipoExame(
                                TipoExame(
                                    nome = nome
                                )
                            )
                        }
                    }
                }
                5 -> {
                    // Medico
                    val nome = binding.inputNomeMedico.text?.toString() ?: ""
                    val crm = binding.inputCrmMedico.text?.toString() ?: ""

                    lifecycleScope.launch {
                        CoroutineScope(Dispatchers.IO).launch {
                            dao.insertMedico(
                                Medico(
                                    nome = nome,
                                    crm = crm
                                )
                            )
                        }
                    }
                }
                6 -> {
                    // Atestado
                    val descricao = binding.inputDescricaoAtestado.text?.toString() ?: ""
                    val tipoAtestadoId = binding.inputTipoAtestadoAtestado.text?.toString() ?: ""

                    lifecycleScope.launch {
                        CoroutineScope(Dispatchers.IO).launch {
                            dao.insertAtestado(
                                Atestado(
                                    descricao = descricao,
                                    tipoAtestadoId = tipoAtestadoId.toLong()
                                )
                            )
                        }
                    }
                }
                7 -> {
                    // Tipo Atestado
                    val nome = binding.inputNomeTipoAtestado.text?.toString() ?: ""

                    lifecycleScope.launch {
                        CoroutineScope(Dispatchers.IO).launch {
                            dao.insertTipoAtestado(
                                TipoAtestado(
                                    nome = nome
                                )
                            )
                        }
                    }
                }
                else -> {
                }
            }

            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_insert)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    fun AppCompatEditText.transformIntoDatePicker(
        context: Context,
        format: String,
        maxDate: Date? = null
    ) {
        isFocusableInTouchMode = false
        isClickable = true
        isFocusable = false

        val myCalendar = Calendar.getInstance()
        val datePickerOnDataSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, monthOfYear)
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val sdf = SimpleDateFormat(format, Locale.UK)
                setText(sdf.format(myCalendar.time))
            }

        setOnClickListener {
            DatePickerDialog(
                context, datePickerOnDataSetListener, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).run {
                maxDate?.time?.also { datePicker.maxDate = it }
                show()
            }
        }
    }
}