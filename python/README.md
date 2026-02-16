Ovulation Calculator API
============

Ovulation Calculator estimates ovulation dates, fertile windows, and provides detailed cycle phase information based on menstrual cycle data.

![Build Status](https://img.shields.io/badge/build-passing-green)
![Code Climate](https://img.shields.io/badge/maintainability-B-purple)
![Prod Ready](https://img.shields.io/badge/production-ready-blue)

This is a Python API Wrapper for the [Ovulation Calculator API](https://apiverve.com/marketplace/ovulationcalculator?utm_source=pypi&utm_medium=readme)

---

## Installation

Using `pip`:

```bash
pip install apiverve-ovulationcalculator
```

Using `pip3`:

```bash
pip3 install apiverve-ovulationcalculator
```

---

## Configuration

Before using the ovulationcalculator API client, you have to setup your account and obtain your API Key.
You can get it by signing up at [https://apiverve.com](https://apiverve.com?utm_source=pypi&utm_medium=readme)

---

## Quick Start

Here's a simple example to get you started quickly:

```python
from apiverve_ovulationcalculator.apiClient import OvulationcalculatorAPIClient

# Initialize the client with your APIVerve API key
api = OvulationcalculatorAPIClient("[YOUR_API_KEY]")

query = {
    "last_period": "2024-01-01",
    "cycle_length": 28
}

try:
    # Make the API call
    result = api.execute(query)

    # Print the result
    print(result)
except Exception as e:
    print(f"Error: {e}")
```

---

## Usage

The Ovulation Calculator API documentation is found here: [https://docs.apiverve.com/ref/ovulationcalculator](https://docs.apiverve.com/ref/ovulationcalculator?utm_source=pypi&utm_medium=readme).
You can find parameters, example responses, and status codes documented here.

### Setup

```python
# Import the client module
from apiverve_ovulationcalculator.apiClient import OvulationcalculatorAPIClient

# Initialize the client with your APIVerve API key
api = OvulationcalculatorAPIClient("[YOUR_API_KEY]")
```

---

## Perform Request

Using the API client, you can perform requests to the API.

###### Define Query

```python
query = {
    "last_period": "2024-01-01",
    "cycle_length": 28
}
```

###### Simple Request

```python
# Make a request to the API
result = api.execute(query)

# Print the result
print(result)
```

###### Example Response

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

## Error Handling

The API client provides comprehensive error handling through the `OvulationcalculatorAPIClientError` exception. Here are some examples:

### Basic Error Handling

```python
from apiverve_ovulationcalculator.apiClient import OvulationcalculatorAPIClient, OvulationcalculatorAPIClientError

api = OvulationcalculatorAPIClient("[YOUR_API_KEY]")

query = {
    "last_period": "2024-01-01",
    "cycle_length": 28
}

try:
    result = api.execute(query)
    print("Success!")
    print(result)
except OvulationcalculatorAPIClientError as e:
    print(f"API Error: {e.message}")
    if e.status_code:
        print(f"Status Code: {e.status_code}")
    if e.response:
        print(f"Response: {e.response}")
```

### Handling Specific Error Types

```python
from apiverve_ovulationcalculator.apiClient import OvulationcalculatorAPIClient, OvulationcalculatorAPIClientError

api = OvulationcalculatorAPIClient("[YOUR_API_KEY]")

query = {
    "last_period": "2024-01-01",
    "cycle_length": 28
}

try:
    result = api.execute(query)

    # Check for successful response
    if result.get('status') == 'success':
        print("Request successful!")
        print(result.get('data'))
    else:
        print(f"API returned an error: {result.get('error')}")

except OvulationcalculatorAPIClientError as e:
    # Handle API client errors
    if e.status_code == 401:
        print("Unauthorized: Invalid API key")
    elif e.status_code == 429:
        print("Rate limit exceeded")
    elif e.status_code >= 500:
        print("Server error - please try again later")
    else:
        print(f"API error: {e.message}")
except Exception as e:
    # Handle unexpected errors
    print(f"Unexpected error: {str(e)}")
```

### Using Context Manager (Recommended)

The client supports the context manager protocol for automatic resource cleanup:

```python
from apiverve_ovulationcalculator.apiClient import OvulationcalculatorAPIClient, OvulationcalculatorAPIClientError

query = {
    "last_period": "2024-01-01",
    "cycle_length": 28
}

# Using context manager ensures proper cleanup
with OvulationcalculatorAPIClient("[YOUR_API_KEY]") as api:
    try:
        result = api.execute(query)
        print(result)
    except OvulationcalculatorAPIClientError as e:
        print(f"Error: {e.message}")
# Session is automatically closed here
```

---

## Advanced Features

### Debug Mode

Enable debug logging to see detailed request and response information:

```python
from apiverve_ovulationcalculator.apiClient import OvulationcalculatorAPIClient

# Enable debug mode
api = OvulationcalculatorAPIClient("[YOUR_API_KEY]", debug=True)

query = {
    "last_period": "2024-01-01",
    "cycle_length": 28
}

# Debug information will be printed to console
result = api.execute(query)
```

### Manual Session Management

If you need to manually manage the session lifecycle:

```python
from apiverve_ovulationcalculator.apiClient import OvulationcalculatorAPIClient

api = OvulationcalculatorAPIClient("[YOUR_API_KEY]")

query = {
    "last_period": "2024-01-01",
    "cycle_length": 28
}

try:
    result = api.execute(query)
    print(result)
finally:
    # Manually close the session when done
    api.close()
```

---

## Customer Support

Need any assistance? [Get in touch with Customer Support](https://apiverve.com/contact?utm_source=pypi&utm_medium=readme).

---

## Updates
Stay up to date by following [@apiverveHQ](https://twitter.com/apiverveHQ) on Twitter.

---

## Legal

All usage of the APIVerve website, API, and services is subject to the [APIVerve Terms of Service](https://apiverve.com/terms?utm_source=pypi&utm_medium=readme) and all legal documents and agreements.

---

## License
Licensed under the The MIT License (MIT)

Copyright (&copy;) 2026 APIVerve, and EvlarSoft LLC

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
