package se.chalmers.hd.db.configuration

import org.jetbrains.exposed.v1.core.Column
import org.jetbrains.exposed.v1.core.Table

fun Table.tsVector(name: String) = registerColumn<String>(name, TsVectorColumnType()).databaseGenerated()

infix fun Column<String>.tsQuery(query: String) = TsQuery(this, query)