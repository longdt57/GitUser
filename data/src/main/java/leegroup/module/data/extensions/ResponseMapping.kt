package leegroup.module.data.extensions

import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.net.ssl.SSLException
import kotlin.experimental.ExperimentalTypeInference
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import leegroup.module.data.remote.models.responses.ErrorResponse
import leegroup.module.data.remote.models.responses.mapToError
import leegroup.module.domain.exceptions.ApiException
import leegroup.module.domain.exceptions.NoConnectivityException
import leegroup.module.domain.exceptions.ServerException
import retrofit2.HttpException
import retrofit2.Response

@OptIn(ExperimentalTypeInference::class)
fun <T> flowTransform(@BuilderInference block: suspend FlowCollector<T>.() -> T) = flow {
    runCatching { block() }
        .onSuccess { result -> emit(result) }
        .onFailure { exception -> throw exception.mapError() }
}

suspend fun <T> transform(block: suspend () -> T): T {
    return runCatching {
        block()
    }.getOrElse { exception ->
        throw exception.mapError()
    }
}

private fun Throwable.mapError(): Throwable {
    return when (this) {
        is UnknownHostException,
        is SSLException,
        is InterruptedIOException -> NoConnectivityException

        is ConnectException -> ServerException
        is HttpException -> {
            val errorResponse = parseErrorResponse(response())
            ApiException(
                errorResponse?.mapToError(),
                code(),
                message()
            )
        }

        else -> this
    }
}

private fun parseErrorResponse(response: Response<*>?): ErrorResponse? {
    val jsonString = response?.errorBody()?.string() ?: return null
    return try {
        Json.decodeFromString<ErrorResponse>(jsonString)
    } catch (ex: SerializationException) {
        null
    } catch (ex: IllegalArgumentException) {
        null
    }
}
