class Book(var id: Int, var title: String, var isAvailable: Boolean = true)

class User(var id: Int, var name: String)

class Library {
    private var books = mutableListOf<Book>()

    fun addBook(bookName: Book) {
        books.add(bookName)
    }

    fun showBooks() {
        for (book in books) {
            println("ID:${book.id}, Tittle:${book.title}, Available:${book.isAvailable}")
        }
    }
    fun borrowBooks(bookId: Int,userId:User){
        var book=books.find { it.id == bookId }

        if (book == null){
            println("Book not found!!")
        }
        else if (book.isAvailable){
            book.isAvailable = false
            println("\n${userId.name} borrowed ${book.title}")
        }
        else{
            println("Book Not Available")

        }

    }
}

fun main() {
    val library = Library()
    val book1 = Book(1,"kotlinBasic")
    val book2 = Book(2,"OOP With Kotlin")
    val book3 = Book(3,"Android Development")
    library.addBook(book1)
    library.addBook(book2)
    library.addBook(book3)

    println("The Books are..")

    library.showBooks()
    val user1= User(1,"Sourov")
    library.borrowBooks(11,user1)
    println("Books after borrowing")
    library.showBooks()

}