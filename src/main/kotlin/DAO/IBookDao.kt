package DAO

import org.example.DAO.Book
import java.util.*

interface IBookDAO {
    fun insert(book: Book): Book?
    fun getAll(): List<Book>?
    fun selectById(id: Int): Book?
    fun update(book: Book): Book?
    fun deleteById(id:Int): Boolean
}