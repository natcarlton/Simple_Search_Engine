class BankAccount() {
    var withdrawn: Long = 0
    var deposited: Long = 0
    var balance: Long = withdrawn - deposited
    constructor(deposited: Long, withdrawn: Long, balance: Long) {

        this.deposited = deposited
        this.withdrawn = withdrawn
        this.balance = balance
    }
}