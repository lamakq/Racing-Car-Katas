package telemetrysystem

import org.junit.Before
import org.junit.Test

class TelemetryDiagnosticControlsTest {

    @Before
    fun setUp() {
    }

    @Test(expected = Exception::class)
    fun `should throw error when offline`() {
        TelemetryDiagnosticControls(OfflineTelemetryClient())
            .checkTransmission()
    }
}

class OfflineTelemetryClient: TelemetryClient() {
    override fun offline() = true
}