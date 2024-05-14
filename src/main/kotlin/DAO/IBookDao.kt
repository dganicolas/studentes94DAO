package org.example.DAO

import org.example.DAO.Book

interface IBookDao {
    fun insert(book: Book): Book?
    fun selectById(id: Int): Book?
    fun getAll(): List<Book>?
    fun update(libro: Book): Book?
    fun deleteById(id: Int): Boolean
}