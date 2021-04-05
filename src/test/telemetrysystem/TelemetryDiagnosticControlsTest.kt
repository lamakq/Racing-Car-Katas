package telemetrysystem

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class TelemetryDiagnosticControlsTest {

    @Before
    fun setUp() {
    }

    @Test(expected = Exception::class)
    fun `should throw error when offline`() {
        TelemetryDiagnosticControls(OfflineTelemetryClient())
            .checkTransmission()
    }

    @Test
    fun `should retry thrice`() {
        val client = RetryTelemetryClient()
        try {
            TelemetryDiagnosticControls(client)
                .checkTransmission()
        } catch (e: Exception) {
            client.retried(3)
        }
    }

    @Test
    fun `should disconnect before connecting`() {
        val client = ReconnectTelemetryClient()
        TelemetryDiagnosticControls(client).checkTransmission()
        client.connectedAfterDisconnecting()
    }
}

open class TelemetryClientDouble: TelemetryClient() {
    override fun offline() = false
    override fun connect(telemetryServerConnectionString: String?) {}
    override fun disconnect() {}
}

open class OfflineTelemetryClient: TelemetryClientDouble() {
    override fun offline() = true
}

class RetryTelemetryClient: TelemetryClientDouble() {
    var retries = 0
    override fun connect(telemetryServerConnectionString: String?) {
        retries++
    }
    fun retried(times: Int) {
        assertEquals(times, retries)
    }
}

class ReconnectTelemetryClient: OfflineTelemetryClient() {
    var disconnected = false
    var connectedAfterDisconnecting = false

    override fun disconnect() {
        disconnected = true
    }
    override fun connect(telemetryServerConnectionString: String?) {
        assert(disconnected)
        connectedAfterDisconnecting = true
    }

    override fun offline() = !connectedAfterDisconnecting

    fun connectedAfterDisconnecting() {
        assertEquals(true, connectedAfterDisconnecting)
    }
}