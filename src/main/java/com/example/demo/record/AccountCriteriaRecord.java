package com.example.demo.record;

import java.util.List;

public record AccountCriteriaRecord(Long moreServicesThan,
                                    List<Long> accountIds,
                                    String name,
                                    String surname) {
}
