package org.example

import BookDaoDB
import org.example.DAO.Book
import org.example.DBConnection.DataSourceFactory
import org.example.consola.Consola

fun main() {
    val consola = Consola()
    var libro1 = Book(1,"funcion lamdba","diego can",2012,false)
    val queTipoElegir = consola.elegirOpcion()
    val baseDeDatos = if(queTipoElegir == 1){
        DataSourceFactory.getDS(DataSourceFactory.DataSourceType.HIKARI)
    }else{
        DataSourceFactory.getDS(DataSourceFactory.DataSourceType.JSON)
    }

    val bookDao =BookDaoDB(baseDeDatos,consola)
    val eliminaLibro = bookDao.deleteById(libro1.ID)
    consola.showMessage("el libro${if(eliminaLibro)"";else " no"} ha sido borrado")
    val crearlibro = bookDao.insert(libro1)
    consola.showMessage("el libro ha sido creado exitosamente $crearlibro")
    val libroEscogido = bookDao.selectById(libro1.ID)
    consola.showMessage("este es el libro escogido $libroEscogido")
    var libros = bookDao.getAll()
    consola.show(libros)
    libro1 = Book(1,"XD","diego can",2012,false)
    val libroactualizado = bookDao.update(libro1)
    consola.showMessage("este es el libro actualizado $libroactualizado")
    libros = bookDao.getAll()
    consola.show(libros)

}