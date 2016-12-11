package interactor.utils;

import java.lang.reflect.Method;

public class TitleGetter {
	public static String getTitle() {
		StackTraceElement[] stack = new Throwable().getStackTrace();
		String methodName = stack[1].getMethodName();
		Class<?> clazz = stack[1].getClass();
		Method[] ms = clazz.getClass().getMethods();

		for (Method m : ms)
			if (m.getName().equals(methodName))
				return m.getAnnotation(Title.class).value();

		return "";
	}
}
