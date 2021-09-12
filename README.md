# Opening Hours

### Problem Statement

Build a simple REST API that receives JSON data as input and returns a human readable output as response. The data received is structured
JSON of opening hours for a restaurant and this information gets parsed into a
text understood by humans.

### Assumptions
1. The restaurant follows the format open-close-open i.e. before reopening the restaurant shall be in a closed state and vice versa.

### Solution

We will write an endpoint that accepts **JSON-formatted** opening hours of a
restaurant as an input and returns the rendered **human readable format** as a text output.

Input JSON consists of keys indicating days of a week and corresponding opening hours
as values. One JSON file includes data for one restaurant.

```
{
    <dayofweek>: <opening hours>
    <dayofweek>: <opening hours>
    ...
}
```

**&lt;dayofweek&gt;**: monday / tuesday / wednesday / thursday / friday / saturday / sunday  
**&lt;opening hours&gt;**: an array of objects containing opening hours. Each object consist of
two keys:
- type: open or close
- value: opening / closing time as UNIX time (1.1.1970 as a date),  
  e.g. 32400 = 9 AM, 37800 = 10.30 AM,  
  max value is 86399 = 11.59:59 PM


Example: on Mondays a restaurant is open from 9 AM to 8 PM

```
{
    "monday" : [
        {
            "type" : "open",
            "value" : 32400
        },
        {
            "type" : "close",
            "value" : 72000
        }
    ],
    ...
}
```

### Special cases

- If a restaurant is closed the whole day, an array of opening hours is empty.
    - “tuesday”: [] means a restaurant is closed on Tuesdays
- A restaurant can be opened and closed multiple times during the same day,
    - E.g. on Mondays from 9 AM - 11 AM and from 1 PM to 5 PM
- A restaurant might not be closed during the same day
    - A restaurant can be opened e.g. on a Friday evening and closed early
      Saturday morning. In that case friday-object includes only the opening time.
      Closing time is part of the saturday-object.
    - When printing opening hours which span between multiple days, closing
      time is always a part of the day when a restaurant was opened (e.g. Friday 8
      PM - 1 AM)

```
{
    "friday" : [
        {
            "type" : "open",
            "value" : 64800
        }
    ],
    “saturday”: [
        {
            "type" : "close",
            "value" : 3600
        },
        {
            "type" : "open",
            "value" : 32400
        },
        {                        # A restaurant is open:
            "type" : "close",    # Friday: 6 PM - 1 AM
            "value" : 39600      # Saturday: 9 AM -11 AM, 4 PM - 11 PM
        },
        {
            "type" : "open",
            "value" : 57600
        },
        {
            "type" : "close",
            "value" : 82800
        }
    ]
}
```

### Running and testing the solution

1. Install `jdk 1.8` and `maven 3.3.3`
2. Clone/Download the repository `Opening-Hour`
3. Open terminal/cmd and run `cd <path_to_Opening-Hour_directory>`
4. run `mvn clean install`
5. run `mvn spring-boot:run`
6. Open `http://localhost:8080/swagger-ui/` in browser.
4. Try out the `/open-hour/convert` POST endpoint in open-hour-controller with the following as inputTimeDto

    ```
    {
    "tuesday": [
            {
                "type": "open",
                "value": 36000
            },
            {
                "type": "close",
                "value": 64800
            }
        ],
        "wednesday": [],
        "monday": [],
        "thursday": [
            {
                "type": "open",
                "value": 37800
            },
            {
                "type": "close",
                "value": 64800
            }
        ],
        "friday": [
            {
                "type": "open",
                "value": 64800
            }
        ],
        "saturday": [
            {
                "type": "close",
                "value": 3600
            },
            {
                "type": "open",
                "value": 32400
            },
            {
                "type": "close",
                "value": 39600
            },
            {
                "type": "open",
                "value": 57600
            },
            {
                "type": "close",
                "value": 82800
            }
        ],
        "sunday": [
            {
                "type": "open",
                "value": 43200
            },
            {
                "type": "close",
                "value": 75600
            }
        ]
    }
    ```

   You should get the following output

   ```bash
     {
        "timeStamp": <timestamp>,
        "status": "success",
        "message": "Operation Successful",
        "data": {
            "Monday": "Closed",
            "Tuesday": "10:00 AM - 6:00 PM",
            "Wednesday": "Closed",
            "Thursday": "10:30 AM - 6:00 PM",
            "Friday": "6:00 PM - 1:00 AM",
            "Saturday": "9:00 AM - 11:00 AM, 4:00 PM - 11:00 PM",
            "Sunday": "12:00 PM - 9:00 PM"
        }
    }
   ```
