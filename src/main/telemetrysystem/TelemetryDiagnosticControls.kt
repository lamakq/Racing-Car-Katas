package telemetrysystem

class TelemetryDiagnosticControls(private val telemetryClient: TelemetryClient = TelemetryClient()) {
    private val DiagnosticChannelConnectionString = "*111#"

    var diagnosticInfo = ""

    @Throws(Exception::class)
    fun checkTransmission() {
        diagnosticInfo = ""

        connect()

        telemetryClient.send(TelemetryClient.DIAGNOSTIC_MESSAGE)
        diagnosticInfo = telemetryClient.receive()
    }

    private fun connect() {
        telemetryClient.disconnect()

        var retryLeft = 3
        while (telemetryClient.offline() && retryLeft > 0) {
            telemetryClient.connect(DiagnosticChannelConnectionString)
            retryLeft -= 1
        }

        if (telemetryClient.offline()) {
            throw Exception("Unable to connect.")
        }
    }
}