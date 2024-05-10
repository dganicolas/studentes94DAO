package DAO

import org.example.DAO.Book
import java.util.*

interface IBookDAO {
    fun create(book: Book): Book?
    fun getAll(): List<Book>?
    fun getById(titulo: String): Book?
    fun update(book: Book): Book?
    fun delete(titulo:String): Boolean
}