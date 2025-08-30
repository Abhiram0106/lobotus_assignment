package com.github.abhiram0106.lobotus_assignment.core.util

class UnableToReachServerException(msg: String = "Unable to reach the server") : Exception(msg)
class AnErrorHasOccurredException(msg: String = "An error has occurred") : Exception(msg)
class NetworkException(val code: Int, msg: String) : Exception(msg)