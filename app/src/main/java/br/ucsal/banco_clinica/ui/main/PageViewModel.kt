package br.ucsal.banco_clinica.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import br.ucsal.banco_clinica.db.ClinicaDatabase
import br.ucsal.banco_clinica.db.EmpresaCliente
import br.ucsal.banco_clinica.db.Funcionario
import br.ucsal.banco_clinica.db.Medico

class PageViewModel : ViewModel() {

    private lateinit var _db: ClinicaDatabase

    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> = Transformations.map(_index) {
        it.toDbClass().javaClass.simpleName
    }

    fun setIndex(index: Int) {
        _index.value = index
    }

    fun setDb(db: ClinicaDatabase) {
        _db = db
    }

    val dataList: LiveData<out List<Any>>
        get() {
            return when (_index.value) {
                1 -> _db.clinicaDao.getAllEmpresas()
                2 -> _db.clinicaDao.getAllFuncionarios()
                3 -> _db.clinicaDao.getAllMedicos()
                else -> MutableLiveData(emptyList())
            }
        }
}

private fun Int.toDbClass(): Any {
    return when (this) {
        1 -> EmpresaCliente(0, "", "")
        2 -> Funcionario(0, "", "", 0)
        3 -> Medico(0, "", "")
        else -> {
        }
    }
}
