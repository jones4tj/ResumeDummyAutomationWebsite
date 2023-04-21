package Loggers;

import org.apache.logging.log4j.LogManager;

public class Log4jLogger implements Logger {
	
	private org.apache.logging.log4j.Logger log4j_logger;
	
	public Log4jLogger(String class_name) {
		this.log4j_logger = LogManager.getLogger(class_name);
	}

	public void trace(String message) {
		log4j_logger.trace(message);
	}

	public void debug(String message) {
		log4j_logger.debug(message);
	}

	public void info(String message) {
		log4j_logger.info(message);
	}

	public void warn(String message) {
		log4j_logger.warn(message);
	}

	public void error(String message) {
		log4j_logger.error(message);
	}

	public void fatal(String message) {
		log4j_logger.fatal(message);
	}

}
