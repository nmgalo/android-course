interface Drone {
    fun destroy(): String
}

class Bayraktar : Drone {
    override fun destroy(): String {
        return "РУССКИЙ КОРАБЛЬ, ИДИ НА *УЙ ..."
    }
}

class DroneFactory {
    fun build(droneType: String): Drone? {
        return when (droneType) {
            "Bayraktar" -> Bayraktar()
            else -> null
        }
    }
}

fun main() {
    val factory = DroneFactory()
    factory.build("Bayraktar")?.let {
        println(it.destroy())
    }
}

