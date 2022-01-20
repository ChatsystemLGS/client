package client.simplelogger;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BasicLogger implements SimpleLogger.LogListener {

	private static final SimpleLogger.LogLevel DEFAULT_LOGLEVEL = SimpleLogger.LogLevel.INFO;
	private static final String DEFAULT_DATE_FORMAT = "dd-mm-yy-hh:mm:ss";

	private final SimpleLogger.LogLevel logLevel;
	private final SimpleDateFormat dateFormat;

	BasicLogger() {
		this(DEFAULT_LOGLEVEL);
	}

	BasicLogger(SimpleLogger.LogLevel logLevel) {
		this(logLevel, DEFAULT_DATE_FORMAT);
	}

	BasicLogger(SimpleLogger.LogLevel logLevel, String dateFormat) {
		this.logLevel = logLevel;
		this.dateFormat = new SimpleDateFormat(dateFormat);
		SimpleLogger.addLogListener(this);
	}

	@Override
	public void log(String message, SimpleLogger.LogLevel logLevel) {
		if (logLevel.ordinal() >= this.logLevel.ordinal())
			writeLog(logString(message, logLevel));
	}

	private String logString(String s, SimpleLogger.LogLevel logLevel) {
		return String.format("%s - [%s]: %s", logLevel, dateFormat.format(new Date()), s);
	}

	protected abstract void writeLog(String logString);

}
