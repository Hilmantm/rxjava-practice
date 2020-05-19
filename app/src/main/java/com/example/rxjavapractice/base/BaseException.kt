package com.example.rxjavapractice.base

import java.lang.Exception

class BaseException(baseResponse: BaseResponse) : Exception() {
    var response: BaseResponse = baseResponse

}