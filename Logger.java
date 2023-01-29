public class Logger {
    public String name;

    public String getName() {
        return name;
    }
}

public abstract class AbstractLogger extends Logger{
	public Calendar calendar;

    public abstract String info(String text);
    public abstract String debug(String text);
    public abstract String error(String text);
    public abstract String warning(String text);
    public abstract String toString(String text);

	public void setCalendar(TimeZone timeZone, int year, int month, int day, int hour, int minutes, int seconds) {
		TimeZone.setDefault(timeZone);
        calendar = Calendar.getInstance(timeZone);
        calendar.set(year, month - 1, day, hour, minutes, seconds);
    }

}

public class DefaultLogger extends AbstractLogger {

    public DefaultLogger(String title) {
        this.name = title;
    }

    @Override
    public String info(String text) {
        return String.format("[INFO] <%s> %s - %s", calendar.getTime(), name, text);
    }

    @Override
    public String debug(String text) {
        return String.format("[DEBUG] <%s> %s - %s", calendar.getTime(), name, text);
    }

    @Override
    public String error(String text) {
        return String.format("[ERROR] <%s> %s - %s", calendar.getTime(), name, text);
    }

    @Override
    public String warning(String text) {
        return String.format("[WARNING] <%s> %s - %s", calendar.getTime(), name, text);
    }

    @Override
    public String toString(String text) {
        return String.format("NAME - %s", this.getClass().getSimpleName());
    }
}


public class InputControlLogger extends AbstractLogger {

    public InputControlLogger(String title) {
        this.name = title;
    }


    @Override
    public String info(String text) {
        return String.format("[INFO] <%s> Вход пользователя: %s", calendar.getTime(), text);
    }

    @Override
    public String debug(String text) {
        return String.format("[DEBUG] <%s> Обнаружен пользователь: %s", calendar.getTime(), text);
    }

    @Override
    public String error(String text) {
        return String.format("[ERROR] <%s> Не удалось найти данные пользователя: %s", calendar.getTime(), text);
    }

    @Override
    public String warning(String text) {
        return String.format("[WARNING] <%s> Обнаружен неавторизованный доступ в систему: %s", calendar.getTime(), text);
    }

    @Override
    public String toString(String text) {
        return String.format("NAME - %s", this.getClass().getSimpleName());
    }
}

public class LogManager {
    List<Logger> loggers = new ArrayList<>();

    public void addLogger(Logger logger) {
        loggers.add(logger);
    }

    public Logger getLogger(String name) {
        for (Logger logger: loggers) {
            if (logger.getName() == name) {
                return logger;
            }
        }
        return null;
    }

    public String printLoggers() {
        StringJoiner stringJoiner = new StringJoiner(" - ");
        for (Logger logger: loggers) {
            String name = logger.toString();
            stringJoiner.add(name);
        }
        return stringJoiner.toString();
    }
}
