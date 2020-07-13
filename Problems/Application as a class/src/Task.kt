class Application(val name: String) {
    fun run(vararg a: String) {
        println(name)
        a.forEach(::println)
    }
}
