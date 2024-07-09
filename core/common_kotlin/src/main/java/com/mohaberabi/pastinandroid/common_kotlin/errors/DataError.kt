package com.mohaberabi.pastinandroid.common_kotlin.errors

interface DataError : PiaError {


    enum class Network : DataError {
        REQUEST_TIMEOUT,
        UNAUTHORIZED,
        CONFLICT,
        TOO_MANY_REQUEST,
        NO_NETWORK,
        PAYLOAD_TOO_LARGE,
        SERVER_ERROR,
        SERIALIZATION_ERROR,
        CANCELED,
        INVALID_ARGUMENT,
        DEADLINE_EXCEEDED,
        ABORTED,
        FAILED_PRECONDITION,
        RESOURCE_EXHAUSTED,
        PERMISSION_DENIED,
        UNAVAILABLE,
        UNIMPLEMENTED,
        OUT_OF_RANGE,
        DATA_LOSS,
        UNKNOWN_ERROR
    }

    enum class Local : DataError {
        DISK_FULL,
        UNKNOWN,
        NOT_FOUND,

    }
}

data class PiaException(val exception: Throwable) : PiaError