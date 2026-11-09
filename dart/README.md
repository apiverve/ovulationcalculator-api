# Ovulation Calculator API - Dart/Flutter Client

Ovulation Calculator estimates ovulation dates, fertile windows, and provides detailed cycle phase information based on menstrual cycle data.

[![pub package](https://img.shields.io/pub/v/apiverve_ovulationcalculator.svg)](https://pub.dev/packages/apiverve_ovulationcalculator)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

This is the Dart/Flutter client for the [Ovulation Calculator API](https://apiverve.com/marketplace/ovulationcalculator?utm_source=dart&utm_medium=referral&utm_content=readme).

## Installation

Add this to your `pubspec.yaml`:

```yaml
dependencies:
  apiverve_ovulationcalculator: ^1.2.5
```

Then run:

```bash
dart pub get
# or for Flutter
flutter pub get
```

## Usage

```dart
import 'package:apiverve_ovulationcalculator/apiverve_ovulationcalculator.dart';

void main() async {
  final client = OvulationcalculatorClient('YOUR_API_KEY');

  try {
    final response = await client.execute({
      'last_period': '2024-01-01',
      'cycle_length': 28
    });

    print('Status: ${response.status}');
    print('Data: ${response.data}');
  } catch (e) {
    print('Error: $e');
  }
}
```

## Response

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

## API Reference

- **API Home:** [Ovulation Calculator API](https://apiverve.com/marketplace/ovulationcalculator?utm_source=dart&utm_medium=referral&utm_content=readme)
- **Documentation:** [docs.apiverve.com/ref/ovulationcalculator](https://docs.apiverve.com/ref/ovulationcalculator?utm_source=dart&utm_medium=referral&utm_content=readme)

## Authentication

All requests require an API key. Get yours at [apiverve.com](https://apiverve.com?utm_source=dart&utm_medium=referral&utm_content=readme).

## License

MIT License - see [LICENSE](LICENSE) for details.

---

Built with Dart for [APIVerve](https://apiverve.com?utm_source=dart&utm_medium=referral&utm_content=readme)
