package se.chalmers.hd.db.configuration

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ColumnType
import org.jetbrains.exposed.sql.DoubleColumnType
import org.jetbrains.exposed.sql.ExpressionWithColumnType
import org.jetbrains.exposed.sql.IColumnType
import org.jetbrains.exposed.sql.QueryBuilder
import org.jetbrains.exposed.sql.stringLiteral

class TsRank(private val tsVectorColumn: Column<String>, private val query: String) : ExpressionWithColumnType<Double>() {
    override fun toQueryBuilder(queryBuilder: QueryBuilder) {
        queryBuilder.append("ts_rank(")
        queryBuilder.append(tsVectorColumn)
        queryBuilder.append(", to_tsquery('swedish', ")
        queryBuilder.append(stringLiteral(sanitizeQuery(query)))
        queryBuilder.append("))")
    }

    override val columnType: IColumnType<Double> get() = DoubleColumnType()

    companion object {
        fun sanitizeQuery(query: String): String {
            // Replace spaces with '&' for logical AND (to match all terms)
            return query.trim().replace(Regex("\\s+"), " & ")
        }
    }
}