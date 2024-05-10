import DAO.IBookDAO
import org.example.DAO.Book
import java.sql.SQLException
import java.util.*
import javax.sql.DataSource

class UserDAOH2(
    private val dataSource: DataSource,
    private val consola: Iconsola
) : IBookDAO {

    override fun create(book: Book): Book? {
        val sql = "INSERT INTO Book (titulo, autor, anopublicacion) VALUES (?, ?, ?)"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, book.titulo)
                    stmt.setString(2, book.autor)
                    stmt.setString(3, book.anoPublicacion.toString())
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
            consola.showMessage("error* insert query failed! (${e.message})")
            null
        }
    }

    override fun getById(titulo: String): Book? {
        val sql = "SELECT * FROM Book WHERE titulo = ?"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, titulo)
                    val rs = stmt.executeQuery()
                    if (rs.next()) {
                        Book(
                            titulo = rs.getString(rs.getString("id")),
                            autor = rs.getString("name"),
                            anoPublicacion = rs.getString("email").toInt()
                        )
                    } else {
                        null
                    }
                }
            }
        } catch (e: SQLException) {
            consola.showMessage("error* insert query failed! (${e.message})")
            null
        }
    }

    override fun getAll(): List<Book>? {
        val sql = "SELECT * FROM book"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    val rs = stmt.executeQuery()
                    val users = mutableListOf<Book>()
                    while (rs.next()) {
                        users.add(
                            Book(
                                titulo = rs.getString(rs.getString("id")),
                                autor = rs.getString("name"),
                                anoPublicacion = rs.getString("email").toInt()
                            )
                        )
                    }
                    users
                }
            }
        } catch (e: SQLException) {
            consola.showMessage("error* insert query failed! (${e.message})")
            null
        }
    }

    override fun update(book: Book): Book? {
        val sql = "UPDATE tuser SET autor = ?, anopublicacion = ? WHERE titulo = ?"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, book.autor)
                    stmt.setString(2, book.anoPublicacion.toString())
                    stmt.setString(3, book.titulo)
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
            consola.showMessage("error* insert query failed! (${e.message})")
            null
        }
    }

    override fun delete(titulo: String): Boolean {
        val sql = "DELETE FROM book WHERE titulo = ?"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, titulo)
                    (stmt.executeUpdate() == 1)
                }
            }
        } catch (e: SQLException) {
            consola.showMessage("error* insert query failed! (${e.message})")
            false
        }
    }
}