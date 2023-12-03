package com.project.springecommerceapi.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.persistence.NoResultException;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.project.springecommerceapi.error.ValidationError;
import com.project.springecommerceapi.exceptions.AlreadyVerifiedEmailException;
import com.project.springecommerceapi.exceptions.CartItemNotFoundException;
import com.project.springecommerceapi.exceptions.CartItemQuantityLimitReachedException;
import com.project.springecommerceapi.exceptions.CartNotFoundException;
import com.project.springecommerceapi.exceptions.CategoryNotFoundException;
import com.project.springecommerceapi.exceptions.ColorNotFoundException;
import com.project.springecommerceapi.exceptions.ColorProductVariantExistsException;
import com.project.springecommerceapi.exceptions.ColorProductVariantNotFoundException;
import com.project.springecommerceapi.exceptions.EmailExistsException;
import com.project.springecommerceapi.exceptions.FileUploadException;
import com.project.springecommerceapi.exceptions.NoItemsToPayException;
import com.project.springecommerceapi.exceptions.HexcodeExistsException;
import com.project.springecommerceapi.exceptions.InvalidImageFileException;
import com.project.springecommerceapi.exceptions.InvalidOperationException;
import com.project.springecommerceapi.exceptions.InvalidTokenException;
import com.project.springecommerceapi.exceptions.NotEnoughStockException;
import com.project.springecommerceapi.exceptions.OrderNotFoundException;
import com.project.springecommerceapi.exceptions.PaymentTransactionFailedException;
import com.project.springecommerceapi.exceptions.ProductNotFoundException;
import com.project.springecommerceapi.exceptions.SizeColorProductVariantExistsException;
import com.project.springecommerceapi.exceptions.SizeColorProductVariantNotFoundException;
import com.project.springecommerceapi.exceptions.SlugExistsException;
import com.project.springecommerceapi.exceptions.UserNotFoundException;
import com.project.springecommerceapi.exceptions.VerifiedEmailRequiredException;
import com.project.springecommerceapi.response.ErrorResponse;

@RestControllerAdvice
public class CustomExceptionHandler {
    private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status,
            String message,
            Map<String, List<ValidationError>> validationErrors) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(status)
                .statusCode(status.value())
                .message(message)
                .reason(status.getReasonPhrase())
                .validationErrors(validationErrors)
                .timestamp(new Date())
                .build();
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationError(MethodArgumentNotValidException e) {
        Map<String, List<ValidationError>> validationErrors = new HashMap<>();
        ResponseEntity<ErrorResponse> errorResponseResponseEntity = buildErrorResponse(HttpStatus.BAD_REQUEST,
                "Validation Error", validationErrors);

        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        for (FieldError fieldError : fieldErrors) {
            List<ValidationError> validationErrorList = Objects.requireNonNull(errorResponseResponseEntity.getBody())
                    .getValidationErrors().get(fieldError.getField());
            if (validationErrorList == null) {
                validationErrorList = new ArrayList<>();
                errorResponseResponseEntity.getBody().getValidationErrors().put(fieldError.getField(),
                        validationErrorList);
            }
            ValidationError validationError = ValidationError.builder()
                    .code(fieldError.getCode())
                    .message(fieldError.getDefaultMessage())
                    .build();
            validationErrorList.add(validationError);
        }

        return errorResponseResponseEntity;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleInternalServerError(Exception e) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, AppConstants.INTERNAL_SERVER_ERROR, null);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e) {
        return buildErrorResponse(HttpStatus.UNAUTHORIZED, AppConstants.ACCESS_DENIED, null);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleIncorrectCredentialsError(BadCredentialsException e) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, AppConstants.INCORRECT_CREDENTIALS, null);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<ErrorResponse> handleTokenExpiredException(TokenExpiredException e) {
        return buildErrorResponse(HttpStatus.UNAUTHORIZED, AppConstants.INVALID_TOKEN, null);
    }

    @ExceptionHandler(SignatureVerificationException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTokenException(SignatureVerificationException e) {
        return buildErrorResponse(HttpStatus.UNAUTHORIZED, AppConstants.INVALID_TOKEN, null);
    }

    @ExceptionHandler(AuthenticationServiceException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTokenException(AuthenticationServiceException e) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, AppConstants.INCORRECT_CREDENTIALS, null);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTokenException(InvalidTokenException e) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, AppConstants.INVALID_TOKEN, null);
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundError(NoResultException e) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, AppConstants.NOT_FOUND_ERROR, null);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleUnsupportedMethodError(HttpRequestMethodNotSupportedException e) {
        HttpMethod supportedMethod = Objects.requireNonNull(e.getSupportedHttpMethods()).iterator().next();
        return buildErrorResponse(HttpStatus.METHOD_NOT_ALLOWED,
                String.format(AppConstants.METHOD_NOT_ALLOWED, supportedMethod),
                null);
    }

    // NOT FOUND EXCEPTIONS

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, AppConstants.USER_NOT_FOUND, null);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNotFoundException(CategoryNotFoundException e) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, AppConstants.CATEGORY_NOT_FOUND, null);
    }

    @ExceptionHandler(ColorNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleColorNotFoundException(ColorNotFoundException e) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, AppConstants.COLOR_NOT_FOUND, null);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException e) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, AppConstants.PRODUCT_NOT_FOUND, null);
    }

    @ExceptionHandler(ColorProductVariantNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleColorProductVariantNotFoundException(
            ColorProductVariantNotFoundException e) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, AppConstants.COLOR_PRODUCT_VARIANT_NOT_FOUND, null);
    }

    @ExceptionHandler(SizeColorProductVariantNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSizeColorProductVariantNotFoundException(
            SizeColorProductVariantNotFoundException e) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, AppConstants.SIZE_COLOR_PRODUCT_VARIANT_NOT_FOUND,
                null);
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCartNotFoundException(CartNotFoundException e) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, AppConstants.CART_NOT_FOUND, null);
    }

    @ExceptionHandler(CartItemNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCartItemNotFoundException(CartItemNotFoundException e) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, AppConstants.CART_ITEM_NOT_FOUND, null);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOrderNotFoundException(OrderNotFoundException e) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, AppConstants.ORDER_NOT_FOUND, null);
    }

    // EXISTS EXCEPTIONS

    @ExceptionHandler(EmailExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailExistsException(EmailExistsException e) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, AppConstants.EMAIL_EXISTS, null);
    }

    @ExceptionHandler(SlugExistsException.class)
    public ResponseEntity<ErrorResponse> handleSlugExistsException(SlugExistsException e) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, AppConstants.SLUG_EXISTS, null);
    }

    @ExceptionHandler(HexcodeExistsException.class)
    public ResponseEntity<ErrorResponse> handleHexcodeExistsException(HexcodeExistsException e) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, AppConstants.HEXCODE_EXISTS, null);
    }

    @ExceptionHandler(ColorProductVariantExistsException.class)
    public ResponseEntity<ErrorResponse> handleColorProductVariantExistsException(
            ColorProductVariantExistsException e) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, AppConstants.COLOR_PRODUCT_VARIANT_EXISTS, null);
    }

    @ExceptionHandler(SizeColorProductVariantExistsException.class)
    public ResponseEntity<ErrorResponse> handleSizeColorProductVariantExistsException(
            SizeColorProductVariantExistsException e) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, AppConstants.SIZE_COLOR_PRODUCT_VARIANT_EXISTS, null);
    }

    // OTHER EXCEPTIONS

    @ExceptionHandler(NotEnoughStockException.class)
    public ResponseEntity<ErrorResponse> handleNotEnoughStockException(NotEnoughStockException e) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, AppConstants.NOT_ENOUGH_STOCK, null);
    }

    @ExceptionHandler(AlreadyVerifiedEmailException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyVerifiedEmailException(AlreadyVerifiedEmailException e) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, AppConstants.ALREADY_VERIFIED_EMAIL, null);
    }

    @ExceptionHandler(CartItemQuantityLimitReachedException.class)
    public ResponseEntity<ErrorResponse> handleCartItemQuantityLimitReachedException(
            CartItemQuantityLimitReachedException e) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, AppConstants.CART_ITEM_QUANTITY_LIMIT_REACHED,
                null);
    }

    @ExceptionHandler(NoItemsToPayException.class)
    public ResponseEntity<ErrorResponse> handleNoItemsToPayException(NoItemsToPayException e) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, AppConstants.NO_ITEMS_TO_PAY, null);
    }

    @ExceptionHandler(VerifiedEmailRequiredException.class)
    public ResponseEntity<ErrorResponse> handleVerifiedEmailRequiredException(VerifiedEmailRequiredException e) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, AppConstants.VERIFIED_EMAIL_REQUIRED, null);
    }

    @ExceptionHandler(PaymentTransactionFailedException.class)
    public ResponseEntity<ErrorResponse> handlePaymentTransactionFailedException(PaymentTransactionFailedException e) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, AppConstants.PAYMENT_TRANSACTION_FAILED, null);
    }

    @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity<ErrorResponse> handleInvalidOperationException(InvalidOperationException e) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, AppConstants.INVALID_OPERATION, null);
    }

    @ExceptionHandler(InvalidImageFileException.class)
    public ResponseEntity<ErrorResponse> handleInvalidImageFileException(InvalidImageFileException e) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, AppConstants.INVALID_IMAGE_FILE, null);
    }

    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<ErrorResponse> handleFileUploadException(FileUploadException e) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, AppConstants.FILE_UPLOAD_FAILED, null);
    }

}
