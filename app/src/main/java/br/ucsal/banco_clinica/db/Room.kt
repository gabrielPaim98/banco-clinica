package br.ucsal.banco_clinica.db

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ClinicaDao {
    /**
     * Endereco
     */
    @Query("select * from endereco")
    fun getAllEnderecos(): LiveData<List<Endereco>>

    @Query("select * from endereco where id = :enderecoId")
    fun getEndereco(enderecoId: Long): LiveData<Endereco>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEndereco(vararg endereco: Endereco)

    @Update
    fun updateEndereco(vararg endereco: Endereco)

    @Delete
    fun deleteEndereco(vararg endereco: Endereco)


    /**
     * Empresa
     */
    @Query("select * from empresa_cliente")
    fun getAllEmpresas(): LiveData<List<EmpresaCliente>>

    @Query("select * from empresa_cliente where id = :empresaId")
    fun getEmpresa(empresaId: Long): LiveData<EmpresaCliente>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmpresa(vararg empresaCliente: EmpresaCliente)

    @Update
    fun updateEmpresa(vararg empresaCliente: EmpresaCliente)

    @Delete
    fun deleteEmpresa(vararg empresaCliente: EmpresaCliente)

    /**
     * Funcionario
     */
    @Query("select * from funcionario")
    fun getAllFuncionarios(): LiveData<List<Funcionario>>

    @Query("select * from funcionario where id = :funcionarioId")
    fun getFuncionario(funcionarioId: Long): LiveData<Funcionario>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFuncionario(vararg funcionario: Funcionario)

    @Update
    fun updateFuncionario(vararg funcionario: Funcionario)

    @Delete
    fun deleteFuncionario(vararg funcionario: Funcionario)

    /**
     * Exame
     */
    @Query("select * from exame")
    fun getAllExames(): LiveData<List<Exame>>

    @Query("select * from exame where id = :exameId")
    fun getExame(exameId: Long): LiveData<Exame>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExame(vararg exame: Exame)

    @Update
    fun updateExame(vararg exame: Exame)

    @Delete
    fun deleteExame(vararg exame: Exame)

    @Query("select * from tipo_exame")
    fun getAllTipoExames(): LiveData<List<TipoExame>>

    @Query("select * from tipo_exame where id = :tipoExameId")
    fun getTipoExame(tipoExameId: Long): LiveData<TipoExame>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTipoExame(vararg tipoExame: TipoExame)

    @Update
    fun updateTipoExame(vararg tipoExame: TipoExame)

    @Delete
    fun deleteTipoExame(vararg tipoExame: TipoExame)

    /**
     * Medico
     */
    @Query("select * from medico")
    fun getAllMedicos(): LiveData<List<Medico>>

    @Query("select * from medico where id = :medicoId")
    fun getMedico(medicoId: Long): LiveData<Medico>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMedico(vararg medico: Medico)

    @Update
    fun updateMedico(vararg medico: Medico)

    @Delete
    fun deleteMedico(vararg medico: Medico)

    /**
     * Atestado
     */
    @Query("select * from atestado")
    fun getAllAtestados(): LiveData<List<Atestado>>

    @Query("select * from atestado where id = :atestadoId")
    fun getAtestado(atestadoId: Long): LiveData<Atestado>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAtestado(vararg atestado: Atestado)

    @Update
    fun updateAtestado(vararg atestado: Atestado)

    @Delete
    fun deleteAtestado(vararg atestado: Atestado)

    @Query("select * from tipo_atestado")
    fun getAllTipoAtestados(): LiveData<List<TipoAtestado>>

    @Query("select * from tipo_atestado where id = :tipoAtestadoId")
    fun getTipoAtestado(tipoAtestadoId: Long): LiveData<TipoAtestado>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTipoAtestado(vararg tipoAtestado: TipoAtestado)

    @Update
    fun updateTipoAtestado(vararg tipoAtestado: TipoAtestado)

    @Delete
    fun deleteTipoAtestado(vararg tipoAtestado: TipoAtestado)
}


@TypeConverters(Converters::class)
@Database(
    entities = [
        Endereco::class,
        EmpresaCliente::class,
        Funcionario::class,
        Exame::class,
        TipoExame::class,
        Medico::class,
        Atestado::class,
        TipoAtestado::class
    ], version = 1
)
abstract class ClinicaDatabase : RoomDatabase() {
    abstract val clinicaDao: ClinicaDao
}

private lateinit var INSTANCE: ClinicaDatabase

fun getDatabase(context: Context): ClinicaDatabase {
    synchronized(ClinicaDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                ClinicaDatabase::class.java,
                "clinica"
            ).build()
        }
    }
    return INSTANCE
}