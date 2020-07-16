package com.interview.commons.util;

import static org.springframework.util.ObjectUtils.isEmpty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.interview.commons.exception.ApiException;

public class ValidacoesUtil {

	private ValidacoesUtil() {
		throw new IllegalStateException("Classe utilitária.");
	}

	/**
	 * VALIDA PARAMETRO E CONVERT STRING TO LONG
	 */
	public static Long validaParamConvertStringToLong(String param, String msg) throws ApiException {
		try {
			return Long.parseLong(param);
		} catch (Exception e) {
			throw new ApiException(msg);
		}
	}
	
	/**
	 * VALIDA PARAMETRO E CONVERT STRING TO INTEGER
	 */
	public static Integer validaParamConvertStringToInteger(String param, String msg) throws ApiException {
		try {
			return Integer.parseInt(param);
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
	
	/**
	 * CONVERTE STRING TO DATE
	 */
	public static Date StringParaDate(String data) throws ParseException {
		Date dataFormatada = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(data);
		return dataFormatada;
	}

}
