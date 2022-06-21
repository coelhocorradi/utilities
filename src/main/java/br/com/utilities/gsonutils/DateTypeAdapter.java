package br.com.utilities.gsonutils;

import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import br.com.utilities.datetime.DateConverter;
import br.com.utilities.datetime.DateFormater;
import br.com.utilities.datetime.DateFormats;
import br.com.utilities.datetime.DateParser;

import br.com.utilities.regexes.RegexUtils;

public class DateTypeAdapter extends TypeAdapter<Date> {

	private static final TypeAdapter<Date> dateTypeAdapter = new DateTypeAdapter();

	public static TypeAdapter<Date> getAdapter() {
		return dateTypeAdapter;
	}

	private JsonToken tokenType;

	private String format;

	private DateTypeAdapter() {
		tokenType = JsonToken.NUMBER;
		format = DateFormats.SIMPLE_TIMESTAMP;
	}

	public void setWriteType(JsonToken tokenType) {
		this.tokenType = tokenType;
	}

	@Override
	public Date read(final JsonReader in) throws IOException {
		// this is where the conversion is performed

		Date result = null;

		// JsonToken check = in.peek();
		switch (in.peek()) {
		case NULL: {
			// quando nulo, apenas l� o pr�ximo nulo
			in.nextNull();
		}
			break;
		case NUMBER: {
			// deve converter o inteiro longo em data
			result = DateConverter.convert(in.nextLong());
		}
			break;
		case STRING: {
			String strtime = in.nextString();
			if (!strtime.isEmpty()) {

				Pattern p = Pattern.compile(RegexUtils.datetimemili);
				Matcher m = p.matcher(strtime);
				if (m.find()) {
					result = DateParser.parse(DateFormats.DATE_TIME_MILI, strtime);
				}

				if (result == null) {
					p = Pattern.compile(RegexUtils.datetime);
					m = p.matcher(strtime);
					if (m.find()) {
						result = DateParser.fromDateTime(strtime);
					}
				}

				if (result == null) {
					p = Pattern.compile(RegexUtils.calendar);
					m = p.matcher(strtime);
					if (m.find()) {
						result = DateParser.fromCalendar(strtime);
					}
				}

				if (result == null) {
					p = Pattern.compile(RegexUtils.timestampmiliexp);
					m = p.matcher(strtime);
					if (m.find()) {
						strtime = strtime.substring(0, 24);
						result = DateParser.parse(DateFormats.TIMESTAMP, strtime);
					}
				}

			}
		}
			break;
		default:
			break;
		}

		return result;
	}

	@Override
	public void write(final JsonWriter out, final Date value) throws IOException {
		// write back if necessary or throw UnsupportedOperationException
		if (value != null) {
			switch (tokenType) {
			case NUMBER: {
				out.value(value.getTime());
			}
				break;
			case STRING: {
				switch (format) {
				case DateFormats.DATE_TIME: {
					out.value(DateFormater.toDateTime(value));
				}
					break;
				case DateFormats.DATE_TIME_MILI: {
					out.value(DateFormater.format(DateFormats.DATE_TIME_MILI, value));
				}
					break;
				case DateFormats.CALENDAR: {
					out.value(DateFormater.toCalendar(value));
				}
					break;
				case DateFormats.TIMESTAMP: {
					out.value(DateFormater.format(DateFormats.TIMESTAMP, value));
				}
					break;
				case DateFormats.SIMPLE_TIMESTAMP:
				default: {
					out.value(DateFormater.toSimpleTimestamp(value));
				}
					break;
				}
			}
				break;
			default: {
				out.nullValue();
			}
				break;
			}
		} else {
			out.nullValue();
		}
	}

}
