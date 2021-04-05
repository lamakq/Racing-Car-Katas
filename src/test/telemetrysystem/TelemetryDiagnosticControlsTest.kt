package telemetrysystem

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TelemetryDiagnosticControlsTest {

    private lateinit var telemetry: TelemetryDiagnosticControls
    private lateinit var client: TelemetryClientDouble

    @Before
    fun setUp() {
        client = TelemetryClientDouble()
        telemetry = TelemetryDiagnosticControls(client)
    }

    @Test
    fun `should disconnect and connect after X retries`() {
        telemetry.checkTransmission()
        client.wasDisconnected()
    }

    @Test(expected = Exception::class)
    fun `unable to connect after retries`() {
        client.initWith(4)
        telemetry.checkTransmission()
    }
}

class TelemetryClientDouble() : TelemetryClient() {

    private var wasDisconnected: Boolean = false
    private var retries: Int = 0
    private var connectAfterRetries: Int = 1

    override fun disconnect() {
        wasDisconnected = true
    }

    fun wasDisconnected() {
        Assert.assertTrue(wasDisconnected)
    }

    override fun connect(telemetryServerConnectionString: String?) {
        retries++

        if (retries == connectAfterRetries) {
            onlineStatus = true
        }
    }

    fun initWith(connectAfterRetries: Int) {
        this.connectAfterRetries = connectAfterRetries
    }
}