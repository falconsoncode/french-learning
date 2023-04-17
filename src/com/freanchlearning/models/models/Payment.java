package com.freanchlearning.models.models;

import java.time.LocalDateTime;

public class Payment {
    private long id;
    private long studentId;
    private long courseId;
    private double paidAmount;
    private LocalDateTime date;
}
