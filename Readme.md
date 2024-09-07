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