package com.example.ch_machinetest.sharedpreference


import com.example.ch_machinetest.api.APIClient
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException

class ErrorUtils {

    companion object {

        fun parseError(response: Response<*>): APIError? {

            val converter: Converter<ResponseBody, APIError> = APIClient.client
                .responseBodyConverter(APIError::class.java, arrayOfNulls<Annotation>(0))
            val error: APIError

            error = try {
                converter.convert(response.errorBody())!!
            } catch (e: IOException) {
                return APIError()
            }
            return error
        }
    }
}