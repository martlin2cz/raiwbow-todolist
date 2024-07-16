PORT=8080
TASK_ID=12c56788-f283-4457-b4ec-2babe77af950


echo "list all (unordered):"
curl -X GET --silent http://localhost:$PORT/api/v0.1/rainbowtodolist/tasks
echo

echo "list all (ordered):"
curl -X GET --silent http://localhost:$PORT/api/v0.1/rainbowtodolist/tasks?order=BY_TEXT
echo

echo "get one:"
curl -X GET --silent http://localhost:$PORT/api/v0.1/rainbowtodolist/tasks/$TASK_ID
echo



echo "insert:"
curl -X PUT --data "try to run the app" -H "Content-Type: text/plain" --silent http://localhost:$PORT/api/v0.1/rainbowtodolist/tasks
echo

echo "update text:"
curl -X PATCH --data "Run the app!" -H "Content-Type: text/plain" --silent http://localhost:$PORT/api/v0.1/rainbowtodolist/tasks/$TASK_ID/text
echo

echo "update status:"
curl -X PATCH --data "DONE" -H "Content-Type: text/plain" --silent http://localhost:$PORT/api/v0.1/rainbowtodolist/tasks/$TASK_ID/status
echo



echo "delete:"
curl -X DELETE -H "Content-Type: text/plain" --silent http://localhost:$PORT/api/v0.1/rainbowtodolist/tasks/$TASK_ID
echo


echo "list all, again:"
curl -X GET --silent http://localhost:$PORT/api/v0.1/rainbowtodolist/tasks?order=BY_TEXT
echo


