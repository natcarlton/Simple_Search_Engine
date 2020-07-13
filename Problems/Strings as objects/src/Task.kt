fun main() {
    val input = readLine()!!

    if (input.isEmpty()) println("\n")
    else if (input.first() == 'i') println(input.drop(1).toInt() + 1)
    else if (input.first() == 's') println(input.drop(1).reversed())
    else println(input)
}