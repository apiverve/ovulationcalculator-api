# Ovulation Calculator API - PHP Package

Ovulation Calculator estimates ovulation dates, fertile windows, and provides detailed cycle phase information based on menstrual cycle data.

## Installation

Install via Composer:

```bash
composer require apiverve/ovulationcalculator
```

## Getting Started

Get your API key at [APIVerve](https://apiverve.com)

### Basic Usage

```php
<?php

require_once 'vendor/autoload.php';

use APIVerve\Ovulationcalculator\Client;

// Initialize the client
$client = new Client('YOUR_API_KEY');

// Make a request
$response = $client->execute([
    'last_period' => '2024-01-01',
    'cycle_length' => 28
]);

// Print the response
print_r($response);
```


### Error Handling

```php
use APIVerve\Ovulationcalculator\Client;
use APIVerve\Ovulationcalculator\Exceptions\APIException;
use APIVerve\Ovulationcalculator\Exceptions\ValidationException;

try {
    $response = $client->execute(['last_period' => '2024-01-01', 'cycle_length' => 28]);
    print_r($response['data']);
} catch (ValidationException $e) {
    echo "Validation error: " . implode(', ', $e->getErrors());
} catch (APIException $e) {
    echo "API error: " . $e->getMessage();
    echo "Status code: " . $e->getStatusCode();
}
```

### Debug Mode

```php
// Enable debug logging
$client = new Client(
    apiKey: 'YOUR_API_KEY',
    debug: true
);
```

## Example Response

```json
{
  "status": "ok",
  "error": null,
  "data": {
    "last_period": "2024-01-01",
    "cycle_length": 28,
    "ovulation": {
      "date": "2024-01-15",
      "days_from_last_period": 14
    },
    "fertile_window": {
      "start": "2024-01-10",
      "end": "2024-01-15",
      "duration_days": 6
    },
    "most_fertile_days": {
      "start": "2024-01-13",
      "end": "2024-01-15",
      "duration_days": 3
    },
    "fertile_days": [
      {
        "date": "2024-01-10",
        "day_relative_to_ovulation": -5,
        "fertility_level": "medium",
        "description": "Fertile"
      },
      {
        "date": "2024-01-11",
        "day_relative_to_ovulation": -4,
        "fertility_level": "medium",
        "description": "Fertile"
      },
      {
        "date": "2024-01-12",
        "day_relative_to_ovulation": -3,
        "fertility_level": "medium",
        "description": "Fertile"
      },
      {
        "date": "2024-01-13",
        "day_relative_to_ovulation": -2,
        "fertility_level": "high",
        "description": "Most fertile"
      },
      {
        "date": "2024-01-14",
        "day_relative_to_ovulation": -1,
        "fertility_level": "high",
        "description": "Most fertile"
      },
      {
        "date": "2024-01-15",
        "day_relative_to_ovulation": 0,
        "fertility_level": "high",
        "description": "Most fertile"
      }
    ],
    "next_period": {
      "date": "2024-01-29",
      "days_from_last_period": 28
    },
    "cycle_phases": {
      "follicular_phase": {
        "duration_days": 14,
        "description": "From first day of period to ovulation"
      },
      "ovulation": {
        "duration_days": 1,
        "description": "Egg is released from ovary"
      },
      "luteal_phase": {
        "duration_days": 14,
        "description": "From ovulation to next period"
      }
    },
    "current_status": {
      "current_phase": "Menstruation",
      "is_fertile": false,
      "days_until_ovulation": -701,
      "days_until_next_period": -687
    },
    "disclaimer": "This calculator provides estimates only. Actual ovulation may vary. Consult a healthcare provider for medical advice."
  }
}
```

## Requirements

- PHP 7.4 or higher
- Guzzle HTTP client

## Documentation

For more information, visit the [API Documentation](https://docs.apiverve.com/ref/ovulationcalculator?utm_source=packagist&utm_medium=referral&utm_content=readme).

## Support

- Website: [https://apiverve.com/marketplace/ovulationcalculator?utm_source=php&utm_medium=referral&utm_content=readme](https://apiverve.com/marketplace/ovulationcalculator?utm_source=php&utm_medium=referral&utm_content=readme)
- Email: hello@apiverve.com

## License

This package is available under the [MIT License](LICENSE).
