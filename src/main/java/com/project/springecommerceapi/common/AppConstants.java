package com.project.springecommerceapi.common;

public class AppConstants {
    public static final Long JWT_AUTHENTICATION_EXPIRATION = 3 * 86400000L; // 3 dias
    public static final Long JWT_VERIFY_EMAIL_EXPIRATION = 60 * 60 * 1000L; // 60 minutos
    public static final Long JWT_RESET_PASSWORD_EXPIRATION = 15 * 60 * 1000L; // 15 minutos
    public static final Long CART_EXPIRATION = 28 * 86400000L; // 28 dias

    public static final String AUTHORITIES = "authorities";
    public static final String TOKEN_HEADER = "Jwt-Token";
    public static final String OPTIONS_HTTP_METHOD = "options";
    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String VERIFY_EMAIL = "Verify your email";
    public static final String RESET_PASSWORD = "Reset your password";
    public static final String CHECK_EMAIL = "Please, check your email.";

    public static final String ACCESS_DENIED = "You don't have permission to access this resource.";
    public static final String FORBIDDEN = "You need to be logged in to access this resource.";
    public static final String NOT_FOUND_ERROR = "404 Not Found.";
    public static final String METHOD_NOT_ALLOWED = "This operation is not allowed. Only %s operations are allowed.";
    public static final String INVALID_TOKEN = "Token is not valid.";
    public static final String INTERNAL_SERVER_ERROR = "An error occurred while processing your request.";
    public static final String INCORRECT_CREDENTIALS = "Incorrect username or password.";
    public static final String TOKEN_UNVERIFIABLE = "Token cannot be verified.";

    public static final String EMAIL_PATTERN = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>.]).{6,32}$";

    public static final String USER_NOT_FOUND = "No user found.";
    public static final String CATEGORY_NOT_FOUND = "No category found.";
    public static final String COLOR_NOT_FOUND = "No color found.";
    public static final String PRODUCT_NOT_FOUND = "No product found.";
    public static final String COLOR_PRODUCT_VARIANT_NOT_FOUND = "No color product variant found.";
    public static final String SIZE_COLOR_PRODUCT_VARIANT_NOT_FOUND = "No size color product variant found.";
    public static final String CART_NOT_FOUND = "No cart found.";
    public static final String CART_ITEM_NOT_FOUND = "No cart item found.";

    public static final String EMAIL_EXISTS = "User exists with this email address.";
    public static final String SLUG_EXISTS = "Slug already exists.";
    public static final String HEXCODE_EXISTS = "Hexcode already exists.";
    public static final String COLOR_PRODUCT_VARIANT_EXISTS = "Color variant already exists for product.";
    public static final String SIZE_COLOR_PRODUCT_VARIANT_EXISTS = "Size variant already exists for color product variant.";
    public static final String ALREADY_VERIFIED_EMAIL = "This email is already verified.";

    public static final String NOT_ENOUGH_STOCK_EXCEPTION = "Not enough stock for product.";

}
