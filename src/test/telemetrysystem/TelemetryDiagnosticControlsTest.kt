package telemetrysystem

import org.junit.Before
import org.junit.Test

class TelemetryDiagnosticControlsTest {

    @Before
    fun setUp() {
    }

    @Test(expected = Exception::class)
    fun `should throw error when offline`() {
        TelemetryDiagnosticControls(OfflineTelemtryClient())
            .checkTransmission()
    }
}

class OfflineTelemtryClient: TelemetryClient() {
    override fun offline() = true
}