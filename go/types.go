// Package ovulationcalculator provides a Go client for the Ovulation Calculator API.
//
// For more information, visit: https://apiverve.com/marketplace/ovulationcalculator?utm_source=go&utm_medium=readme
package ovulationcalculator

import (
	"fmt"
	"reflect"
	"regexp"
	"strings"
)

// ValidationRule defines validation constraints for a parameter.
type ValidationRule struct {
	Type      string
	Required  bool
	Min       *float64
	Max       *float64
	MinLength *int
	MaxLength *int
	Format    string
	Enum      []string
}

// ValidationError represents a parameter validation error.
type ValidationError struct {
	Errors []string
}

func (e *ValidationError) Error() string {
	return "Validation failed: " + strings.Join(e.Errors, "; ")
}

// Helper functions for pointers
func float64Ptr(v float64) *float64 { return &v }
func intPtr(v int) *int             { return &v }

// Format validation patterns
var formatPatterns = map[string]*regexp.Regexp{
	"email":    regexp.MustCompile(`^[^\s@]+@[^\s@]+\.[^\s@]+$`),
	"url":      regexp.MustCompile(`^https?://.+`),
	"ip":       regexp.MustCompile(`^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$|^([0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$`),
	"date":     regexp.MustCompile(`^\d{4}-\d{2}-\d{2}$`),
	"hexColor": regexp.MustCompile(`^#?([0-9a-fA-F]{3}|[0-9a-fA-F]{6})$`),
}

// Request contains the parameters for the Ovulation Calculator API.
//
// Parameters:
//   - last_period (required): string - First day of last menstrual period (YYYY-MM-DD) [format: date]
//   - cycle_length: integer - Average menstrual cycle length in days [min: 21, max: 35]
type Request struct {
	LastPeriod string `json:"last_period"` // Required
	CycleLength int `json:"cycle_length,omitempty"` // Optional
}

// ToQueryParams converts the request struct to a map of query parameters.
// Only non-zero values are included.
func (r *Request) ToQueryParams() map[string]string {
	params := make(map[string]string)
	if r == nil {
		return params
	}

	v := reflect.ValueOf(*r)
	t := v.Type()

	for i := 0; i < v.NumField(); i++ {
		field := v.Field(i)
		fieldType := t.Field(i)

		// Get the json tag for the field name
		jsonTag := fieldType.Tag.Get("json")
		if jsonTag == "" {
			continue
		}
		// Handle tags like `json:"name,omitempty"`
		jsonName := strings.Split(jsonTag, ",")[0]
		if jsonName == "-" {
			continue
		}

		// Skip zero values
		if field.IsZero() {
			continue
		}

		// Convert to string
		params[jsonName] = fmt.Sprintf("%v", field.Interface())
	}

	return params
}

// Validate checks the request parameters against validation rules.
// Returns a ValidationError if validation fails, nil otherwise.
func (r *Request) Validate() error {
	rules := map[string]ValidationRule{
		"last_period": {Type: "string", Required: true, Format: "date"},
		"cycle_length": {Type: "integer", Required: false, Min: float64Ptr(21), Max: float64Ptr(35)},
	}

	if len(rules) == 0 {
		return nil
	}

	var errors []string
	v := reflect.ValueOf(*r)
	t := v.Type()

	for i := 0; i < v.NumField(); i++ {
		field := v.Field(i)
		fieldType := t.Field(i)

		jsonTag := fieldType.Tag.Get("json")
		if jsonTag == "" {
			continue
		}
		jsonName := strings.Split(jsonTag, ",")[0]

		rule, exists := rules[jsonName]
		if !exists {
			continue
		}

		// Check required
		if rule.Required && field.IsZero() {
			errors = append(errors, fmt.Sprintf("Required parameter [%s] is missing", jsonName))
			continue
		}

		if field.IsZero() {
			continue
		}

		// Type-specific validation
		switch rule.Type {
		case "integer", "number":
			var numVal float64
			switch field.Kind() {
			case reflect.Int, reflect.Int64:
				numVal = float64(field.Int())
			case reflect.Float64:
				numVal = field.Float()
			}
			if rule.Min != nil && numVal < *rule.Min {
				errors = append(errors, fmt.Sprintf("Parameter [%s] must be at least %v", jsonName, *rule.Min))
			}
			if rule.Max != nil && numVal > *rule.Max {
				errors = append(errors, fmt.Sprintf("Parameter [%s] must be at most %v", jsonName, *rule.Max))
			}

		case "string":
			strVal := field.String()
			if rule.MinLength != nil && len(strVal) < *rule.MinLength {
				errors = append(errors, fmt.Sprintf("Parameter [%s] must be at least %d characters", jsonName, *rule.MinLength))
			}
			if rule.MaxLength != nil && len(strVal) > *rule.MaxLength {
				errors = append(errors, fmt.Sprintf("Parameter [%s] must be at most %d characters", jsonName, *rule.MaxLength))
			}
			if rule.Format != "" {
				if pattern, ok := formatPatterns[rule.Format]; ok {
					if !pattern.MatchString(strVal) {
						errors = append(errors, fmt.Sprintf("Parameter [%s] must be a valid %s", jsonName, rule.Format))
					}
				}
			}
		}

		// Enum validation
		if len(rule.Enum) > 0 {
			strVal := fmt.Sprintf("%v", field.Interface())
			found := false
			for _, enumVal := range rule.Enum {
				if strVal == enumVal {
					found = true
					break
				}
			}
			if !found {
				errors = append(errors, fmt.Sprintf("Parameter [%s] must be one of: %s", jsonName, strings.Join(rule.Enum, ", ")))
			}
		}
	}

	if len(errors) > 0 {
		return &ValidationError{Errors: errors}
	}
	return nil
}

// ResponseData contains the data returned by the Ovulation Calculator API.
type ResponseData struct {
	LastPeriod string `json:"last_period"`
	CycleLength int `json:"cycle_length"`
	Ovulation OvulationData `json:"ovulation"`
	FertileWindow FertileWindowData `json:"fertile_window"`
	MostFertileDays MostFertileDaysData `json:"most_fertile_days"`
	FertileDays []FertileDaysItem `json:"fertile_days"`
	NextPeriod NextPeriodData `json:"next_period"`
	CyclePhases CyclePhasesData `json:"cycle_phases"`
	CurrentStatus CurrentStatusData `json:"current_status"`
	Disclaimer string `json:"disclaimer"`
}

// OvulationData represents the ovulation object.
type OvulationData struct {
	Date string `json:"date"`
	DaysFromLastPeriod int `json:"days_from_last_period"`
}

// FertileWindowData represents the fertile_window object.
type FertileWindowData struct {
	Start string `json:"start"`
	End string `json:"end"`
	DurationDays int `json:"duration_days"`
}

// MostFertileDaysData represents the most_fertile_days object.
type MostFertileDaysData struct {
	Start string `json:"start"`
	End string `json:"end"`
	DurationDays int `json:"duration_days"`
}

// FertileDaysItem represents an item in the FertileDays array.
type FertileDaysItem struct {
	Date string `json:"date"`
	DayRelativeToOvulation int `json:"day_relative_to_ovulation"`
	FertilityLevel string `json:"fertility_level"`
	Description string `json:"description"`
}

// NextPeriodData represents the next_period object.
type NextPeriodData struct {
	Date string `json:"date"`
	DaysFromLastPeriod int `json:"days_from_last_period"`
}

// CyclePhasesData represents the cycle_phases object.
type CyclePhasesData struct {
	FollicularPhase FollicularPhaseData `json:"follicular_phase"`
	Ovulation OvulationData `json:"ovulation"`
	LutealPhase LutealPhaseData `json:"luteal_phase"`
}

// FollicularPhaseData represents the follicular_phase object.
type FollicularPhaseData struct {
	DurationDays int `json:"duration_days"`
	Description string `json:"description"`
}

// OvulationData represents the ovulation object.
type OvulationData struct {
	DurationDays int `json:"duration_days"`
	Description string `json:"description"`
}

// LutealPhaseData represents the luteal_phase object.
type LutealPhaseData struct {
	DurationDays int `json:"duration_days"`
	Description string `json:"description"`
}

// CurrentStatusData represents the current_status object.
type CurrentStatusData struct {
	CurrentPhase string `json:"current_phase"`
	IsFertile bool `json:"is_fertile"`
	DaysUntilOvulation int `json:"days_until_ovulation"`
	DaysUntilNextPeriod int `json:"days_until_next_period"`
}
