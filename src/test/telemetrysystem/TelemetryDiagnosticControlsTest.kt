package tddmicroexercises.telemetrysystem

import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import telemetrysystem.TelemetryDiagnosticControls
import telemetrysystem.TelemetryClientDouble

class TelemetryDiagnosticControlsTest {

    private lateinit var telemetry: TelemetryDiagnosticControls
    private lateinit var client: TelemetryClientDouble

    @Before
    fun setUp() {
        client = TelemetryClientDouble()
        telemetry = TelemetryDiagnosticControls(client)
    }

    @Test
    fun `should disconnect`() {
        telemetry.checkTransmission()
        assertTrue(client.wasDisconnected)
    }
}
