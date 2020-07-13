val lambda: (Long, Long) -> Long = { i, j ->
    var product: Long = 1
    for (i in i..j) {
        product *= i
    }
    product
}