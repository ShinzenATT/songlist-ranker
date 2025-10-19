package se.chalmers.hd.db.configuration

import org.jetbrains.exposed.v1.core.Expression
import org.jetbrains.exposed.v1.core.Op
import org.jetbrains.exposed.v1.core.QueryBuilder
import org.jetbrains.exposed.v1.core.stringLiteral

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
            return query.trim().replace(Regex("\\s+"), "+") + ":*"
        }
    }
}