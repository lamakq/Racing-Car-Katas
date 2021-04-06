package telemetrysystem

import org.junit.Test
import telemetrysystem.TelemetryClient.Companion.DIAGNOSTIC_MESSAGE
import kotlin.test.assertEquals

class TelemetryDiagnosticControlsTest {

    @Test
    fun `should check if correct message sent`() {
        val client = TelemetryClientDouble()
        TelemetryDiagnosticControls(client).checkTransmission()
        client.diagnosticMessageMatches(DIAGNOSTIC_MESSAGE)
    }
}

class TelemetryClientDouble: TelemetryClient() {
    var message: String? = null
    override fun send(message: String?) {
        this.message = message
    }

    fun diagnosticMessageMatches(diagnosticMessage: String) {
        assertEquals(diagnosticMessage, message)
    }
}