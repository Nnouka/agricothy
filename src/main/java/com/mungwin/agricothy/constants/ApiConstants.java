package com.mungwin.agricothy.constants;
/**
 * @author nouks
 *
 * @date 18 Oct 2019
 */
public final class ApiConstants {
    public static final String APP_NAME = "Agrica";
    public static final String JSON_CONTENT_TYPE = "application/json";
    public static final String ACCOUNT_ACTIVATION_SUBJECT = "[" + APP_NAME + "] Verification";
    public static final String VOUCHER_SUBJECT = "[" + APP_NAME + "] Payment Voucher";
    public static final String VOUCHER_REQUEST_SUBJECT = "[" + APP_NAME + "] Payment Voucher Request";
    public static final String PAYMENT_DETAILS_SUBJECT = "[" + APP_NAME + "] Payment Transaction Successful";
    public static final String GET_STARTED_SUBJECT = "[" + APP_NAME + "] Get Started";
    public static final String URGENT_NOTIFICATION_SUBJECT = "Urgent: Notification";
    public static final String NOTIFICATION_SUBJECT = "Impact LLC: Notification";
    public static final String PASSWORD_RESET_SUBJECT = "[" + APP_NAME + "] Password reset";
    public static final String ACCOUNT_ACTIVATION_FROM_ADDRESS = "no-reply@agrica.com";
    public static final String NO_REPLY_FROM_ADDRESS = "no-reply@agrica.com";
    public static final String INFO_FROM_ADDRESS = "info@agrica.com";
    public static final String REFERRAL_SUBJECT = "Team Referral";

    private ApiConstants() {
        throw new IllegalStateException("Cannot create instance of static util class");
    }
}
