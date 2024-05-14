package org.example.consola

import org.example.DAO.Book

class Consola : IConsola {

    override fun showMessage(message:String, lineBreak:Boolean){
        if(lineBreak) println(message)
        else print(message)
    }

    override fun show(userList: List<Book>?,message: String){
        if(userList != null){
            if(userList.isNotEmpty()){
                showMessage(message)
                userList.forEachIndexed { index, userEntity ->
                    showMessage("\t${index+1}. $userEntity")
                }
            }else{
                showMessage("No Books Found")
            }
        }

    }

    override fun elegirOpcion(): Int {
        showMessage("¿que modo iniciar?\n1.Bases De Datos\n2.Fichero Json")
        var opcion:Int?
        do{
            opcion = readln().toIntOrNull()
            if (opcion != 1 && opcion != 2){
                showMessage("ERROR** elige una opcion valida")
            }
        }while (opcion != 1 && opcion != 2)
        return opcion
    }
}