package se.chalmers.hd.db.configuration

import org.jetbrains.exposed.sql.ColumnType
import org.postgresql.util.PGobject

class TsVectorColumnType: ColumnType<String>(nullable = true) {
    override fun sqlType(): String = "tsvector"

    override fun valueFromDB(value: Any): String? {
        return when (value) {
            is String -> value
            is PGobject -> value.value
            else -> null
        }
    }

    override fun valueToDB(value: String?): Any? = PGobject().apply {
            type = "tsvector"
            this.value = value
        }.value

    override fun notNullValueToDB(value: String): Any = PGobject().apply {
        type = "tsvector"
        this.value = value
    }.value!!

    override fun nonNullValueToString(value: String): String = "'$value'"
}