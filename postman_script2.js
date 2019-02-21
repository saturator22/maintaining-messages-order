//1. f na 8 pozycje
//2. a na 42 pozycje
//3. k na 1 pozycje
//4. c na początku
//5. x na 3 od konca
//6. d na początku
//7. a na 4 pozycje
//8. l na 2 od konca
// {"timeStampMillis": {"timeStampMillis":17},"message":{"message":"l"},"offSet":{"offSet":-2}} <-- tego jSona należy umieścić w Body
//wynik : dcfxakla

const echoPostRequest = {
  url: 'http://localhost:8080/message',
  method: 'POST',
  header: {'content-type':'application/json'},
  body: {
    mode: 'raw',
    raw: JSON.stringify({"timeStampMillis": {"timeStampMillis":10}, "message":{"message":"f"},	"offSet":{"offSet":8} })
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
    raw: JSON.stringify({"timeStampMillis": {"timeStampMillis":11}, "message":{"message":"a"},	"offSet":{"offSet":42}})
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
    raw: JSON.stringify({"timeStampMillis": {"timeStampMillis":12}, "message":{"message":"k"},	"offSet":{"offSet":1} })
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
    raw: JSON.stringify({"timeStampMillis": {"timeStampMillis":13},"message":{message:"c"},	"offSet":{"offSet":0} })
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
    raw: JSON.stringify({"timeStampMillis": {"timeStampMillis":14},"message":{"message":"x"},	"offSet":{"offSet":-3} })
  }
};
pm.sendRequest(echoPostRequest6, function (err, res) {
  console.log(err ? err : res.json());
});
const echoPostRequest7 = {
  url: 'http://localhost:8080/message',
  method: 'POST',
  header: {'content-type':'application/json'},
  body: {
    mode: 'raw',
    raw: JSON.stringify({"timeStampMillis": {"timeStampMillis":15},"message":{"message":"d"},	"offSet":{"offSet":0}})
  }
};
pm.sendRequest(echoPostRequest7, function (err, res) {
  console.log(err ? err : res.json());
});
const echoPostRequest8 = {
  url: 'http://localhost:8080/message',
  method: 'POST',
  header: {'content-type':'application/json'},
  body: {
    mode: 'raw',
    raw: JSON.stringify({"timeStampMillis": {"timeStampMillis":16},"message":{"message":"a"},	"offSet":{"offSet":4}})
  }
};
pm.sendRequest(echoPostRequest8, function (err, res) {
  console.log(err ? err : res.json());
});
