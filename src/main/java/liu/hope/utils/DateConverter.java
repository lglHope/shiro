package liu.hope.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String, Date> {

	private String[][] arrs = { { "yyyy-MM-dd HH:mm:ss", "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}" },
			{ "yyyy/MM/dd", "\\d{4}/\\d{2}/\\d{2}" },
			{ "yyyy-MM-dd", "\\d{4}-\\d{2}-\\d{2}" },
			{ "yyyyMMdd", "\\d{8}" }
	};

	public Date convert(String source) {
		try {
			for (String[] strings : arrs) {
				if (source.matches(strings[1])) {
					return new SimpleDateFormat(strings[0]).parse(source);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
