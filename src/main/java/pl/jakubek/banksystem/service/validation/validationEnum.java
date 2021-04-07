package pl.jakubek.banksystem.service.validation;

public enum validationEnum {
    LOGIN("login-failed"),
    PASSWORD("password-failed"),
    REPEATED("repeated-failed"),
    VALIDATED("validated");

    private final String response;

    validationEnum(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }
}
