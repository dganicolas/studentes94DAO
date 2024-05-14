package org.example.consola

import org.example.DAO.Book

interface IConsola {
    fun showMessage(message: String, lineBreak: Boolean=true)
    fun show(userList: List<Book>?, message: String= "All Books:")
    fun elegirOpcion(): Int
}