
//"abstrakcyjny przyklad"
//{"timeStampMillis": {"timeStampMillis":14},"message":{"message":"f"},	"offSet":{"offSet":0}} <-- tą linijkę należy wkleić w Body

const echoPostRequest = {
  url: 'http://localhost:8080/message',
  method: 'POST',
  header: {'content-type':'application/json'},
  body: {
    mode: 'raw',
    raw: JSON.stringify({"timeStampMillis": {"timeStampMillis":11}, "message":{"message":"b"},	"offSet":{"offSet":-1} })
  }
};
pm.sendRequest(echoPostRequest, function (err, res) {
  console.log(err ? err : res.json());
});
const echoPostRequest2 = {
  url: 'http://localhost:8080/message',
  method: 'POST',
  header: {'content-type':'application/json'},
  body: {
    mode: 'raw',
    raw: JSON.stringify({"timeStampMillis": {"timeStampMillis":10}, "message":{"message":"a"},	"offSet":{"offSet":-1}})
  }
};
pm.sendRequest(echoPostRequest2, function (err, res) {
  console.log(err ? err : res.json());
});
const echoPostRequest3 = {
  url: 'http://localhost:8080/message',
  method: 'POST',
  header: {'content-type':'application/json'},
  body: {
    mode: 'raw',
    raw: JSON.stringify({"timeStampMillis": {"timeStampMillis":12}, "message":{"message":"c"},	"offSet":{"offSet":1} })
  }
};
pm.sendRequest(echoPostRequest3, function (err, res) {
  console.log(err ? err : res.json());
});
const echoPostRequest4 = {
  url: 'http://localhost:8080/message',
  method: 'POST',
  header: {'content-type':'application/json'},
  body: {
    mode: 'raw',
    raw: JSON.stringify({"timeStampMillis": {"timeStampMillis":13},"message":{message:"dg"},	"offSet":{"offSet":1} })
  }
};
pm.sendRequest(echoPostRequest4, function (err, res) {
  console.log(err ? err : res.json());
});


const echoPostRequest6 = {
  url: 'http://localhost:8080/message',
  method: 'POST',
  header: {'content-type':'application/json'},
  body: {
    mode: 'raw',
    raw: JSON.stringify({"timeStampMillis": {"timeStampMillis":15},"message":{"message":"r"},	"offSet":{"offSet":0} })
  }
};
pm.sendRequest(echoPostRequest6, function (err, res) {
  console.log(err ? err : res.json());
});
