package org.example.controllers
import java.util.*

fun main() {
    val scanner = abrirScanner()
    val preusBillets = mutableListOf<Pair<String, Double>>()  // Lista para almacenar los billetes comprados
    menuBillets(preusBillets, scanner)
    cerrarScanner(scanner)
}

fun abrirScanner(): Scanner {
    return Scanner(System.`in`)
}

fun cerrarScanner(scanner: Scanner) {
    scanner.close()
}

fun mostrarMenu() {
    println("Premi el billet que vol adquirir:")
    println("1-Billet senzill")
    println("2-TCasual")
    println("3-TUsual")
    println("4-TFamiliar")
    println("5-TJove")
    println("6-Nah res nomes miraba")
    print("Posa la teva opcio: ")
}

fun menuBillets(preusBillets: MutableList<Pair<String, Double>>, scanner: Scanner) {
    var continuar = true
    while (continuar) {
        mostrarMenu()
        val opcio = scanner.nextInt()
        when (opcio) {
            1 -> elegirTipusBillet("Billet senzill", 2.40, preusBillets, scanner)
            2 -> elegirTipusBillet("TCasual", 11.35, preusBillets, scanner)
            3 -> elegirTipusBillet("TUsual", 40.00, preusBillets, scanner)
            4 -> elegirTipusBillet("TFamiliar", 10.00, preusBillets, scanner)
            5 -> elegirTipusBillet("TJove", 80.00, preusBillets, scanner)
            6 -> {
                println("Adeu, que tingui un bon dia.")
                continuar = false
            }
            else -> println("Opció no disponible, torna a escollir una:")
        }
    }
}

/**
 * Funció per seleccionar la zona i obtenir el preu final del bitllet.
 */
fun elegirZona(preuBase: Double, scanner: Scanner): Pair<Int, Float> {
    println("De quantes zones vols el bitllet?")
    println("1-Zona 1")
    println("2-Zona 2")
    println("3-Zona 3")
    print("Posa la teva opció: ")

    val zona = scanner.nextInt()

    val preuFinal = when (zona) {
        1 -> preuBase.toFloat()
        2 -> (preuBase * 1.3125).toFloat()
        3 -> (preuBase * 1.8443).toFloat()
        else -> {
            println("Zona no vàlida. Seleccionant Zona 1 per defecte.")
            preuBase.toFloat()
        }
    }
    return Pair(zona, preuFinal)
}

/**
 * Funció per seleccionar el tipus de bitllet i calcular el preu segons la zona.
 */
fun elegirTipusBillet(tipusBillet: String, preuBase: Double, preusBillets: MutableList<Pair<String, Double>>, scanner: Scanner) {
    val (zona, preuFinal) = elegirZona(preuBase, scanner)
    println("Has escollit el $tipusBillet per a la Zona $zona.")
    println("El preu del bitllet és $preuFinal€.")

    preusBillets.add(Pair("$tipusBillet (Zona $zona)", preuFinal.toDouble()))
    mesBillets(preusBillets, scanner)
}

/**
 * Funció per preguntar si l'usuari vol continuar comprant.
 */
fun mesBillets(preusBillets: MutableList<Pair<String, Double>>, scanner: Scanner) {
    var seguirComprant = 0
    var continuar = true

    while (continuar) {
        println("Vols continuar comprant? (Sí/No):")
        val resposta = scanner.next()

        if (resposta.equals("SI", ignoreCase = true)) {
            seguirComprant++
            if (seguirComprant >= 3) {  // Limitar a 3 compras
                println("Has arribat al límit de compres. El procés es deté.")
                continuar = false  // Detener el ciclo de compra
            } else {
                println("Tornant al menú principal...")
                menuBillets(preusBillets, scanner)  // Volver al menú principal
                continuar = false  // Salir del ciclo actual para que no siga pidiendo más compras
            }
        } else if (resposta.equals("NO", ignoreCase = true)) {
            pagar(preusBillets, scanner)  // Proceder al pago
            continuar = false  // Terminar el ciclo de compra
        } else {
            println("Resposta no vàlida. Torna a intentar-ho.")
        }
    }
}

fun pagar(preusBillets: List<Pair<String, Double>>, scanner: Scanner) {
    val total = preusBillets.sumOf { it.second }  // Sumar los precios de todos los billetes
    println("El total a pagar és: $total€.")

    val monedasValidas = listOf(0.05, 0.10, 0.20, 0.50, 1.0, 2.0, 5.0, 10.0, 20.0, 50.0)
    var pago = 0.0

    while (pago < total) {
        println("Introdueix una moneda o bitllet vàlid:")
        val moneda = scanner.nextDouble()

        if (monedasValidas.contains(moneda)) {
            pago += moneda
            println("Has introduït: $moneda€. Total pagat: $pago€.")
        } else {
            println("Moneda no vàlida. Torna a intentar-ho.")
        }
    }

    if (pago > total) {
        val canvi = pago - total
        println("Has pagat més del necessari. El teu canvi és: $canvi€.")
    }

    // Llamada a la función ticket para mostrar el recibo
    ticket(preusBillets, scanner)
}

/**
 * Funció per mostrar el tiquet de les compres realitzades.
 */
fun ticket(preusBillets: List<Pair<String, Double>>, scanner: Scanner) {
    println("Vols tiquet? (Sí/No)")
    val resposta = scanner.next()

    if (resposta.equals("SI", ignoreCase = true)) {
        println("_______TIQUET_______")
        var total = 0.0
        // Imprimir cada tiquet y su precio
        for ((tipus, preu) in preusBillets) {
            println("$tipus - $preu€")
            total += preu
        }
        println("_____________________")
        println("Total a pagar: $total€")
        println("Gràcies per la seva compra!")
    } else {
        println("Gràcies per la seva compra.")
    }

    // Volver al menú principal después de mostrar el tiquet
    menuBillets(preusBillets, scanner)
}
