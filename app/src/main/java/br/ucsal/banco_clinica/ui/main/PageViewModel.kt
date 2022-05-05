package br.ucsal.banco_clinica.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import br.ucsal.banco_clinica.db.*
import java.util.*

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
                4 -> _db.clinicaDao.getAllAtestados()
                5 -> _db.clinicaDao.getAllExames()
                6 -> _db.clinicaDao.getAllEnderecos()
                7 -> _db.clinicaDao.getAllTipoExames()
                8 -> _db.clinicaDao.getAllTipoAtestados()
                else -> MutableLiveData(emptyList())
            }
        }
}

private fun Int.toDbClass(): Any {
    return when (this) {
        1 -> EmpresaCliente(0, "", "")
        2 -> Funcionario(0, "", "", 0)
        3 -> Medico(0, "", "")
        4 -> Atestado(0L, "", 0L)
        5 -> Exame(0L, Date(), "", 0L, 0L, 0L, 0L)
        6 -> Endereco(0L, "", "", "", "", "", "", 0L)
        7 -> TipoExame(0L, "")
        8 -> TipoAtestado(0L, "")
        else -> {
        }
    }
}
