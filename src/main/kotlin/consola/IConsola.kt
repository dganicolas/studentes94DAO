package org.example.consola

import org.example.DAO.Book

interface Iconsola {
    fun showMessage(message: String, lineBreak: Boolean=true)
    fun show(userList: List<Book>?,message: String = "All book:")
    fun elegirOpcion(): Int
}