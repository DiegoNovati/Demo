package uk.co.itmms.demo.datasources.console

interface IConsole {
    fun write(tag: String, message: String)
}

class Console : IConsole {
    override fun write(tag: String, message: String) {
        println("[$tag] $message")
    }
}