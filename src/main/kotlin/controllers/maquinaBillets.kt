package org.example.controllers

import java.util.*


fun main() {
    val scanner = abrirScanner()
    val preusBillets = mutableListOf<Pair<String, Double>>()  // Lista para almacenar los billetes comprados
    menuBillets(preusBillets, scanner)
    cerrarScanner(scanner)
}

/**
 * Abre un objeto [Scanner] para leer la entrada del usuario.
 *
 * @return Un objeto [Scanner] para la entrada estándar.
 */
fun abrirScanner(): Scanner = Scanner(System.`in`)

/**
 * Cierra el objeto [Scanner] para liberar recursos.
 *
 * @param scanner El objeto [Scanner] a cerrar.
 */
fun cerrarScanner(scanner: Scanner) {
    scanner.close()
}

/**
 * Muestra el menú principal con las opciones de billetes disponibles.
 */
fun mostrarMenu() {
    println("Premi el billet que vol adquirir:")
    println("1-Billet senzill")
    println("2-TCasual")
    println("3-TUsual")
    println("4-TFamiliar")
    println("5-TJove")
    println("6-Nah res, només mirava")
    print("Posa la teva opció: ")
}

/** @author sergionavas
 * Controla el flujo del menú principal, gestionando las opciones seleccionadas por el usuario.
 *
 * @param preusBillets Lista mutable donde se almacenan los billetes comprados y sus precios.
 * @param scanner Objeto [Scanner] para leer las entradas del usuario.
 */
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
    pagar(preusBillets, scanner)
}

/** @author sergionavas
 * Permite al usuario seleccionar la zona del billete y calcula el precio final según la zona seleccionada.
 *
 * @param preuBase Precio base del billete.
 * @param scanner Objeto [Scanner] para leer las entradas del usuario.
 * @return Un par [Pair] que contiene la zona seleccionada y el precio final del billete.
 */
fun elegirZona(preuBase: Double, scanner: Scanner): Pair<Int, Double> {
    println("De quantes zones vols el bitllet?")
    println("1-Zona 1")
    println("2-Zona 2")
    println("3-Zona 3")
    print("Posa la teva opció: ")

    val zona = scanner.nextInt()

    val preuFinal = when (zona) {
        1 -> preuBase
        2 -> preuBase * 1.3125
        3 -> preuBase * 1.8443
        else -> {
            println("Zona no vàlida. Seleccionant Zona 1 per defecte.")
            preuBase
        }
    }
    return Pair(zona, preuFinal)
}

/** @author sergionavas
 * Permite al usuario seleccionar un tipo de billete y calcular su precio en función de la zona seleccionada.
 *
 * @param tipusBillet Nombre del tipo de billete seleccionado.
 * @param preuBase Precio base del billete.
 * @param preusBillets Lista mutable para almacenar los billetes seleccionados y sus precios.
 * @param scanner Objeto [Scanner] para leer las entradas del usuario.
 */
fun elegirTipusBillet(
    tipusBillet: String,
    preuBase: Double,
    preusBillets: MutableList<Pair<String, Double>>,
    scanner: Scanner
) {
    val (zona, preuFinal) = elegirZona(preuBase, scanner)
    println("Has escollit el $tipusBillet per a la Zona $zona.")
    println("El preu del bitllet és ${"%.2f".format(preuFinal)}€.")

    preusBillets.add(Pair("$tipusBillet (Zona $zona)", preuFinal))
    if (!seguirComprant(preusBillets, scanner)) {
        pagar(preusBillets, scanner)
    }
}

/** @author sergionavas
 * Pregunta al usuario si desea seguir comprando billetes.
 *
 * @param preusBillets Lista de billetes comprados hasta el momento.
 * @param scanner Objeto [Scanner] para leer las entradas del usuario.
 * @return `true` si el usuario desea continuar comprando, `false` en caso contrario.
 */
fun seguirComprant(preusBillets: MutableList<Pair<String, Double>>, scanner: Scanner): Boolean {
    println("Vols continuar comprant? (Sí/No):")
    val resposta = scanner.next()
    return resposta.equals("SI", ignoreCase = true)
}

/** @author sergionavas
 * Realiza el proceso de pago, permitiendo al usuario introducir monedas o billetes válidos.
 *
 * @param preusBillets Lista de billetes comprados y sus precios.
 * @param scanner Objeto [Scanner] para leer las entradas del usuario.
 */
fun pagar(preusBillets: List<Pair<String, Double>>, scanner: Scanner) {
    val total = preusBillets.sumOf { it.second }
    println("El total a pagar és: ${"%.2f".format(total)}€.")

    val monedasValidas = listOf(0.05, 0.10, 0.20, 0.50, 1.0, 2.0, 5.0, 10.0, 20.0, 50.0)
    var pago = 0.0

    while (pago < total) {
        println("Introdueix una moneda o bitllet vàlid: (${monedasValidas.joinToString(", ")})")
        val moneda = scanner.nextDouble()

        if (monedasValidas.contains(moneda)) {
            pago += moneda
            println("Has introduït: ${"%.2f".format(moneda)}€. Total pagat: ${"%.2f".format(pago)}€.")
        } else {
            println("Moneda no vàlida. Torna a intentar-ho.")
        }
    }

    if (pago > total) {
        val canvi = pago - total
        println("Has pagat més del necessari. El teu canvi és: ${"%.2f".format(canvi)}€.")
    }

    ticket(preusBillets, scanner)
}

/** @author sergionavas
 * Genera y muestra el ticket de los billetes comprados. Permite al usuario volver al menú principal.
 *
 * @param preusBillets Lista de billetes comprados y sus precios.
 * @param scanner Objeto [Scanner] para leer las entradas del usuario.
 */
fun ticket(preusBillets: List<Pair<String, Double>>, scanner: Scanner) {
    println("Vols tiquet? (Sí/No)")
    val resposta = scanner.next()

    if (resposta.equals("SI", ignoreCase = true)) {
        println("_______TIQUET_______")
        var total = 0.0
        for ((tipus, preu) in preusBillets) {
            println("$tipus - ${"%.2f".format(preu)}€")
            total += preu
        }
        println("_____________________")
        println("Total a pagar: ${"%.2f".format(total)}€")
        println("Gràcies per la seva compra!")
    } else {
        println("Gràcies per la seva compra.")
    }

    // Tornar al menú principal
    println("Tornant al menú principal...")
    menuBillets(preusBillets.toMutableList(), scanner)
}
