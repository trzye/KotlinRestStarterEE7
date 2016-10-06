package pl.edu.pw.ee.jereczem.krs.rest.security

import java.nio.charset.Charset
import java.security.Principal
import java.util.*
import javax.ws.rs.WebApplicationException
import javax.ws.rs.container.ContainerRequestContext
import javax.ws.rs.container.ContainerRequestFilter
import javax.ws.rs.core.Response.*
import javax.ws.rs.core.SecurityContext
import javax.ws.rs.ext.Provider

/**
 * Created by micha on 06.10.2016.
 */
@Provider
open class AuthorizationRequestFilter : ContainerRequestFilter {
    override fun filter(requestContext: ContainerRequestContext?) {
        if(requestContext != null) {
            val credentials = requestContext.getCredentialsOfBasicAuthorization()
            if (credentials != null) {
                val securityContext = KrsSecurityContext(credentials.username)
                requestContext.securityContext = securityContext
                return
            }
        }
        throw WebApplicationException(Status.UNAUTHORIZED)
    }
}

private fun ContainerRequestContext.getCredentialsOfBasicAuthorization(): KrsCredentials? {
    val authorizationHeader = this.headers.getFirst("Authorization")
    if (authorizationHeader != null) {
        if (authorizationHeader.startsWith("Basic")) {
            val encodedUsernameAndPassword = authorizationHeader.substring("Basic".length).trim()
            val decodedUsernameAndPassword = Base64.getDecoder().decode(encodedUsernameAndPassword)
            val usernameAndPassword = String(decodedUsernameAndPassword, Charsets.UTF_8)
            val authorizationDetails = StringTokenizer(usernameAndPassword, ":")
            if (authorizationDetails.countTokens() == 2) {
                val username = authorizationDetails.nextToken()
                val password = authorizationDetails.nextToken()
                return KrsCredentials(username, password)
            }
        }
    }
    return null
}

private data class KrsCredentials(val username: String, val password: String)

private class KrsSecurityContext(val username: String) : SecurityContext{
    override fun isSecure() = false

    override fun isUserInRole(role: String?) = false

    override fun getAuthenticationScheme() = SecurityContext.BASIC_AUTH

    override fun getUserPrincipal() = Principal { username }
}
