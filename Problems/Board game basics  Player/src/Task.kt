class Player(val id: Int, val name: String, val hp: Int) {

    companion object {
        val id = 0
        val hp = 100
        fun create(name: String): Player {
           return Player (id, name, hp)
        }
    }
}