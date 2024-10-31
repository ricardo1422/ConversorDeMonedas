package com.aluracursos.records;

import java.util.HashMap;

public record RecordMoneda(String base_code, HashMap<String, Double> conversion_rates ) {
}
