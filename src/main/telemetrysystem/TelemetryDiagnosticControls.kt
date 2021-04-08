package telemetrysystem

import junit.framework.Assert.assertEquals

class TelemetryDiagnosticControls(private val client: TelemetryClient) {
    private val DiagnosticChannelConnectionString = "*111#"

    private var diagnosticInfo = ""

    @Throws(Exception::class)
    fun checkTransmission() {
        diagnosticInfo = ""

        client.disconnect()

        var retryLeft = 3
        while (client.onlineStatus == false && retryLeft > 0) {
            client.connect(DiagnosticChannelConnectionString)
            retryLeft -= 1
        }

        if (client.onlineStatus == false) {
            throw Exception("Unable to connect.")
        }

        client.send(TelemetryClient.DIAGNOSTIC_MESSAGE)
        diagnosticInfo = client.receive()
    }

    fun has(diagnosticInfo: String) {
        assertEquals(diagnosticInfo, this.diagnosticInfo)
    }
}
