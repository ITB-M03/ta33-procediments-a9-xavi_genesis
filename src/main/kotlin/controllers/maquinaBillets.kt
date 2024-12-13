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
            2-> menuZona()
            3-> menuZona()
            4-> menuZona()
            5->  menuZona()
            6-> {
                println("Adeu, que tingui un bon dia.")
                continuar=false
            }
            else-> {
                println("Opcio incorreecta")
                continuar = false
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
    val billSencill= 2.40
    val continuar= true
    while(continuar){
        val opcio= scanner.nextInt()
        when(opcio){
            1-> print("De cuantes zones?:")
            2-> print("De cantes zones?:")
            3-> print("De cuantes zones?:")
            4-> return mostrarMenu()

        }
    }

}