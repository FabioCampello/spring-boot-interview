package com.interview.commons.util;

import static org.springframework.util.ObjectUtils.isEmpty;

import com.interview.commons.exception.ApiException;

public class ValidacoesUtil {

	private ValidacoesUtil() {
		throw new IllegalStateException("Classe utilitária.");
	}

	/**
	 * VALIDA PARAMETRO E CONVERT STRING TO LONG
	 */
	public static Long validaIdConvertStringToLong(String param, String msg) throws ApiException {
		try {
			return Long.parseLong(param);
		} catch (Exception e) {
			throw new ApiException(msg);
		}
	}

	/**
	 * VALIDA PARAMETRO STRING
	 */
	public static void validaParametroString(String parametro, String msg) throws ApiException {
		if (isEmpty(parametro)) {
			throw new ApiException(msg);
		}
	}

}
