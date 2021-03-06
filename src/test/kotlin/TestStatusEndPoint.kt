import clarify.server
import com.natpryce.hamkrest.assertion.assertThat
import org.http4k.client.OkHttp
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status
import org.http4k.hamkrest.hasStatus
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TestStatusEndPoint {

    private val client = OkHttp()
    private val server = server(0)

    @BeforeEach
    fun setup() {
        server.start()
    }

    @AfterEach
    fun teardown() {
        server.stop()
    }

    @Test fun `response to status`() {
        val response = client(Request(Method.GET, "http://localhost:${server.port()}/status"))
        assertThat(response, hasStatus(Status.OK))
    }
}
