class Book(val id: Int, val title: String) {
    private var isAvailable: Boolean = true

    fun displayInfo() {
        println("ID: $id, Title: $title, Available: $isAvailable")
    }

    fun borrow(): Boolean {
        return if (isAvailable) {
            isAvailable = false
            true
        } else {
            false
        }
    }

    fun returnBook() {
        isAvailable = true
    }
}

abstract class Person(val id: Int, val name: String) {
    abstract fun getRole(): String
}

interface Borrowable {
    fun borrowBook(book: Book)
    fun returnBook(book: Book)
}

class User(id: Int, name: String) : Person(id, name), Borrowable {

    override fun getRole(): String {
        return "User"
    }

    override fun borrowBook(book: Book) {
        if (book.borrow()) {
            println("$name borrowed the book: ${book.title}")
        } else {
            println("Sorry! ${book.title} is not available")
        }
    }

    override fun returnBook(book: Book) {
        book.returnBook()
        println("$name returned the book: ${book.title}")
    }
}

class Librarian(id: Int, name: String) : Person(id, name) {

    override fun getRole(): String {
        return "Librarian"
    }

    fun addBook(library: Library, book: Book) {
        library.addBook(book)
        println("Book added: ${book.title}")
    }
}

class Library {
    private val books = mutableListOf<Book>()

    fun addBook(book: Book) {
        books.add(book)
    }

    fun showBooks() {
        println("\nLibrary Books:")
        for (book in books) {
            book.displayInfo()
        }
    }
}

fun main(){
    val library = Library()
    val librarian=Librarian(1, "Dipto")
    val user=User(101,"sourov")
    val book1=Book(1,"kotlin Bascis")
    val book2=Book(2,"OOP with kotlin")
    librarian.addBook(library,book1)
    librarian.addBook(library,book2)
    library.showBooks()

    println()
    user.borrowBook(book1)
    user.borrowBook(book1)
    user.returnBook(book1)
    user.borrowBook(book1)

    println("\n Final library books state:")
    library.showBooks()
}
