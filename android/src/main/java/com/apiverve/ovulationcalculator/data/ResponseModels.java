// Converter.java

// To use this code, add the following Maven dependency to your project:
//
//
//     com.fasterxml.jackson.core     : jackson-databind          : 2.9.0
//     com.fasterxml.jackson.datatype : jackson-datatype-jsr310   : 2.9.0
//
// Import this package:
//
//     import com.apiverve.data.Converter;
//
// Then you can deserialize a JSON string with
//
//     OvulationCalculatorData data = Converter.fromJsonString(jsonString);

package com.apiverve.ovulationcalculator.data;

import java.io.IOException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class Converter {
    // Date-time helpers

    private static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ISO_DATE_TIME)
            .appendOptional(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            .appendOptional(DateTimeFormatter.ISO_INSTANT)
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SX"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssX"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            .toFormatter()
            .withZone(ZoneOffset.UTC);

    public static OffsetDateTime parseDateTimeString(String str) {
        return ZonedDateTime.from(Converter.DATE_TIME_FORMATTER.parse(str)).toOffsetDateTime();
    }

    private static final DateTimeFormatter TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ISO_TIME)
            .appendOptional(DateTimeFormatter.ISO_OFFSET_TIME)
            .parseDefaulting(ChronoField.YEAR, 2020)
            .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
            .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
            .toFormatter()
            .withZone(ZoneOffset.UTC);

    public static OffsetTime parseTimeString(String str) {
        return ZonedDateTime.from(Converter.TIME_FORMATTER.parse(str)).toOffsetDateTime().toOffsetTime();
    }
    // Serialize/deserialize helpers

    public static OvulationCalculatorData fromJsonString(String json) throws IOException {
        return getObjectReader().readValue(json);
    }

    public static String toJsonString(OvulationCalculatorData obj) throws JsonProcessingException {
        return getObjectWriter().writeValueAsString(obj);
    }

    private static ObjectReader reader;
    private static ObjectWriter writer;

    private static void instantiateMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        SimpleModule module = new SimpleModule();
        module.addDeserializer(OffsetDateTime.class, new JsonDeserializer<OffsetDateTime>() {
            @Override
            public OffsetDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
                String value = jsonParser.getText();
                return Converter.parseDateTimeString(value);
            }
        });
        mapper.registerModule(module);
        reader = mapper.readerFor(OvulationCalculatorData.class);
        writer = mapper.writerFor(OvulationCalculatorData.class);
    }

    private static ObjectReader getObjectReader() {
        if (reader == null) instantiateMapper();
        return reader;
    }

    private static ObjectWriter getObjectWriter() {
        if (writer == null) instantiateMapper();
        return writer;
    }
}

// OvulationCalculatorData.java

package com.apiverve.ovulationcalculator.data;

import com.fasterxml.jackson.annotation.*;
import java.time.LocalDate;

public class OvulationCalculatorData {
    private LocalDate lastPeriod;
    private long cycleLength;
    private NextPeriod ovulation;
    private FertileWindow fertileWindow;
    private FertileWindow mostFertileDays;
    private FertileDay[] fertileDays;
    private NextPeriod nextPeriod;
    private CyclePhases cyclePhases;
    private CurrentStatus currentStatus;
    private String disclaimer;

    @JsonProperty("last_period")
    public LocalDate getLastPeriod() { return lastPeriod; }
    @JsonProperty("last_period")
    public void setLastPeriod(LocalDate value) { this.lastPeriod = value; }

    @JsonProperty("cycle_length")
    public long getCycleLength() { return cycleLength; }
    @JsonProperty("cycle_length")
    public void setCycleLength(long value) { this.cycleLength = value; }

    @JsonProperty("ovulation")
    public NextPeriod getOvulation() { return ovulation; }
    @JsonProperty("ovulation")
    public void setOvulation(NextPeriod value) { this.ovulation = value; }

    @JsonProperty("fertile_window")
    public FertileWindow getFertileWindow() { return fertileWindow; }
    @JsonProperty("fertile_window")
    public void setFertileWindow(FertileWindow value) { this.fertileWindow = value; }

    @JsonProperty("most_fertile_days")
    public FertileWindow getMostFertileDays() { return mostFertileDays; }
    @JsonProperty("most_fertile_days")
    public void setMostFertileDays(FertileWindow value) { this.mostFertileDays = value; }

    @JsonProperty("fertile_days")
    public FertileDay[] getFertileDays() { return fertileDays; }
    @JsonProperty("fertile_days")
    public void setFertileDays(FertileDay[] value) { this.fertileDays = value; }

    @JsonProperty("next_period")
    public NextPeriod getNextPeriod() { return nextPeriod; }
    @JsonProperty("next_period")
    public void setNextPeriod(NextPeriod value) { this.nextPeriod = value; }

    @JsonProperty("cycle_phases")
    public CyclePhases getCyclePhases() { return cyclePhases; }
    @JsonProperty("cycle_phases")
    public void setCyclePhases(CyclePhases value) { this.cyclePhases = value; }

    @JsonProperty("current_status")
    public CurrentStatus getCurrentStatus() { return currentStatus; }
    @JsonProperty("current_status")
    public void setCurrentStatus(CurrentStatus value) { this.currentStatus = value; }

    @JsonProperty("disclaimer")
    public String getDisclaimer() { return disclaimer; }
    @JsonProperty("disclaimer")
    public void setDisclaimer(String value) { this.disclaimer = value; }
}

// CurrentStatus.java

package com.apiverve.ovulationcalculator.data;

import com.fasterxml.jackson.annotation.*;

public class CurrentStatus {
    private String currentPhase;
    private boolean isFertile;
    private long daysUntilOvulation;
    private long daysUntilNextPeriod;

    @JsonProperty("current_phase")
    public String getCurrentPhase() { return currentPhase; }
    @JsonProperty("current_phase")
    public void setCurrentPhase(String value) { this.currentPhase = value; }

    @JsonProperty("is_fertile")
    public boolean getIsFertile() { return isFertile; }
    @JsonProperty("is_fertile")
    public void setIsFertile(boolean value) { this.isFertile = value; }

    @JsonProperty("days_until_ovulation")
    public long getDaysUntilOvulation() { return daysUntilOvulation; }
    @JsonProperty("days_until_ovulation")
    public void setDaysUntilOvulation(long value) { this.daysUntilOvulation = value; }

    @JsonProperty("days_until_next_period")
    public long getDaysUntilNextPeriod() { return daysUntilNextPeriod; }
    @JsonProperty("days_until_next_period")
    public void setDaysUntilNextPeriod(long value) { this.daysUntilNextPeriod = value; }
}

// CyclePhases.java

package com.apiverve.ovulationcalculator.data;

import com.fasterxml.jackson.annotation.*;

public class CyclePhases {
    private FollicularPhase follicularPhase;
    private FollicularPhase ovulation;
    private FollicularPhase lutealPhase;

    @JsonProperty("follicular_phase")
    public FollicularPhase getFollicularPhase() { return follicularPhase; }
    @JsonProperty("follicular_phase")
    public void setFollicularPhase(FollicularPhase value) { this.follicularPhase = value; }

    @JsonProperty("ovulation")
    public FollicularPhase getOvulation() { return ovulation; }
    @JsonProperty("ovulation")
    public void setOvulation(FollicularPhase value) { this.ovulation = value; }

    @JsonProperty("luteal_phase")
    public FollicularPhase getLutealPhase() { return lutealPhase; }
    @JsonProperty("luteal_phase")
    public void setLutealPhase(FollicularPhase value) { this.lutealPhase = value; }
}

// FollicularPhase.java

package com.apiverve.ovulationcalculator.data;

import com.fasterxml.jackson.annotation.*;

public class FollicularPhase {
    private long durationDays;
    private String description;

    @JsonProperty("duration_days")
    public long getDurationDays() { return durationDays; }
    @JsonProperty("duration_days")
    public void setDurationDays(long value) { this.durationDays = value; }

    @JsonProperty("description")
    public String getDescription() { return description; }
    @JsonProperty("description")
    public void setDescription(String value) { this.description = value; }
}

// FertileDay.java

package com.apiverve.ovulationcalculator.data;

import com.fasterxml.jackson.annotation.*;
import java.time.LocalDate;

public class FertileDay {
    private LocalDate date;
    private long dayRelativeToOvulation;
    private String fertilityLevel;
    private String description;

    @JsonProperty("date")
    public LocalDate getDate() { return date; }
    @JsonProperty("date")
    public void setDate(LocalDate value) { this.date = value; }

    @JsonProperty("day_relative_to_ovulation")
    public long getDayRelativeToOvulation() { return dayRelativeToOvulation; }
    @JsonProperty("day_relative_to_ovulation")
    public void setDayRelativeToOvulation(long value) { this.dayRelativeToOvulation = value; }

    @JsonProperty("fertility_level")
    public String getFertilityLevel() { return fertilityLevel; }
    @JsonProperty("fertility_level")
    public void setFertilityLevel(String value) { this.fertilityLevel = value; }

    @JsonProperty("description")
    public String getDescription() { return description; }
    @JsonProperty("description")
    public void setDescription(String value) { this.description = value; }
}

// FertileWindow.java

package com.apiverve.ovulationcalculator.data;

import com.fasterxml.jackson.annotation.*;
import java.time.LocalDate;

public class FertileWindow {
    private LocalDate start;
    private LocalDate end;
    private long durationDays;

    @JsonProperty("start")
    public LocalDate getStart() { return start; }
    @JsonProperty("start")
    public void setStart(LocalDate value) { this.start = value; }

    @JsonProperty("end")
    public LocalDate getEnd() { return end; }
    @JsonProperty("end")
    public void setEnd(LocalDate value) { this.end = value; }

    @JsonProperty("duration_days")
    public long getDurationDays() { return durationDays; }
    @JsonProperty("duration_days")
    public void setDurationDays(long value) { this.durationDays = value; }
}

// NextPeriod.java

package com.apiverve.ovulationcalculator.data;

import com.fasterxml.jackson.annotation.*;
import java.time.LocalDate;

public class NextPeriod {
    private LocalDate date;
    private long daysFromLastPeriod;

    @JsonProperty("date")
    public LocalDate getDate() { return date; }
    @JsonProperty("date")
    public void setDate(LocalDate value) { this.date = value; }

    @JsonProperty("days_from_last_period")
    public long getDaysFromLastPeriod() { return daysFromLastPeriod; }
    @JsonProperty("days_from_last_period")
    public void setDaysFromLastPeriod(long value) { this.daysFromLastPeriod = value; }
}