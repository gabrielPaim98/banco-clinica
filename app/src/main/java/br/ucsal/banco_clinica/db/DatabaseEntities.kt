package br.ucsal.banco_clinica.db

import androidx.room.*
import java.util.*

/**
 * https://developer.android.com/training/data-storage/room/relationships
 */


/**
 * Endereco
 */
@Entity(
    foreignKeys = [
        ForeignKey(
            entity = EmpresaCliente::class,
            parentColumns = ["id"],
            childColumns = ["empresa_id"],
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class Endereco(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val rua: String,
    val estado: String,
    val cidade: String,
    val cep: String,
    val numero: String,
    val bairro: String,
    @ColumnInfo(name = "empresa_id", index = true) val empresaId: Long
)


/**
 * Empresa
 */
@Entity(tableName = "empresa_cliente", indices = [Index(value = ["cnpj", "nome"], unique = true)])
data class EmpresaCliente(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val nome: String,
    val cnpj: String,
)


/**
 * Funcionario
 */
@Entity(
    foreignKeys = [
        ForeignKey(
            entity = EmpresaCliente::class,
            parentColumns = ["id"],
            childColumns = ["empresa_id"],
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class Funcionario(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val nome: String,
    val cpf: String,
    @ColumnInfo(name = "empresa_id", index = true) val empresaId: Long
)


/**
 * Exame
 */
@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Funcionario::class,
            parentColumns = ["id"],
            childColumns = ["funcionario_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Atestado::class,
            parentColumns = ["id"],
            childColumns = ["atestado_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Medico::class,
            parentColumns = ["id"],
            childColumns = ["medico_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = TipoExame::class,
            parentColumns = ["id"],
            childColumns = ["tipo_exame_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Exame(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val data: Date,
    val relatorio: String,
    @ColumnInfo(name = "funcionario_id", index = true) val funcionarioId: Long,
    @ColumnInfo(name = "atestado_id", index = true) val atestadoId: Long,
    @ColumnInfo(name = "medico_id", index = true) val medicoId: Long,
    @ColumnInfo(name = "tipo_exame_id", index = true) val tipoExameId: Long
)

@Entity(
    tableName = "tipo_exame"
)
data class TipoExame(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val nome: String
)


/**
 * Medico
 */
@Entity
data class Medico(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val nome: String,
    val crm: String
)

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = TipoAtestado::class,
            parentColumns = ["id"],
            childColumns = ["tipo_atestado_id"],
            onDelete = ForeignKey.CASCADE
        ),
    ]
)


/**
 * Atestado
 */
data class Atestado(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val descricao: String,
    @ColumnInfo(name = "tipo_atestado_id", index = true) val tipoAtestadoId: Long
)

@Entity(tableName = "tipo_atestado")
data class TipoAtestado(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val nome: String,
)

/**
 * https://developer.android.com/training/data-storage/room/referencing-data
 */
class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}