package org.example.DAO

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.io.File

class BookDaoFile: IBookDao {
    val gson =Gson()
    val file = File("data.json")
    val jsonString = file.readText()
    override fun insert(book: Book): Book? {
        return try {
            val currentBooks: MutableList<Book> = gson.fromJson(jsonString, Array<Book>::class.java).toMutableList()
            currentBooks.add(book)
            val jsonString = gson.toJson(currentBooks)
            file.writeText(jsonString)
            book
        }catch (e: JsonSyntaxException){
            null
        }

    }

    override fun getAll(): List<Book>? {
        return try {
            val books: Array<Book> = gson.fromJson(jsonString, Array<Book>::class.java)
            return books.toList()
        }catch (e: JsonSyntaxException){
            null
        }

    }

    override fun selectById(id: Int): Book? {
        return try {
            val currentBooks: MutableList<Book> = gson.fromJson(jsonString, Array<Book>::class.java).toMutableList()
            val book = currentBooks.find {
                it.ID == id
            }

            book
        }catch (e: JsonSyntaxException){
            null
        }
    }

    override fun update(book: Book): Book? {
        return try {
            var contador = 0
            val currentBooks: MutableList<Book> = gson.fromJson(jsonString, Array<Book>::class.java).toMutableList()
            currentBooks.forEach {
                if (it.ID == book.ID){
                    currentBooks[contador]=book
                    return book
                }
                contador++
            }
            null
        }catch (e: JsonSyntaxException){
            null
        }
    }

    override fun deleteById(id: Int): Boolean {
        return try {
            var contador = 0
            val currentBooks: MutableList<Book> = gson.fromJson(jsonString, Array<Book>::class.java).toMutableList()
            currentBooks.forEach {
                if (it.ID == id){
                    currentBooks.remove(it)
                    return true
                }
                contador++
            }

            true
        }catch (e: JsonSyntaxException){
            false
        }
    }
    }