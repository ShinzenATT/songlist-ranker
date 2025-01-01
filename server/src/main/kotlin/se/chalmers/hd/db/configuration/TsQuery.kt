package se.chalmers.hd.db.configuration

import org.jetbrains.exposed.sql.Expression
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.QueryBuilder
import org.jetbrains.exposed.sql.stringLiteral

class TsQuery(val tsVectorColumn: Expression<String>, val query: String): Op<Boolean>() {
    override fun toQueryBuilder(queryBuilder: QueryBuilder) {
        queryBuilder.append(tsVectorColumn)
        queryBuilder.append(" @@ to_tsquery('swedish', ")
        queryBuilder.append(stringLiteral(sanitizeQuery(query)))
        queryBuilder.append(")")
    }

    companion object {
        fun sanitizeQuery(query: String): String {
            // Replace spaces with '&' for logical AND (to match all terms)
            return query.trim().replace(Regex("\\s+"), " & ")
        }
    }
}