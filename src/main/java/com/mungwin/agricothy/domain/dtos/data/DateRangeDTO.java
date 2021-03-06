package com.mungwin.agricothy.domain.dtos.data;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DateRangeDTO {
  private LocalDateTime startDate;
  private LocalDateTime stopDate;
}
