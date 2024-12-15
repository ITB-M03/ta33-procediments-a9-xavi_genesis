package org.example.controllers
import java.util.*

fun main(){
    val scanner= Scanner(System.`in`)
    menuBilletes()
}
fun mostrarMenu() {
    println("Premi el billet que vol adquirir:")
    println("1-Billet sencill")
    println("2-TCasual")
    println("3-TUsual")
    println("4-TFamiliar")
    println("5-TJove")
    println("6-Nah res nomes miraba")
    print("Posa la teva opcio:")
}
fun menuBilletes(){
    val scanner= Scanner(System.`in`)
    mostrarMenu()
    var continuar= true
    while(continuar){
        val opcio= scanner.nextInt()
        when(opcio){
            1-> sencill()
            2-> tCasual()
            3-> mostrarMenu()
            4-> mostrarMenu()
            5-> mostrarMenu()
            6-> {
                println("Adeu, que tingui un bon dia.")
                continuar=false
            }
            else-> {
                println("Opcio no disponible, torna a escollir una:")


            }
        }
    }
}

fun mostrarMenuZona() {
    println("De cuantes zones?:")
    println("1-Zona 1")
    println("2-Zona 2")
    println("3-Zona 3")
    println("4-Tornar a escollir billet")
    print("Posa la teva opcio:")
}

fun sencill(){
    val scanner= Scanner(System.`in`)
    mostrarMenuZona()

    var continuar= true
    while(continuar){
        val opcio= scanner.nextInt()
        val (preuZona1, preuZona2, preuZona3) = calcularPreu(2.40)
        when(opcio){
            1-> println("Billet sencill, Zona1\nEl preu del billet es $preuZona1€")
            2-> println("Billet sencill, Zona2\nEl preu del billet es $preuZona2€")
            3-> println("Billet sencill, Zona3\nEl preu del billet es $preuZona3€")
            4-> return mostrarMenu()

        }
        println("Vols continuar comprant? (Sí/No):")
        val resposta = scanner.next()
        if (resposta=="SI"|| resposta == "si" || resposta == "Si"){
            return mostrarMenu()

        }else if (resposta == "No" || resposta == "no" || resposta == "NO") {
            continuar = false
            println("Gràcies per la teva compra! Fins aviat.")
        }
    }
}
fun tCasual(){
    val scanner= Scanner(System.`in`)
    mostrarMenuZona()

    var continuar= true

    while(continuar){
        val opcio= scanner.nextInt()
        val (preuZona1, preuZona2, preuZona3) = calcularPreu(11.35)
        when(opcio){
            1-> println("Billet Tcasual, Zona1\nEl preu del billet es $preuZona1€")
            2-> println("Billet Tcasual, Zona2\nEl preu del billet es $preuZona2€")
            3-> println("Billet Tcasual, Zona3\nEl preu del billet es $preuZona3€")
            4-> return mostrarMenu()

        }
    }
    mesBillets()
}
fun calcularPreu(preuZona1: Double): Triple<Float, Float, Float> {
    val preu = preuZona1.toFloat()
    val preuZona2 = (preuZona1 * 1.3125).toFloat()
    val preuZona3 = (preuZona1 * 1.8443).toFloat()
    return Triple(preu, preuZona2, preuZona3)
}
fun mesBillets(){
    val scanner= Scanner(System.`in`)
    println("Vols continuar comprant? (Sí/No):")
    var seguirCompran= 0
    var continuar= true
    val resposta = scanner.next()
    if (resposta=="SI"|| resposta == "si" || resposta == "Si"){
        seguirCompran++
        if (seguirCompran>=3){
            println("Error: Esperi a l'operari")
            continuar = false
        }else {
            mostrarMenu()
        }

    }else if (resposta == "No" || resposta == "no" || resposta == "NO") {
        continuar = false
        println("Gràcies per la teva compra! Fins aviat.")
    }

}