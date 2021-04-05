package telemetrysystem

class TelemetryDiagnosticControls(private val telemetryClient: TelemetryClient = TelemetryClient()) {
    private val DiagnosticChannelConnectionString = "*111#"

    var diagnosticInfo = ""

    @Throws(Exception::class)
    fun checkTransmission() {
        diagnosticInfo = ""

        disconnect()
        connect()
        throwErrorIfOffline()

        telemetryClient.send(TelemetryClient.DIAGNOSTIC_MESSAGE)
        diagnosticInfo = telemetryClient.receive()
    }

    private fun disconnect() {
        telemetryClient.disconnect()
    }

    private fun throwErrorIfOffline() {
        if (telemetryClient.offline()) {
            throw Exception("Unable to connect.")
        }
    }

    private fun connect() {
        var retryLeft = 3
        while (telemetryClient.offline() && retryLeft > 0) {
            telemetryClient.connect(DiagnosticChannelConnectionString)
            retryLeft -= 1
        }
    }
}