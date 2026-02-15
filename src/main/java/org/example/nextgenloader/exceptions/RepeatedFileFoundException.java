package org.example.nextgenloader.exceptions;

import java.io.IOException;

public class RepeatedFileFoundException extends IOException {
    String number;
    public RepeatedFileFoundException(String number) {
        this.number=number;
    }

    public String getNumber() {
        return number;
    }
}
