package net.juzabel.server.internal

import android.content.Context
import android.net.Uri
import android.net.http.HttpResponseCache.install
import android.util.Log
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.request.uri
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.ApplicationEngine
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

internal object Server {
    private var server: ApplicationEngine? = null
    fun init(context: Context) {
        Log.d(Server::class.qualifiedName, "embeded server init")
        destroy()
        server = embeddedServer(Netty, PORT) {
            install(ContentNegotiation) {
                gson {}
            }
            routing {
                get("/api/postures/") {
                    call.respond(readFile(context, "postures"))
                }
                get("/api/posture_detail/") {
                    call.respond(readFile(context, "posture_detail"))
                }
            }
        }.start(wait = false)
    }

    fun destroy() {
        server?.apply {
            Log.d(Server::class.qualifiedName, "embeded server destroy")
            stop(GRACE_PERIOD, TIMEOUT)
        }
    }
    private fun readFile(context: Context, uri: String?): String {
        var json: String? = null
        try {
            val inputStream = context.assets.open("$uri.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: Exception) {
            ex.printStackTrace()
            return "[]"
        }
        return json
    }

        private const val PORT = 8080
        private const val GRACE_PERIOD = 100L
        private const val TIMEOUT = 100L

}