import org.example.DAO.Book
import org.example.DAO.IBookDao
import org.example.consola.IConsola
import java.sql.SQLException
import javax.sql.DataSource

class BookDaoDB(
    private val dataSource: DataSource,
    private val consola: IConsola
) : IBookDao {

    override fun insert(book: Book): Book? {
        val sql = "INSERT INTO Book (id,titulo, autor, anopublicacion) VALUES (?,?, ?, ?)"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setInt(1,book.ID)
                    stmt.setString(2, book.titulo)
                    stmt.setString(3, book.autor)
                    stmt.setString(4, book.anoPublicacion.toString())
                    val rs = stmt.executeUpdate()
                    if (rs == 1) {
                        book
                    } else {
                        consola.showMessage("error insert query failed! ($rs records inserted)")
                        null
                    }
                }
            }
        } catch (e: SQLException) {
            consola.showMessage("1 :error* insert query failed! (${e.message})")
            null
        }
    }

    override fun selectById(id: Int): Book? {
        val sql = "SELECT * FROM Book WHERE id = ?"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, id.toString())
                    val rs = stmt.executeQuery()
                    if (rs.next()) {
                        Book(
                            ID = rs.getInt("ID"),
                            titulo = rs.getString("titulo"),
                            autor = rs.getString("autor"),
                            anoPublicacion = rs.getInt("anopublicacion"),
                            estado = rs.getBoolean("estado")
                        )
                    } else {
                        null
                    }
                }
            }
        } catch (e: SQLException) {
            consola.showMessage("3: error* insert query failed! (${e.message})")
            null
        }
    }

    override fun getAll(): List<Book>? {
        val sql = "SELECT * FROM book"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    val rs = stmt.executeQuery()
                    val libro = mutableListOf<Book>()
                    while (rs.next()) {
                        libro.add(
                            Book(
                                ID = rs.getInt("ID"),
                                titulo = rs.getString("titulo"),
                                autor = rs.getString("autor"),
                                anoPublicacion = rs.getInt("anopublicacion"),
                                estado = rs.getBoolean("estado")
                            )
                        )
                    }
                    libro
                }
            }
        } catch (e: SQLException) {
            consola.showMessage("5:  error* insert query failed! (${e.message})")
            null
        }
    }

    override fun update(libro: Book): Book? {
        val sql = "UPDATE book SET autor = ?, anopublicacion = ?, titulo = ?, estado = ? WHERE id = ?"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, libro.autor)
                    stmt.setInt(2, libro.anoPublicacion)
                    stmt.setString(3, libro.titulo)
                    stmt.setBoolean(4,libro.estado)
                    stmt.setInt(5, libro.ID)
                    val rs = stmt.executeUpdate()
                    if (rs == 1) {
                        libro
                    } else {
                        consola.showMessage("error insert query failed! ($rs records inserted)")
                        null
                    }
                }
            }
        } catch (e: SQLException) {
            consola.showMessage("6: error* insert query failed! (${e.message})")
            null
        }
    }

    override fun deleteById(id: Int): Boolean {
        val sql = "DELETE FROM book WHERE ID = ?"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setInt(1, id)
                    (stmt.executeUpdate() == 1)
                }
            }
        } catch (e: SQLException) {
            consola.showMessage("7: error* insert query failed! (${e.message})")
            false
        }
    }
}