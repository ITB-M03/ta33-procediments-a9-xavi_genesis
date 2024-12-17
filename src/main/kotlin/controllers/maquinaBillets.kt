package org.example.controllers
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val preusBillets = mutableListOf<Double>()
    menuBillets(preusBillets)
    pagar(preusBillets)
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

fun menuBillets(preusBillets: MutableList<Double>) {
    val scanner = Scanner(System.`in`)
    var continuar = true
    while (continuar) {
        mostrarMenu()
        val opcio = scanner.nextInt()
        when (opcio) {
            1 -> elegirTipusBillet("Billet senzill", 2.40, preusBillets)
            2 -> elegirTipusBillet("TCasual", 11.35, preusBillets)
            3 -> elegirTipusBillet("TUsual", 40.00, preusBillets)
            4 -> elegirTipusBillet("TFamiliar", 10.00, preusBillets)
            5 -> elegirTipusBillet("TJove", 80.00, preusBillets)
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
fun elegirZona(preuBase: Double): Pair<Int, Float> {
    val scanner = Scanner(System.`in`)
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
fun elegirTipusBillet(tipusBillet: String, preuBase: Double, preusBillets: MutableList<Double>) {
    val (zona, preuFinal) = elegirZona(preuBase)
    println("Has escollit el $tipusBillet per a la Zona $zona.")
    println("El preu del bitllet és $preuFinal€.")

    preusBillets.add(preuFinal.toDouble())
    mesBillets(preusBillets)
}

/**
 * Funció per preguntar si l'usuari vol continuar comprant.
 */
fun mesBillets(preusBillets: MutableList<Double>) {
    val scanner = Scanner(System.`in`)
    var seguirComprant = 0
    var continuar = true

    while (continuar) {
        println("Vols continuar comprant? (Sí/No):")
        val resposta = scanner.next()

        if (resposta.equals("SI", ignoreCase = true)) {
            seguirComprant++
            if (seguirComprant >= 3) {
                println("Error: Has superat el límit. Esperi a l'operari.")
                continuar = false // Finaliza el bucle al superar el límite
            } else {
                println("Tornant al menú principal...")
                return
            }
        } else if (resposta.equals("NO", ignoreCase = true)) {
            println("Gràcies per la teva compra! Fins aviat.")
            continuar = false
        } else {
            println("Resposta no vàlida. Torna a intentar-ho.")
        }
    }
}

fun pagar(preciosBilletes: List<Double>) {
    val total = preciosBilletes.sum() // Suma tots els preus
    println("Resum de la compra:")
    preciosBilletes.forEachIndexed { index, preu ->
        println("Billet ${index + 1}: $preu€")
    }
    println("El total a pagar és: $total€.")
    println("Gràcies per la teva compra! Fins aviat.")
}