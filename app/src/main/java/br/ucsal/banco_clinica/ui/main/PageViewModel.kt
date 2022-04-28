package br.ucsal.banco_clinica.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.ucsal.banco_clinica.db.ClinicaDatabase
import br.ucsal.banco_clinica.db.EmpresaCliente
import br.ucsal.banco_clinica.db.Funcionario
import br.ucsal.banco_clinica.db.Medico

class PageViewModel : ViewModel() {

    private lateinit var _db: ClinicaDatabase

    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> = Transformations.map(_index) {
        dbType = it.toDbClass()
        dbType?.javaClass?.simpleName
    }

    fun setIndex(index: Int) {
        _index.value = index
    }

    fun setDb(db: ClinicaDatabase) {
        _db = db
    }

    private var dbType: Any? = null

    private val _dataList = MutableLiveData<List<Any>>()
    val dataList: LiveData<out List<Any>>
        get() {
            return when (dbType) {
                is EmpresaCliente -> _db.clinicaDao.getAllEmpresas()
                is Funcionario -> _db.clinicaDao.getAllFuncionarios()
                is Medico -> _db.clinicaDao.getAllMedicos()
                else -> MutableLiveData(emptyList())
            }
        }
}

fun Int.toDbClass(): Any {
    return when (this) {
        1 -> EmpresaCliente(0, "", "")
        2 -> Funcionario(0, "", "", 0)
        3 -> Medico(0, "", "")
        else -> {
        }
    }
}
