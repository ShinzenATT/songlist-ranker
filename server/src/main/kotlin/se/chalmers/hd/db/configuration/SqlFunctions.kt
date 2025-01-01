package se.chalmers.hd.db.configuration

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.alias

fun Table.tsVector(name: String) = registerColumn<String>(name, TsVectorColumnType()).databaseGenerated()

infix fun Column<String>.tsQuery(query: String) = TsQuery(this, query)