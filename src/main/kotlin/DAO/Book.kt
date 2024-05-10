package org.example.DAO

data class Book(
    override val ID: Int,
    override val titulo: String,
    override val autor: String,
    override val anoPublicacion: Int,
    override var estado: Boolean
)
    :IBook