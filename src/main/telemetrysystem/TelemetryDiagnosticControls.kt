package telemetrysystem

class TelemetryDiagnosticControls(private val telemetryClient: TelemetryClient = TelemetryClient()) {
    private val DiagnosticChannelConnectionString = "*111#"

    var diagnosticInfo = ""

    @Throws(Exception::class)
    fun checkTransmission() {
        diagnosticInfo = ""

        telemetryClient.disconnect()

        var retryLeft = 3
        while (telemetryClient.onlineStatus == false && retryLeft > 0) {
            telemetryClient.connect(DiagnosticChannelConnectionString)
            retryLeft -= 1
        }

        if (telemetryClient.onlineStatus == false) {
            throw Exception("Unable to connect.")
        }

        telemetryClient.send()
        diagnosticInfo = telemetryClient.receive()
    }
}