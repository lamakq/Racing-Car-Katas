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
}

open class TelemetryClientDouble: TelemetryClient() {
    override fun offline() = false
    override fun connect(telemetryServerConnectionString: String?) {}
    override fun disconnect() {}
}

class OfflineTelemetryClient: TelemetryClientDouble() {
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