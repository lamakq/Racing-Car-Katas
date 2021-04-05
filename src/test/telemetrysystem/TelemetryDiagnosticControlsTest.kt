package telemetrysystem

import org.junit.Before
import org.junit.Test

class TelemetryDiagnosticControlsTest {

    private lateinit var telemetry: TelemetryDiagnosticControls
    private lateinit var client: TelemetryClient

    @Before
    fun setUp() {
        client = TelemetryClient()
        telemetry = TelemetryDiagnosticControls(client)
    }

    @Test
    fun `should do something`() {
        telemetry.checkTransmission()
    }
}