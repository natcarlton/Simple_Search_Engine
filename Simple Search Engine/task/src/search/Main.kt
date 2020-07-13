package search

import java.io.File

fun main() {
    var fileName = readLine()!!.split(" ")
    if (fileName[0] == "--data" && fileName[1] == "names.txt") fileName = listOf(fileName[1])

    val file = File("Simple Search Engine\\task\\src\\search\\${fileName[0]}").readLines()
    menu(file)
}

fun menu(file: List<String>) {
    while (true) {
        println("=== Menu ===")
        println("1. Find an author")
        println("2. Print all authors")
        println("0. Exit")
        val option = readLine()!!.toInt()

        if (option == 0) break
        else if (option == 1) {
            println("Select a matching strategy: ALL, ANY, NONE")
            val method = SearchType.valueOf(readLine()!!.toUpperCase())
            println("Enter the author or book to search:")
            val foundLines = findLines(InvertedIndex.createInvertedIndex(file), file, method)
            if (foundLines.isNotEmpty()) foundLines.forEach { println(it) }
        } else if (option == 2) printAllAuthors(file)
        else println("Incorrect option! Try again.")
    }
}

fun printAllAuthors(file: List<String>) {
    println("=== List of people ===")
    file.forEach { println(it) }
}

fun findLines(invertedIndex: Map<String, List<Int>>, lines: List<String>, searchType: SearchType): List<String> {
    val query = readLine()!!.toUpperCase().split(" ")

    return when (searchType) {
        SearchType.ALL -> {
            lines.filter { line ->
                query.all { it in line.toUpperCase() }
            }
        }
        SearchType.NONE -> {
            lines.filter { line ->
                query.none { it in line.toUpperCase() }
            }
        }
        SearchType.ANY -> {
            invertedIndex.toList() //List<Pair<String, List<Int>>>
                    .filter { it.first.toUpperCase() in query }  //List<Pair<String, List<Int>>>
                    .map { it.second } // Pair<String, List<Int>>
                    .flatten()
                    .distinct()
                    .map { lines[it] }
        }
    }
}

object InvertedIndex {
    fun createInvertedIndex(lines: List<String>): Map<String, List<Int>> {
        // List<String>
        return lines
                .mapIndexed { lineNumber, line ->
                    line.split(" ").map { it to lineNumber /* Pair<String, Int> */ } // List<Pair<String, Int>>
                } // List<List<Pair<String, Int>>>
                .flatten() // List<Pair<String, Int>>
                .groupBy { it.first } // Map<String, List<Pair<String, Int>>>
                .toList() // List<Pair<String, List<Pair<String, Int>>>>
                .map { item -> item.first to item.second.map { it.second } }.toList() // List<Pair<String, List<Int>>>
                .toMap() // Map<String, List<Int>>
    }
}

enum class SearchType {
    ANY, ALL, NONE
}