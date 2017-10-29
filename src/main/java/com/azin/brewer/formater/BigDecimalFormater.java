package com.azin.brewer.formater;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

public class BigDecimalFormater implements Formatter<BigDecimal> {

	@Override
	public BigDecimal parse(String text, Locale locale) throws ParseException {
		DecimalFormat format = (DecimalFormat) NumberFormat.getNumberInstance(locale);
		format.applyPattern("#,##0.00");
		format.setParseBigDecimal(true);
		format.setGroupingUsed(true);
		return (BigDecimal) format.parse(text);
	}

	@Override
	public String print(BigDecimal object, Locale locale) {
		DecimalFormat format = (DecimalFormat) NumberFormat.getNumberInstance(locale);
		format.applyPattern("#,##0.00");
		format.setParseBigDecimal(true);
		format.setGroupingUsed(false);
		return object.toString();
	}
}
