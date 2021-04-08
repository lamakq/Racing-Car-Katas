package telemetrysystem

import org.junit.Test
import kotlin.test.assertEquals

class TelemetryDiagnosticControlsTest {

    @Test
    fun `should check if correct message sent`() {
        val client = TelemetryClientDouble()
        TelemetryDiagnosticControls(client).checkTransmission()
        client.messageSent()
    }

    @Test
    fun `should receive diagnostic message`() {
        val client = TelemetryClientDouble()
        val controls = TelemetryDiagnosticControls(client)
        controls.checkTransmission()
        assertEquals("LAST TX rate................ 100 MBPS\r\n"
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
                + "Remote Rtrn Count........... 00", controls.diagnosticInfo)
    }
}

class TelemetryClientDouble: TelemetryClient() {
    var sent = false
    override fun send() {
        super.send()
        sent = true
    }

    fun messageSent() {
        assert(sent)
    }
}