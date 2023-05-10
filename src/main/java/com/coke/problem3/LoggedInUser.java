package com.coke.problem3;

public class LoggedInUser {
    private static final ThreadLocal<UserDetail> USER_DETAILS =
            ThreadLocal.withInitial(() -> new UserDetail("admin@gmail.com"));

    public static ThreadLocal<UserDetail> getThreadLocal() {
        return USER_DETAILS;
    }

    public static void switchUser(String emailAddress) {
        USER_DETAILS.set(new UserDetail(emailAddress));
    }
}
