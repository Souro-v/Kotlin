interface AccountOperations {
    fun deposit(amount: Double)
    fun withdraw(amount: Double): Boolean
}

abstract class BankAccount(
    val accountNumber: String,
    protected var balance: Double = 0.0
) : AccountOperations {

    override fun deposit(amount: Double) {
        if (amount > 0) {
            balance += amount
            println("Deposited $amount to $accountNumber")
        }
    }

    abstract fun canWithdraw(amount: Double): Boolean

    override fun withdraw(amount: Double): Boolean {
        if (canWithdraw(amount)) {
            balance -= amount
            println("Withdrawn $amount from $accountNumber")
            return true
        } else {
            println("Insufficient balance in $accountNumber")
            return false
        }
    }

    fun show() {

        println("Account: $accountNumber, Balance: $balance")
    }
}

class SavingsAccount(
    accountNumber: String,
    initialBalance: Double = 0.0
) : BankAccount(accountNumber, initialBalance) {

    private val minBalance = 100.0

    override fun canWithdraw(amount: Double): Boolean {
        return (balance - amount) >= minBalance
    }
}

class Bank {
    private val accounts = mutableListOf<BankAccount>()

    fun addAccount(account: BankAccount) {
        accounts.add(account)
        println("Account ${account.accountNumber} added to bank")
    }

    fun showAll() {
        println("\nFinal account details:")
        accounts.forEach { it.show() }
    }
}

fun main() {
    val bank = Bank()

    val acc1 = SavingsAccount("101")
    val acc2 = SavingsAccount("102", 0.0)

    bank.addAccount(acc1)
    bank.addAccount(acc2)

    println()

    acc1.deposit(500.0)
    acc1.withdraw(200.0)
    acc1.withdraw(300.0)

    bank.showAll()
}