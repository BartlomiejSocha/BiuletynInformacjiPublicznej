package clarify

import org.http4k.core.HttpHandler
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.server.Http4kServer
import org.http4k.server.Jetty

val app: HttpHandler = { Response(Status.OK) }
fun server(port: Int): Http4kServer {
    return Jetty(port).toServer(app)
}
