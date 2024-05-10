package org.example.DAO

data class Book(
    override val titulo: String,
    override val autor: String,
    override val anoPublicacion: Int)
    :IBook