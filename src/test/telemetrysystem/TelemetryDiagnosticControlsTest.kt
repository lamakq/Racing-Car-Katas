package telemetrysystem

import org.junit.Test

class TelemetryDiagnosticControlsTest {

    @Test
    fun `should check if correct message sent`() {
        val client = TelemetryClientDouble()
        TelemetryDiagnosticControls(client).checkTransmission()
        client.messageSent()
    }
}

class TelemetryClientDouble: TelemetryClient() {
    var sent = false
    override fun send() {
        sent = true
    }

    fun messageSent() {
        assert(sent)
    }
}