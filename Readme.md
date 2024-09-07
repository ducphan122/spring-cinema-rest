## Stage 1/4:The show begins
Our cinema room has 9 rows with 9 seats each, so the total number of respective rows and columns also amounts to 9 each.

Note that the seats array contains 81 elements, as there are 81 seats in the room.
GET /seats
{
   "rows": 9,
   "columns": 9,
   "seats": [
      {
         "row": 1,
         "column": 1
      },
      {
         "row":1,
         "column":2
      },
      {
         "row": 1,
         "column": 3
      },

      ........

      {
         "row": 9,
         "column": 8
      },
      {
         "row": 9,
         "column": 9
      }
   ]
}

## Stage 2/4:Take your seat
GET /seats
The ticket price is determined by a row number. If the row number is less or equal to 4, set the price at 10. All other rows cost 8 per seat.
{
   "rows": 9,
   "columns": 9,
   "seats": [
      {
         "row": 1,
         "column": 1,
         "price": 10
      }
   ]
}

POST /purchase
{
   "row": 1,
   "column": 1
}
response
{
    "row": 5,
    "column": 7,
    "price": 8
}
If the seat is taken, respond with a 400 (Bad Request) status code and the message "The ticket has been already purchased!"
If users pass a wrong row/column number, respond with a 400 (Bad Request) status code and the message "The number of a row or a column is out of bounds!"

## Stage 3/4:A change of plans
- Change the JSON response when a customer purchases a ticket by making a POST request to the /purchase endpoint. Turn it into the following format:

{
    "token": "00ae15f2-1ab6-4a02-a01f-07810b42c0ee",
    "ticket": {
        "row": 1,
        "column": 1,
        "price": 10
    }
}
- Implement the /return endpoint, which will handle POST requests and allow customers to refund their tickets.

The request should have the token feature that identifies the ticket in the request body. Once you have the token, you need to identify the ticket it relates to and mark it as available. The response body should be as follows:

{
    "ticket": {
        "row": 1,
        "column": 1,
        "price": 10
    }
}

## Stage 4/4:The statistics
Objectives

Implement the /stats endpoint that will handle GET requests with URL parameters. If the URL parameters contain a password key with a super_secret value, return the movie theatre statistics in the following format:

{
    "income": 0,
    "available": 81,
    "purchased": 0
}
If the parameters don't contain a password key or a wrong value has been passed, respond with a 401 status code. The response body should contain the following:

{
    "error": "The password is wrong!"
}