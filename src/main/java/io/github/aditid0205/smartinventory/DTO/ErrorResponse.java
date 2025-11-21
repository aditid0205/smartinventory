package io.github.aditid0205.smartinventory.DTO;

import java.time.LocalDateTime;

public class ErrorResponse {
    private String errormessage;
    private int status;
    private LocalDateTime timestamp;

    public ErrorResponse(String errormessage, int status) {
        this.errormessage = errormessage;
        this.status = status;
        this.timestamp = LocalDateTime.now();

    }

    public String getErrormessage() {
        return errormessage;
    }

    public void setErrormessage(String errormessage) {
        this.errormessage = errormessage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
