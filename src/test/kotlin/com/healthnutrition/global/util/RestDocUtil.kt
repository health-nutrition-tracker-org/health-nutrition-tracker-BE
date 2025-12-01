package com.healthnutrition.global.util

import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor
import org.springframework.restdocs.operation.preprocess.Preprocessors

object RestDocUtil {
	fun requestPreprocessor(): OperationRequestPreprocessor? {
		return Preprocessors.preprocessRequest(Preprocessors.prettyPrint())
	}

	fun responsePreprocessor(): OperationResponsePreprocessor? {
		return Preprocessors.preprocessResponse(Preprocessors.prettyPrint())
	}
}
