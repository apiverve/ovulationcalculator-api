# Ovulation Calculator API - Go Client

Ovulation Calculator estimates ovulation dates, fertile windows, and provides detailed cycle phase information based on menstrual cycle data.

![Build Status](https://img.shields.io/badge/build-passing-green)
![Code Climate](https://img.shields.io/badge/maintainability-B-purple)
![Prod Ready](https://img.shields.io/badge/production-ready-blue)

This is a Go client for the [Ovulation Calculator API](https://apiverve.com/marketplace/ovulationcalculator?utm_source=go&utm_medium=readme)

---

## Installation

```bash
go get github.com/apiverve/ovulationcalculator-api/go
```

---

## Configuration

Before using the Ovulation Calculator API client, you need to obtain your API key.
You can get it by signing up at [https://apiverve.com](https://apiverve.com?utm_source=go&utm_medium=readme)

---

## Quick Start

[Get started with the Quick Start Guide](https://docs.apiverve.com/quickstart?utm_source=go&utm_medium=readme)

The Ovulation Calculator API documentation is found here: [https://docs.apiverve.com/ref/ovulationcalculator](https://docs.apiverve.com/ref/ovulationcalculator?utm_source=go&utm_medium=readme)

---

## Usage

```go
package main

import (
    "fmt"
    "log"

    "github.com/apiverve/ovulationcalculator-api/go"
)

func main() {
    // Create a new client
    client := ovulationcalculator.NewClient("YOUR_API_KEY")

    // Set up parameters
    params := map[string]interface{}{
        "last_period": "2024-01-01",
        "cycle_length": 28
    }

    // Make the request
    response, err := client.Execute(params)
    if err != nil {
        log.Fatal(err)
    }

    fmt.Printf("Status: %s\n", response.Status)
    fmt.Printf("Data: %+v\n", response.Data)
}
```

---

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
      "days_until_ovulation": -670,
      "days_until_next_period": -656
    },
    "disclaimer": "This calculator provides estimates only. Actual ovulation may vary. Consult a healthcare provider for medical advice."
  }
}
```

---

## Customer Support

Need any assistance? [Get in touch with Customer Support](https://apiverve.com/contact?utm_source=go&utm_medium=readme).

---

## Updates

Stay up to date by following [@apiverveHQ](https://twitter.com/apiverveHQ) on Twitter.

---

## Legal

All usage of the APIVerve website, API, and services is subject to the [APIVerve Terms of Service](https://apiverve.com/terms?utm_source=go&utm_medium=readme), [Privacy Policy](https://apiverve.com/privacy?utm_source=go&utm_medium=readme), and [Refund Policy](https://apiverve.com/refund?utm_source=go&utm_medium=readme).

---

## License
Licensed under the The MIT License (MIT)

Copyright (&copy;) 2026 APIVerve, and EvlarSoft LLC

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
