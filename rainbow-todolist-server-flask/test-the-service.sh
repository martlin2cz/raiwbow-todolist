PORT=5000
TASK_ID=5e5ba352-42ad-4417-b5d4-1c8d969e4e2f


echo "list all:"
curl -X GET --silent http://localhost:$PORT/api/v0.2/rainbowtodolist/tasks
echo

echo "insert:"
curl -X PUT --data "text=try to run the app" --silent http://localhost:$PORT/api/v0.2/rainbowtodolist/tasks
echo

echo "get:"
curl -X GET --silent http://localhost:$PORT/api/v0.2/rainbowtodolist/tasks/$TASK_ID
echo

echo "update text:"
curl -X PATCH --data "text=Run the app!" --silent http://localhost:$PORT/api/v0.2/rainbowtodolist/tasks/$TASK_ID
echo


echo "delete:"
curl -X DELETE --silent http://localhost:$PORT/api/v0.2/rainbowtodolist/tasks/$TASK_ID
echo

