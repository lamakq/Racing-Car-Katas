package telemetrysystem

import java.util.*

open class TelemetryClient {

    var onlineStatus: Boolean = false
        protected set

    private var diagnosticMessageResult: String = ""

    private val connectionEventsSimulator = Random(42)

    open fun connect(telemetryServerConnectionString: String?) {
        if (telemetryServerConnectionString == null || "" == telemetryServerConnectionString) {
            throw IllegalArgumentException()
        }

        // simulate the operation on a real modem
        val success = connectionEventsSimulator.nextInt(10) <= 8

        onlineStatus = success
    }

    open fun disconnect() {
        onlineStatus = false
    }

    open fun send() {

        // simulate a status report
        diagnosticMessageResult = ("LAST TX rate................ 100 MBPS\r\n"
                + "HIGHEST TX rate............. 100 MBPS\r\n"
                + "LAST RX rate................ 100 MBPS\r\n"
                + "HIGHEST RX rate............. 100 MBPS\r\n"
                + "BIT RATE.................... 100000000\r\n"
                + "WORD LEN.................... 16\r\n"
                + "WORD/FRAME.................. 511\r\n"
                + "BITS/FRAME.................. 8192\r\n"
                + "MODULATION TYPE............. PCM/FM\r\n"
                + "TX Digital Los.............. 0.75\r\n"
                + "RX Digital Los.............. 0.10\r\n"
                + "BEP Test.................... -5\r\n"
                + "Local Rtrn Count............ 00\r\n"
                + "Remote Rtrn Count........... 00")

        // here should go the real Send operation (not needed for this exercise)
    }

    fun receive(): String {
        val message = diagnosticMessageResult
        diagnosticMessageResult = ""

        return message
    }
}
